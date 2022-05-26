package yeyue.ruoyi.study.module.system.api.domain.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-17 09:54:16
 */
@Data
@ApiModel(description = "OAuth2客户端")
public class SystemOAuth2ClientDomain implements Serializable {

    @ApiModelProperty(value = "客户端编号", required = true)
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true)
    private String secret;

    @ApiModelProperty(value = "应用名", required = true)
    private String name;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @ApiModelProperty(value = "授权码有效期")
    private Integer codeValiditySeconds;

    @ApiModelProperty(value = "访问令牌的有效期", required = true)
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true)
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "可重定向的 URI 地址")
    private List<String> redirectUris;

    @ApiModelProperty(value = "授权类型（模式）")
    private List<String> authorizedGrantTypes;

    @ApiModelProperty(value = "授权范围")
    private List<String> scopes;

    @ApiModelProperty(value = "自动授权的 Scope")
    private List<String> autoApproveScopes;

    @ApiModelProperty(value = "附加信息，JSON 格式")
    private String additionalInformation;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;
}
