package yeyue.ruoyi.study.framework.common.exception;

import lombok.Data;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;
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

    public ServiceException() {}

    public ServiceException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public ServiceException(ErrorCode errorCode, Throwable throwable) {
        super(throwable);
        ErrorCode.assertError(code);
        this.code = errorCode.getCode();
        this.msg = throwable.getMessage();
    }

    public ServiceException(String code, String message) {
        super(ObjectUtils.indexJoin(code, message));
        ErrorCode.assertError(code);
        this.code = code;
        this.msg = message;
    }
}
