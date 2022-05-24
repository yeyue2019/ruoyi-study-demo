package yeyue.ruoyi.study.module.system.impl.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
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

    POST_NAME_DUPLICATE("010001", "岗位名称已存在"),
    POST_CODE_DUPLICATE("010002", "岗位编码已存在"),
    POST_NOT_FOUND("010003", "当前岗位不存在"),
    DEPT_NOT_FOUND("010004", "当前部门不存在"),
    DEPT_PARENT_NOT_EXITS("010005", "父级部门不存在"),
    DEPT_NOT_ENABLE("010006", "部门不处于开启状态，不允许选择"),
    DEPT_NAME_DUPLICATE("010007", "已经存在该名字的部门"),
    DEPT_EXITS_CHILDREN("010008", "存在子部门，无法删除"),
    MENU_PARENT_NOT_EXISTS("010101", "父菜单不存在"),
    MENU_NOT_EXISTS("010102", "菜单不存在"),
    MENU_PARENT_NOT_DIR_OR_MENU("010103", "父菜单的类型必须是目录或者菜单"),
    MENU_NAME_DUPLICATE("010104", "已经存在该名字的菜单"),
    MENU_EXISTS_CHILDREN("010105", "存在子菜单，无法删除"),
    ROLE_CODE_DUPLICATE("010106", "角色编码已存在"),
    ROLE_NAME_DUPLICATE("010107", "角色名称已存在"),
    ROLE_NOT_EXISTS("010108", "角色不存在"),

    OAUTH2_CLIENT_EXIST("10901", "客户端编号已存在"),
    OAUTH2_CLIENT_NOT_EXISTS("10902", "客户端不存在"),
    OAUTH2_CLIENT_STATUS_DISABLE("10903", "客户端被禁用"),
    OAUTH2_CODE_EXIST("10904", "授权码已存在"),
    OAUTH2_CODE_NOT_EXISTS("10905", "授权码不存在"),
    OAUTH2_CODE_EXPIRES("10906", "授权码已过期"),
    OAUTH2_CODE_DISABLE("10907", "授权码已不是最新"),
    OAUTH2_REFRESH_TOKEN_NOT_EXISTS("10908", "无效的刷新令牌"),
    OAUTH2_REFRESH_TOKEN_UNSUPPORTED_CLIENT("10909", "客户端与令牌不匹配"),
    OAUTH2_REFRESH_TOKEN_EXPIRES("10910", "刷新令牌已过期"),
    OAUTH2_ACCESS_TOKEN_NOT_EXISTS("10911", "无效的访问令牌"),
    OAUTH2_ACCESS_TOKEN_EXPIRES("10912", "访问令牌已过期"),
    ;

    private final String code;
    private final String msg;
}
