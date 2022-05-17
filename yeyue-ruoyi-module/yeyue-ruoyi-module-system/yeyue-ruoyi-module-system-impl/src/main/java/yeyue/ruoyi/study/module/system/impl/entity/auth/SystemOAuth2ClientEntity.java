package yeyue.ruoyi.study.module.system.impl.entity.auth;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.mybatis.core.entity.MyBatisEntity;
import yeyue.ruoyi.study.module.system.api.enums.OAuth2GrantTypeEnum;

import java.util.List;

import static yeyue.ruoyi.study.module.system.impl.constants.SystemTableConstants.SYSTEM_AUTH_CLIENT;

/**
 * OAuth2.0 客户端
 *
 * @author yeyue
 * @date 2022-05-16 16:24:05
 */
@Data
@TableName(value = SYSTEM_AUTH_CLIENT, autoResultMap = true)
public class SystemOAuth2ClientEntity extends MyBatisEntity {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户端编号
     */
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
     * logo
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 访问令牌有效期
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 可重定向的 URI 地址
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> redirectUris;

    /**
     * 是否自动授权
     */
    private Boolean autoApprove;


    /**
     * 授权类型（模式）
     * <p>
     * 枚举 {@link OAuth2GrantTypeEnum}
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorizedGrantTypes;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 权限
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authorities;

    /**
     * 资源
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> resourceIds;

    /**
     * 附加信息，JSON 格式
     */
    private String additionalInformation;
}
