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

    /* 岗位 */

    POST_NAME_DUPLICATE("010101", "岗位名称已存在"),
    POST_CODE_DUPLICATE("010102", "岗位编码已存在"),
    POST_NOT_FOUND("010103", "当前岗位不存在"),
    POST_NOT_ENABLE("010104", "岗位不处于开启状态，不允许选择"),
    /* 部门 */

    DEPT_NOT_FOUND("010201", "当前部门不存在"),
    DEPT_PARENT_NOT_EXITS("010202", "父级部门不存在"),
    DEPT_NOT_ENABLE("010203", "部门不处于开启状态，不允许选择"),
    DEPT_NAME_DUPLICATE("010204", "已经存在该名字的部门"),
    DEPT_EXITS_CHILDREN("010205", "存在子部门，无法删除"),

    /* 菜单  */

    MENU_PARENT_NOT_EXISTS("010301", "父菜单不存在"),
    MENU_NOT_EXISTS("010302", "菜单不存在"),
    MENU_PARENT_NOT_DIR_OR_MENU("010303", "父菜单的类型必须是目录或者菜单"),
    MENU_NAME_DUPLICATE("010304", "已经存在该名字的菜单"),
    MENU_EXISTS_CHILDREN("010305", "存在子菜单，无法删除"),

    /* 角色 */

    ROLE_CODE_DUPLICATE("010401", "角色编码已存在"),
    ROLE_NAME_DUPLICATE("010402", "角色名称已存在"),
    ROLE_NOT_EXISTS("010403", "角色不存在"),
    ROLE_CAN_NOT_UPDATE_CODE_VALUE_SUPER_ADMIN("010405", "不能操作超级管理员角色"),

    /* 用户 */

    USER_NOT_EXISTS("010501", "用户不存在"),
    USER_USERNAME_EXISTS("010502", "用户账号已经存在"),
    USER_MOBILE_EXISTS("010503", "手机号已经存在"),
    USER_EMAIL_EXISTS("010504", "邮箱已经存在"),
    USER_PASSWORD_FAILED("010505", "用户密码校验失败"),

    /* 登录 */

    AUTH_LOGIN_BAD_CREDENTIALS("010601", "登录失败，账号密码不正确"),
    AUTH_LOGIN_USER_DISABLED("010602", "登录失败，账号被禁用"),

    AUTH_LOGIN_CAPTCHA_NOT_FOUND("010603", "登录失败，验证码不存在或已过期"),
    AUTH_LOGIN_CAPTCHA_CODE_ERROR("010604", "验证码错误"),

    /* OAuth2 */

    OAUTH2_CLIENT_EXIST("010901", "客户端编号已存在"),
    OAUTH2_CLIENT_NOT_EXISTS("010902", "客户端不存在"),
    OAUTH2_CLIENT_STATUS_DISABLE("010903", "客户端被禁用"),
    OAUTH2_CLIENT_CLIENT_SECRET_ERROR("010904", "客户端密钥错误"),
    OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS("010905", "不支持的授权类型"),
    OAUTH2_CLIENT_SCOPE_OVER("010906", "授权范围错误"),
    OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH("010907", "回调地址不再范围内"),
    OAUTH2_CODE_EXIST("010908", "授权码已存在"),
    OAUTH2_CODE_NOT_EXISTS("010909", "授权码不存在"),
    OAUTH2_CODE_EXPIRES("010910", "授权码已过期"),
    OAUTH2_REFRESH_TOKEN_NOT_EXISTS("010911", "无效的刷新令牌"),
    OAUTH2_REFRESH_TOKEN_UNSUPPORTED_CLIENT("010912", "客户端与令牌不匹配"),
    OAUTH2_REFRESH_TOKEN_EXPIRES("010913", "刷新令牌已过期"),
    OAUTH2_ACCESS_TOKEN_NOT_EXISTS("010914", "无效的访问令牌"),
    OAUTH2_ACCESS_TOKEN_EXPIRES("010915", "访问令牌已过期"),
    OAUTH2_GRANT_CLIENT_ID_MISMATCH("010916", "客户端Id不匹配"),
    OAUTH2_GRANT_SCOPE_HAS_NOT_APPROVE("010917", "存在需要授权的权限"),
    ;

    private final String code;
    private final String msg;
}
