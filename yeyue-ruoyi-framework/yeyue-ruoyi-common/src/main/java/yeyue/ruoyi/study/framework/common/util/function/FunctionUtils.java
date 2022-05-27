package yeyue.ruoyi.study.framework.common.util.function;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author yeyue
 * @date 2022-05-27 14:31:37
 */
public abstract class FunctionUtils {

    /**
     * 返回自身的函数
     *
     * @param <T> 类型
     * @return 函数
     */
    public static <T> Function<T, T> selfFunc() {
        return source -> source;
    }

    /**
     * merge取前面
     *
     * @param <T> 类型
     * @return 函数
     */
    public static <T> BinaryOperator<T> mergeStart() {
        return (v1, v2) -> v1;
    }

    /**
     * merge取后面
     *
     * @param <T> 类型
     * @return 函数
     */
    public static <T> BinaryOperator<T> mergeEnd() {
        return (v1, v2) -> v2;
    }
}
