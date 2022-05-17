package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import yeyue.ruoyi.study.framework.common.validation.annotation.InStringEnum;
import yeyue.ruoyi.study.module.system.api.enums.OAuth2GrantTypeEnum;

import javax.validation.constraints.*;
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
    @NotNull(message = "客户端编号不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥", required = true)
    @NotNull(message = "客户端密钥不能为空")
    private String secret;

    @ApiModelProperty(value = "应用名", required = true)
    @NotNull(message = "应用名不能为空")
    private String name;

    @ApiModelProperty(value = "应用图标", required = true)
    @NotNull(message = "应用图标不能为空")
    @URL(message = "应用图标的地址不正确")
    private String logo;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @ApiModelProperty(value = "访问令牌的有效期", required = true)
    @NotNull(message = "访问令牌的有效期不能为空")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期", required = true)
    @NotNull(message = "刷新令牌的有效期不能为空")
    private Integer refreshTokenValiditySeconds;

    @ApiModelProperty(value = "可重定向的 URI 地址", required = true)
    @NotNull(message = "可重定向的 URI 地址不能为空")
    private List<@NotEmpty(message = "重定向的 URI 不能为空")
    @URL(message = "重定向的 URI 格式不正确") String> redirectUris;

    @ApiModelProperty(value = "是否自动授权", required = true, example = "true")
    @NotNull(message = "是否自动授权不能为空")
    private Boolean autoApprove;

    @ApiModelProperty(value = "授权类型", required = true, example = "password", notes = "参见 OAuth2GrantTypeEnum 枚举")
    @NotNull(message = "授权类型不能为空")
    private List<@NotEmpty(message = "授权类型不能为空")
    @InStringEnum(value = OAuth2GrantTypeEnum.class, message = "授权类型不在可选范围内") String> authorizedGrantTypes;

    @ApiModelProperty(value = "授权范围", example = "user_info")
    private List<String> scopes;

    @ApiModelProperty(value = "权限", example = "system:user:query")
    private List<String> authorities;

    @ApiModelProperty(value = "资源", example = "1024")
    private List<String> resourceIds;

    @ApiModelProperty(value = "附加信息", example = "{yunai: true}")
    private String additionalInformation;
}
