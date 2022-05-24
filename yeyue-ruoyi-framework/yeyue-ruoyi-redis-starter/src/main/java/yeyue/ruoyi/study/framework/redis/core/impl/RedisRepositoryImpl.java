package yeyue.ruoyi.study.framework.redis.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;
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

    RedissonClient redissonClient;

    ObjectMapper objectMapper;

    StringRedisTemplate redisTemplate;

    private static final StringCodec STRING_CODEC = new StringCodec();

    @Autowired
    public RedisRepositoryImpl(RedissonClient redissonClient, ObjectMapper objectMapper, StringRedisTemplate redisTemplate) {
        this.redissonClient = redissonClient;
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean delete(String name, Object id) {
        try {
            Boolean result = redisTemplate.delete(ObjectUtils.indexJoin(name, id));
            return Objects.equals(Boolean.TRUE, result);
        } catch (Throwable e) {
            log.error("[RedisRepository][delete][1]请求失败", e);
            return false;
        }
    }

    @Override
    public void delete(String name, Collection<Object> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        try {
            List<String> keys = CollectionUtils.funcList(ids, id -> ObjectUtils.indexJoin(name, id));
            redisTemplate.delete(keys);
        } catch (Throwable e) {
            log.error("[RedisRepository][delete][2]请求失败", e);
        }
    }

    @Override
    public <T> void save(String name, RedisDomainDefine<T> define) {
        try {
            String key = ObjectUtils.indexJoin(name, define.getId());
            String value = objectMapper.writeValueAsString(define.getValue());
            if (define.getTimeout() > 0) {
                redisTemplate
                        .opsForValue()
                        .set(key, value, define.getTimeout(), define.getTimeUnit());
            } else {
                redisTemplate
                        .opsForValue()
                        .set(key, value);
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
                String key = ObjectUtils.indexJoin(name, define.getId());
                String value = objectMapper.writeValueAsString(define.getValue());
                RBucketAsync<String> bucket = batch.getBucket(key, STRING_CODEC);
                if (define.getTimeout() > 0) {
                    bucket.setAsync(value, define.getTimeout(), define.getTimeUnit());
                } else {
                    bucket.setAsync(value);
                }
            }
            batch.execute();
        } catch (Throwable e) {
            log.error("[RedisRepository][save][2]请求失败", e);
        }
    }

    @Override
    public <T> T get(String name, Object id, TypeReference<T> type) {
        try {
            String key = ObjectUtils.indexJoin(name, id);
            String value = redisTemplate
                    .opsForValue()
                    .get(key);
            if (value == null) {
                return null;
            }
            return objectMapper.readValue(value, type);
        } catch (Throwable e) {
            log.error("[RedisRepository][get][1]请求失败", e);
            throw new ServiceException(GlobalErrorCode.REDIS_CLIENT_COMMAND_FAIL);
        }
    }

    @Override
    public <T> Map<Object, T> get(String name, List<Object> ids, TypeReference<T> type) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        try {
            RBatch batch = redissonClient.createBatch();
            Map<Object, T> map = CollectionUtils.newHashMap(ids.size());
            Map<Object, RFuture<String>> futureMap = CollectionUtils.newHashMap(ids.size());
            for (Object id : ids) {
                RBucketAsync<String> bucketAsync = batch.getBucket(ObjectUtils.indexJoin(name, id));
                futureMap.put(id, bucketAsync.getAsync());
            }
            batch.execute();
            for (Map.Entry<Object, RFuture<String>> entry : futureMap.entrySet()) {
                String value = entry
                        .getValue()
                        .getNow();
                if (value != null) {
                    map.put(entry.getKey(), objectMapper.readValue(value, type));
                }
            }
            return map;
        } catch (Throwable e) {
            log.error("[RedisRepository][get][2]请求失败", e);
            throw new ServiceException(GlobalErrorCode.REDIS_CLIENT_COMMAND_FAIL);
        }
    }
}
