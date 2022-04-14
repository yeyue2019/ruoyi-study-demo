package yeyue.ruoyi.study.framework.common.exception;

import lombok.Data;
import yeyue.ruoyi.study.framework.common.core.ErrorCode;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

/**
 * 业务异常
 *
 * @author yeyue
 * @date 2022-04-08 20:08:08
 */
@Data
public class ServiceException extends RuntimeException implements ErrorCode {

    /**
     * 业务错误码
     */
    private String code;
    private String msg;

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

    public ServiceException(ErrorCode errorCode, Throwable throwable) {
        super(ObjectUtils.indexJoin(errorCode.getCode(), throwable.getMessage()), throwable);
        ErrorCode.assertError(code);
        this.code = errorCode.getCode();
    }

    private ServiceException(String code, String message) {
        super(ObjectUtils.indexJoin(code, message));
        ErrorCode.assertError(code);
        this.code = code;
    }
}
