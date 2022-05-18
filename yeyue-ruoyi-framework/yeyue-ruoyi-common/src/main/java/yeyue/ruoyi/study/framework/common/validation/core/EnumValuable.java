package yeyue.ruoyi.study.framework.common.validation.core;

/**
 * 可以实例为枚举的接口
 *
 * @author yeyue
 * @date 2022-04-18 21:14:45
 */
public interface EnumValuable<T> {

    /**
     * 数组
     *
     * @return 数组
     */
    T[] enums();
}
