package yeyue.ruoyi.study.module.system.impl.entity.oauth2;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;

import java.util.List;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_OAUTH2_CLIENT;

/**
 * OAuth2.0 客户端
 *
 * @author yeyue
 * @date 2022-05-16 16:24:05
 */
@Data
@TableName(value = SYSTEM_OAUTH2_CLIENT, autoResultMap = true)
public class SystemOAuth2ClientEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户端Id
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String clientId;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    // 客户端配置

    /**
     * 授权码有效期
     */
    private Integer codeValiditySeconds;

    /**
     * 访问令牌有效期
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 同意授权的有效期
     */
    private Integer approveValiditySeconds;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 可重定向的 URI 地址
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> redirectUris;

    /**
     * 授权类型（模式）
     * <p>
     * 枚举 {@link yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum}
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorizedGrantTypes;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 自动授权的 Scope
     * <p>
     * code 授权时，如果 scope 在这个范围内，则自动通过
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> autoApproveScopes;

    /**
     * 附加信息，JSON 格式
     */
    private String additionalInformation;
}
