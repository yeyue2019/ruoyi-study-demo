package yeyue.ruoyi.study.framework.common.exception.util;

import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

/**
 * 获取异常信息的工具类
 *
 * @author yeyue
 * @date 2022-04-14 11:05:19
 */
public abstract class ExceptionUtils {

    /**
     * 获取错误具体信息
     *
     * @param e 异常
     * @return 错误提示
     */
    public static String getMessage(Throwable e) {
        if (e == null) {
            return null;
        }
        return ObjectUtils.indexJoin(e.getClass().getSimpleName(), e.getMessage());
    }
}
