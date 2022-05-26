package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

    @ApiModelProperty(value = "可重定向的 URI 地址", required = true, example = "https://www.iocoder.cn")
    @NotNull(message = "可重定向的 URI 地址不能为空")
    private List<@NotEmpty(message = "重定向的 URI 不能为空") @URL(message = "重定向的 URI 格式不正确") String> redirectUris;

    @ApiModelProperty(value = "授权类型", required = true, example = "password", notes = "参见 OAuth2GrantTypeEnum 枚举")
    @NotNull(message = "授权类型不能为空")
    private List<@NotEmpty(message = "授权类型不能为空") @InEnum(value = OAuth2GrantTypeEnum.class,
            message = "授权类型不在可选范围内") String> authorizedGrantTypes;

    @ApiModelProperty(value = "授权范围", example = "user_info")
    private List<String> scopes;

    @ApiModelProperty(value = "自动通过的授权范围", example = "user_info")
    private List<String> autoApproveScopes;

    @ApiModelProperty(value = "附加信息", example = "{yunai: true}")
    private String additionalInformation;
}
