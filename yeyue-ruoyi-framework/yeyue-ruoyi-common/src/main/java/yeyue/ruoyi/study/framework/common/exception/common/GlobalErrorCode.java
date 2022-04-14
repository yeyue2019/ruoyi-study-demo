package yeyue.ruoyi.study.framework.common.exception.common;

import lombok.*;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.core.ErrorCode;

/**
 * 全局错误码枚举
 *
 * @author yeyue
 * @date 2022-04-08 21:53:20
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    // TODO: 2022/4/10 因为需要将错误码在Swagger输出，所以全局错误码统一进行规定，存在错误码子类时再额外考虑其处理场景

    SUCCESS(CommonConstants.CODE_SUCCESS_STR, "success"),

    BAD_REQUEST("400", "请求参数存在错误"),
    NOT_FOUND("404", "请求路径不存在"),
    METHOD_NOT_ALLOWED("405", "不被支持的请求方法"),

    INTERNAL_SERVER_ERROR("500", "服务器异常"),

    SQL_EXECUTE_BAD("599", "SQL执行错误"),
    IO_EXCEPTION("600", "IO操作异常"),
    ENUM_VALUE_CANNOT_DEFAULT("601", "条件不在枚举筛选范围内"),
    ENCODE_NOT_SUPPORTED("602", "系统不支持的编码格式"),

    REDIS_CLIENT_COMMAND_FAIL("701", "Redis操作行为异常"),
    ;

    private final String code;
    private final String msg;
}
