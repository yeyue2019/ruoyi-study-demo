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
    DEPT_NOT_FOUND("10004", "当前部门不存在"),
    DEPT_PARENT_ERROR("10005", "不能设置自己为父部门"),
    DEPT_PARENT_NOT_EXITS("10006", "父级部门不存在"),
    DEPT_NOT_ENABLE("10007", "部门不处于开启状态，不允许选择"),
    DEPT_PARENT_IS_CHILD("10008", "不能设置自己的下级部门为父部门"),
    DEPT_NAME_DUPLICATE("10009","已经存在该名字的部门"),
    DEPT_EXITS_CHILDREN("10010", "存在子部门，无法删除"),

    OAUTH2_CLIENT_EXIST("10101", "客户端编号已存在"),
    OAUTH2_CLIENT_NOT_EXISTS("10102", "客户端不存在"),
    OAUTH2_CLIENT_STATUS_DISABLE("10103", "客户端被禁用"),
    OAUTH2_CODE_EXIST("10104", "授权码已存在"),
    OAUTH2_CODE_NOT_EXISTS("10105", "授权码不存在"),
    OAUTH2_CODE_EXPIRES("10106", "授权码已过期"),
    OAUTH2_CODE_DISABLE("10107", "授权码已不是最新"),
    OAUTH2_REFRESH_TOKEN_NOT_EXISTS("10108", "无效的刷新令牌"),
    OAUTH2_REFRESH_TOKEN_UNSUPPORTED_CLIENT("10109", "客户端与令牌不匹配"),
    OAUTH2_REFRESH_TOKEN_EXPIRES("10110", "刷新令牌已过期"),
    OAUTH2_ACCESS_TOKEN_NOT_EXISTS("10111", "无效的访问令牌"),
    OAUTH2_ACCESS_TOKEN_EXPIRES("10112", "访问令牌已过期"),
    ;

    private final String code;
    private final String msg;
}
