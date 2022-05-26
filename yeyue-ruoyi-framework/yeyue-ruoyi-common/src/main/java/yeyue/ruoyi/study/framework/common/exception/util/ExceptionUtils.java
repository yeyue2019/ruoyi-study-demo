package yeyue.ruoyi.study.framework.common.exception.util;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

/**
 * 获取异常信息的工具类
 *
 * @author yeyue
 * @date 2022-04-14 11:05:19
 */
@Slf4j
public abstract class ExceptionUtils {
    public static final String OUT_PLACE_HOLDER = "{}";

    public static ServiceException exception(ErrorCode errorCode, Object... params) {
        String message = format(errorCode.getCode(), errorCode.getMsg(), params);
        return new ServiceException(errorCode.getCode(), message);
    }

    public static ServiceException exception(String code, String messagePattern, Object... params) {
        String message = format(code, messagePattern, params);
        return new ServiceException(code, message);
    }

    public static <T> CommonResult<T> handle(ErrorCode code, String messagePattern, Object... params) {
        if (StringUtils.isEmpty(messagePattern)) {
            return CommonResult.error(code);
        }
        return CommonResult.error(code.getCode(), format(code.getCode(), messagePattern, params));
    }

    /**
     * 将错误编号对应的消息使用 params 进行格式化。
     *
     * @param code 错误编号
     * @param messagePattern 消息模版
     * @param params 参数
     * @return 格式化后的提示
     */
    public static String format(String code, String messagePattern, Object... params) {
        if (CollectionUtils.isNull(params)) {
            return messagePattern;
        }
        StringBuilder sb = new StringBuilder(messagePattern.length() + 100);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf(OUT_PLACE_HOLDER, i);
            if (j == -1) {
                log.error("[format][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sb.append(messagePattern.substring(i));
                    return sb.toString();
                }
            } else {
                sb.append(messagePattern, i, j);
                sb.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf(OUT_PLACE_HOLDER, i) != -1) {
            log.error("[format][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        sb.append(messagePattern.substring(i));
        return sb.toString();
    }

}
