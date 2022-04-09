package yeyue.ruoyi.study.framework.redis.core;

import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;

import java.util.*;

/**
 * 基础redis操作类集合
 *
 * @author yeyue
 * @date 2022-04-09 19:24:21
 */
public interface RedisRepository {

    // TODO: 2022/4/9 统一所有的缓存管理，后续根据使用需求补充

    /**
     * 删除缓存
     *
     * @param name 缓存声明名称
     * @param id   缓存Id
     */
    boolean delete(String name, Object id);

    /**
     * 设置缓存
     *
     * @param name   键名称
     * @param define 缓存定义对象
     */
    <T> void save(String name, RedisDomainDefine<T> define);

    /**
     * 设置缓存
     *
     * @param name    键名称
     * @param defines 缓存定义对象
     */
    <T> void save(String name, List<RedisDomainDefine<T>> defines);

    /**
     * 获取缓存
     *
     * @param name 键名称
     * @param id   缓存Id
     * @param <T>  获取的缓存类型
     * @return 缓存
     */
    <T> T get(String name, Object id);

    /**
     * 获取缓存
     *
     * @param name 键名称
     * @param ids  缓存Id
     * @param <T>  获取的缓存类型
     * @return 缓存
     */
    <T> Map<Object, T> get(String name, List<Object> ids);
}
