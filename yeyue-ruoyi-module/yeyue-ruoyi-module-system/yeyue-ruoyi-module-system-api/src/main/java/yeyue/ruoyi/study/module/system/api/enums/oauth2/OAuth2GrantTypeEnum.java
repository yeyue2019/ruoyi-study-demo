package yeyue.ruoyi.study.module.system.api.enums.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * OAuth2 授权类型（模式）的枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:51:09
 */
@AllArgsConstructor
@Getter
public enum OAuth2GrantTypeEnum implements EnumValuable<String> {

    /**
     * 密码模式
     */
    PASSWORD("password"),
    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("authorization_code"),
    /**
     * 简化模式
     */
    IMPLICIT("implicit"),
    /**
     * 客户端模式
     */
    CLIENT_CREDENTIALS("client_credentials"),
    /**
     * 刷新模式
     */
    REFRESH_TOKEN("refresh_token");

    private final String grantType;

    @Override
    public String get() {
        return this.grantType;
    }
}
