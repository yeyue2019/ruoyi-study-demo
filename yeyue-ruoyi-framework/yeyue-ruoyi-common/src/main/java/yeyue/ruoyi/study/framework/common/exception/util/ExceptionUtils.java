package yeyue.ruoyi.study.framework.common.exception.util;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.common.pojo.core.*;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

/**
 * 获取异常信息的工具类
 *
 * @author yeyue
 * @date 2022-04-14 11:05:19
 */
@Slf4j
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

    /**
     * 日志输出控制器
     *
     * @param ex        错误实例
     * @param errorCode 转化生成的错误码
     * @param errMsg    转化生成的错误提示
     * @param logged    是否输出到日志
     * @param logMsg    输出的日志内容
     * @return 结果
     */
    public static CommonResult<?> output(Throwable ex, ErrorCode errorCode, String errMsg, boolean logged, String logMsg) {
        if (logged) {
            log.warn("[Exception.output]:{}", logMsg == null ? getMessage(ex) : logMsg);
        }
        if (errMsg == null) {
            return CommonResult.error(errorCode);
        }
        return CommonResult.error(errorCode.getCode(), errMsg);
    }
}
