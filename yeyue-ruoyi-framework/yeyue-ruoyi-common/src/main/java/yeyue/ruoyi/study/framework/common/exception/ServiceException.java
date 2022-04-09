package yeyue.ruoyi.study.framework.common.exception;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.core.ErrorCode;

/**
 * 业务异常
 *
 * @author yeyue
 * @date 2022-04-08 20:08:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException implements ErrorCode {

    /**
     * 业务错误码
     */
    private String code = StringUtils.EMPTY;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return getMessage();
    }

    public ServiceException() {
    }

    public ServiceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public ServiceException(String code, String message) {
        super(message);
        ErrorCode.assertError(code);
        this.code = code;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        ErrorCode.assertError(code);
        this.code = String.valueOf(code);
    }
}
