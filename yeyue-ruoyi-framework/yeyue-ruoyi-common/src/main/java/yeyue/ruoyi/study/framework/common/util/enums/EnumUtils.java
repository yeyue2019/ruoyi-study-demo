package yeyue.ruoyi.study.framework.common.util.enums;

import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

import java.util.*;
import java.util.function.Function;

/**
 * 枚举操作工具
 *
 * @author yeyue
 * @date 2022-05-19 15:30:28
 */
public abstract class EnumUtils {

    /**
     * 枚举条件校验
     *
     * @param clazz   枚举类型
     * @param func    枚举获取比较值的方法
     * @param compare 比较对象
     * @param <E>     枚举类型
     * @param <C>     比较类型
     * @return 是否符合
     */
    public static <E extends Enum<E>, C> boolean valid(Class<E> clazz, Function<E, C> func, C compare) {
        final E[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return false;
        }
        return Arrays.stream(enums).anyMatch(e -> Objects.equals(func.apply(e), compare));
    }

    /**
     * 根据条件获取枚举集合
     *
     * @param clazz 枚举类型
     * @param func  获取函数
     * @param <E>   枚举类型
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

    /**
     * 枚举和对应值的比较
     *
     * @param source  枚举对象
     * @param func    转换函数
     * @param compare 被比较的对象
     * @param <E>     枚举类型
     * @param <C>     对象类型
     * @return 比较结果
     */
    public static <E extends Enum<E>, C> boolean equals(E source, Function<E, C> func, C compare) {
        if (compare == null) {
            return false;
        }
        C target = func.apply(source);
        return Objects.equals(compare, target);
    }

    public static <E extends Enum<E>, C> boolean notEquals(E source, Function<E, C> func, C compare) {
        return !equals(source, func, compare);
    }
}