package yeyue.ruoyi.study.framework.redis.core.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.*;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.redis.core.RedisRepository;
import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;

import java.util.*;

/**
 * @author yeyue
 * @date 2022-04-09 20:56:38
 */
@Slf4j
@Component
public class RedisRepositoryImpl implements RedisRepository {
    public static final String INDEX_JOIN = ":";

    RedissonClient redissonClient;

    JsonJacksonCodec jacksonCodec;

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Autowired
    public void setJacksonCodec(JsonJacksonCodec jacksonCodec) {
        this.jacksonCodec = jacksonCodec;
    }

    @Override
    public boolean delete(String name, Object id) {
        try {
            return redissonClient.getBucket(joinIndex(name, id)).delete();
        } catch (Throwable e) {
            log.error("[RedisRepository][delete]请求失败", e);
            return false;
        }
    }

    @Override
    public <T> void save(String name, RedisDomainDefine<T> define) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(joinIndex(name, define.getId()), jacksonCodec);
            if (define.getTimeout() >= 0) {
                bucket.set(define.getValue(), define.getTimeout(), define.getTimeUnit());
            } else {
                bucket.set(define.getValue());
            }
        } catch (Throwable e) {
            log.error("[RedisRepository][save][1]请求失败", e);
        }
    }

    @Override
    public <T> void save(String name, List<RedisDomainDefine<T>> defines) {
        if (CollectionUtils.isEmpty(defines)) {
            return;
        }
        try {
            RBatch batch = redissonClient.createBatch();
            for (RedisDomainDefine<T> define : defines) {
                RBucketAsync<T> bucket = batch.getBucket(joinIndex(name, define.getId()), jacksonCodec);
                if (define.getTimeout() >= 0) {
                    bucket.setAsync(define.getValue(), define.getTimeout(), define.getTimeUnit());
                } else {
                    bucket.setAsync(define.getValue());
                }
            }
            batch.execute();
        } catch (Throwable e) {
            log.error("[RedisRepository][save][2]请求失败", e);
        }
    }

    @Override
    public <T> T get(String name, Object id) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(joinIndex(name, id), jacksonCodec);
            return bucket.get();
        } catch (Throwable e) {
            log.error("[RedisRepository][get][1]请求失败", e);
            throw new ServiceException(GlobalErrorCode.REDIS_CLIENT_COMMAND_FAIL);
        }
    }

    @Override
    public <T> Map<Object, T> get(String name, List<Object> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        try {
            RBatch batch = redissonClient.createBatch();
            Map<Object, T> map = CollectionUtils.newHashMap(ids.size());
            Map<Object, RFuture<T>> futureMap = CollectionUtils.newHashMap(ids.size());
            for (Object id : ids) {
                RBucketAsync<T> bucketAsync = batch.getBucket(joinIndex(name, id));
                futureMap.put(id, bucketAsync.getAsync());
            }
            batch.execute();
            for (Map.Entry<Object, RFuture<T>> entry : futureMap.entrySet()) {
                T valueNow = entry.getValue().getNow();
                if (valueNow != null) {
                    map.put(entry.getKey(), valueNow);
                }
            }
            return map;
        } catch (Throwable e) {
            log.error("[RedisRepository][get][2]请求失败", e);
            throw new ServiceException(GlobalErrorCode.REDIS_CLIENT_COMMAND_FAIL);
        }
    }

    public static String joinIndex(Object... args) {
        return StringUtils.joinWith(INDEX_JOIN, args);
    }
}
