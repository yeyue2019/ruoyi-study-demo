package yeyue.ruoyi.study.framework.common.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;

/**
 * 全局错误码枚举
 *
 * @author yeyue
 * @date 2022-04-08 21:53:20
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    SUCCESS(CODE_SUCCESS_STR, "success"),
    BAD_REQUEST("400", "请求参数存在错误"),
    UNAUTHORIZED("401", "账号未登录"),
    FORBIDDEN("403", "没有操作权限"),
    NOT_FOUND("404", "请求路径不存在"),
    METHOD_NOT_ALLOWED("405", "不被支持的请求方法"),
    LOCKED("423", "请求失败，请稍后重试"),
    TOO_MANY_REQUESTS("429", "请求过于频繁，请稍后重试"),
    INTERNAL_SERVER_ERROR("500", "服务器异常"),
    SQL_EXECUTE_BAD("601", "MYSQL执行异常"),
    IO_EXCEPTION("602", "IO操作异常"),
    REDIS_CLIENT_COMMAND_FAIL("603", "REDIS执行异常"),
    DATA_NOT_MATCH_ENUMS("701", "枚举匹配失败"),
    SYSTEM_CODE_SUPPORT_ERROR("702", "不支持的系统编码"),
    UNSUPPORTED_OPERATION("703", "暂不支持的操作");

    private final String code;
    private final String msg;
}
