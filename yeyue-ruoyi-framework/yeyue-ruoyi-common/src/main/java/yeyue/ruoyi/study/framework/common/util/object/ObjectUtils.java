package yeyue.ruoyi.study.framework.common.util.object;

import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.constants.StringConstants;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

import java.util.*;
import java.util.function.Function;

/**
 * 对象操作集合
 *
 * @author yeyue
 * @date 2022-04-08 21:23:09
 */
public abstract class ObjectUtils {

    /**
     * 对象转化
     *
     * @param function      转换方法
     * @param source        源对象
     * @param defaultResult 源对象为空的默认返回结果
     * @return 结果
     */
    public static <T, R> R convert(T source, R defaultResult, Function<T, R> function) {
        return source == null ? defaultResult : function.apply(source);
    }

    /**
     * 对象组合索引
     *
     * @param args 对象集合
     * @return 索引
     */
    public static String indexJoin(Object... args) {
        return StringUtils.joinWith(StringConstants.INDEX_JOIN, args);
    }

    /**
     * 枚举条件校验
     *
     * @param clazz  枚举类型
     * @param func   枚举获取比较值的方法
     * @param target 比较对象
     * @param <E>    枚举对象
     * @param <C>    比较类型
     * @return 是否符合
     */
    public static <E extends Enum<E>, C> boolean valid(Class<E> clazz, Function<E, C> func, C target) {
        final E[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return false;
        }
        return Arrays.stream(enums).anyMatch(e -> Objects.equals(func.apply(e), target));
    }

    /**
     * 根据条件获取枚举集合
     *
     * @param clazz 枚举类型
     * @param func  获取函数
     * @param <E>   枚举对象
     * @param <C>   结果类型
     * @return 数组
     */
    public static <E extends Enum<E>, C> C[] getArray(Class<E> clazz, Function<E, C> func) {
        final E[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return CollectionUtils.empty();
        }
        return CollectionUtils.convertArray(enums, func);
    }
}
