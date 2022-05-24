package yeyue.ruoyi.study.framework.common.validation.core;

/**
 * 可以实例为枚举的接口
 *
 * @author yeyue
 * @date 2022-04-18 21:14:45
 */
@FunctionalInterface
public interface EnumValuable<T> {

    /**
     * 转换为比较的参数
     *
     * @return 比较的参数
     */
    T get();
}
