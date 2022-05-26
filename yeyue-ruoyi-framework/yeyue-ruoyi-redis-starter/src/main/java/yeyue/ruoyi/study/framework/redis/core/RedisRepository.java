package yeyue.ruoyi.study.framework.redis.core;

import com.fasterxml.jackson.core.type.TypeReference;
import yeyue.ruoyi.study.framework.redis.domain.RedisDomainDefine;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础redis操作类集合
 *
 * @author yeyue
 * @date 2022-04-09 19:24:21
 */
public interface RedisRepository {

    /**
     * 删除缓存
     *
     * @param name 缓存声明名称
     * @param id   缓存Id
     */
    boolean delete(String name, Object id);

    /**
     * 删除缓存
     *
     * @param name 缓存声明名称
     * @param ids  缓存Id集合
     */
    void delete(String name, Collection<Object> ids);

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
     * @param type 类型
     * @param <T>  获取的缓存类型
     * @return 缓存
     */
    <T> T get(String name, Object id, TypeReference<T> type);

    /**
     * 获取缓存
     *
     * @param name 键名称
     * @param ids  缓存Id
     * @param type 类型
     * @param <T>  获取的缓存类型
     * @return 缓存
     */
    <T> Map<Object, T> get(String name, List<Object> ids, TypeReference<T> type);
}
