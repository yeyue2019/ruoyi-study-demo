package yeyue.ruoyi.study.module.system.api.domain.auth;

import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-17 09:54:16
 */
@Data
@ApiModel(description = "OAuth2 客户端")
public class SystemOAuth2ClientDomain implements Serializable {

    @ApiModelProperty(value = "客户端编号", required = true)
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true)
    private String secret;

    @ApiModelProperty(value = "应用名", required = true)
    private String name;

    @ApiModelProperty(value = "应用图标", required = true)
    private String logo;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @ApiModelProperty(value = "访问令牌的有效期", required = true)
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true)
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "可重定向的 URI 地址", required = true)
    private List<String> redirectUris;

    @ApiModelProperty(value = "是否自动授权", required = true, example = "true")
    private Boolean autoApprove;

    @ApiModelProperty(value = "授权类型", required = true, example = "password", notes = "参见 OAuth2GrantTypeEnum 枚举")
    private List<String> authorizedGrantTypes;

    @ApiModelProperty(value = "授权范围", example = "user_info")
    private List<String> scopes;

    @ApiModelProperty(value = "权限", example = "system:user:query")
    private List<String> authorities;

    @ApiModelProperty(value = "资源", example = "1024")
    private List<String> resourceIds;

    @ApiModelProperty(value = "附加信息", example = "{yunai: true}")
    private String additionalInformation;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;
}
