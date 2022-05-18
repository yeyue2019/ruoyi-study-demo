package yeyue.ruoyi.study.module.system.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;

/**
 * OAuth2 授权类型（模式）的枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:51:09
 */
@AllArgsConstructor
@Getter
public enum OAuth2GrantTypeEnum implements StringEnum {

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

    @EnumValue
    private final String grantType;


    @Override
    public String[] array() {
        return new String[]{PASSWORD.grantType, AUTHORIZATION_CODE.grantType, IMPLICIT.grantType, CLIENT_CREDENTIALS.grantType, REFRESH_TOKEN.grantType};
    }
}
