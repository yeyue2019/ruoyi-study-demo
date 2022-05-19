package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 创建 OAuth2 客户端请求
 *
 * @author yeyue
 * @date 2022-05-17 09:19:22
 */
@Data
@ApiModel(description = "OAuth2客户端创建")
public class SystemOAuth2ClientCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端编号", required = true)
    @NotEmpty(message = "客户端编号不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true)
    @NotEmpty(message = "客户端密钥不能为空")
    private String secret;

    @ApiModelProperty(value = "应用名", required = true)
    @NotEmpty(message = "应用名不能为空")
    private String name;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @ApiModelProperty(value = "授权码有效期")
    private Integer codeValiditySeconds;

    @ApiModelProperty(value = "访问令牌的有效期", required = true)
    @NotNull(message = "访问令牌的有效期不能为空")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true)
    @NotNull(message = "刷新令牌的有效期不能为空")
    private Integer refreshTokenValiditySeconds;
}
