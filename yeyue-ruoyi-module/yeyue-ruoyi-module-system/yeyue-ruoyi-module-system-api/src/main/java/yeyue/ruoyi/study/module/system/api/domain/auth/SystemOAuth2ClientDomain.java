package yeyue.ruoyi.study.module.system.api.domain.auth;

import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;
}
