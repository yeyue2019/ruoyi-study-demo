package yeyue.ruoyi.study.framework.common.util.object;

import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.constants.StringConstants;

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
    public static <T, R> R funcOrDefault(T source, R defaultResult, Function<T, R> function) {
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
}
