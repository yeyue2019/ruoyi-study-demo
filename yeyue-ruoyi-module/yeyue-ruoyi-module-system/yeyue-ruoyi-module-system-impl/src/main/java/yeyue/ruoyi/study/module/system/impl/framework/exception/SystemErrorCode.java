package yeyue.ruoyi.study.module.system.impl.framework.exception;

import lombok.*;
import yeyue.ruoyi.study.framework.common.pojo.core.ErrorCode;

/**
 * 系统错误码枚举
 *
 * @author yeyue
 * @date 2022-04-28 17:14:05
 */
@Getter
@AllArgsConstructor
public enum SystemErrorCode implements ErrorCode {

    POST_NAME_DUPLICATE("10001", "岗位名称已存在"),
    POST_CODE_DUPLICATE("10002", "岗位编码已存在"),
    POST_NOT_FOUND("10003", "当前岗位不存在"),


    ;

    private final String code;
    private final String msg;
}
