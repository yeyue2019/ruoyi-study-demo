package yeyue.ruoyi.study.framework.redis.domain;

import java.util.concurrent.TimeUnit;

import lombok.Data;

/**
 * redis实例定义
 *
 * @author yeyue
 * @date 2022-04-09 19:45:43
 */
@Data
public class RedisDomainDefine<T> {

    /**
     * 缓存id
     */
    private Object id;

    /**
     * 实例内容
     */
    private T value;

    /**
     * 缓存有效期
     */
    private long timeout;

    /**
     * 缓存有效期单位
     */
    private TimeUnit timeUnit;

    public RedisDomainDefine(Object id, T value, long timeout, TimeUnit timeUnit) {
        this.id = id;
        this.value = value;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public RedisDomainDefine(Object id, T value) {
        this.id = id;
        this.value = value;
    }
}
