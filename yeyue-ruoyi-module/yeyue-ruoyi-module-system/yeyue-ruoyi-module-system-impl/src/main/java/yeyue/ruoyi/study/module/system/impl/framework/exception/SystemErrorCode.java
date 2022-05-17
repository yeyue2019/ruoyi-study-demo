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

    OAUTH2_CLIENT_EXIST("10101", "OAuth2客户端编号已存在"),
    OAUTH2_CLIENT_NOT_EXISTS("10102", "OAuth2客户端不存在"),
    OAUTH2_CLIENT_STATUS_DISABLE("10103", "OAuth2客户端被禁用"),
    OAUTH2_CODE_EXIST("10104", "OAuth2授权码已存在"),
    OAUTH2_CODE_NOT_EXISTS("10105", "OAuth2授权码不存在"),
    OAUTH2_CODE_EXPIRES("10106", "OAuth2授权码已过期"),
    OAUTH2_CODE_DISABLE("10107", "OAuth2授权码已不是最新"),
    ;

    private final String code;
    private final String msg;
}
