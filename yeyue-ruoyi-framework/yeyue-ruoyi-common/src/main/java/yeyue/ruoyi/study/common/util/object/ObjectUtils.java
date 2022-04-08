package yeyue.ruoyi.study.common.util.object;

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
}
