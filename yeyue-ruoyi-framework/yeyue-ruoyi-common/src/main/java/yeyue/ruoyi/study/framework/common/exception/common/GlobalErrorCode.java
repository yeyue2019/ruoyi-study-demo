package yeyue.ruoyi.study.framework.common.exception.common;

import lombok.*;
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

    REDIS_CLIENT_COMMAND_FAIL("601", "Redis操作行为异常"),

    ENUM_VALUE_CANNOT_DEFAULT("701", "条件不在枚举筛选范围内"),


    ;

    private final String code;
    private final String msg;
}
