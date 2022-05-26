package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author yeyue
 * @date 2022-05-26 13:16:27
 */
@Data
@ApiModel(description = "客户端校验")
public class SystemOAuth2ClientValidateReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @ApiModelProperty(value = "密钥")
    private String secret;

    @ApiModelProperty(value = "授权类型")
    private String authorizedGrantType;

    @ApiModelProperty(value = "授权范围")
    private Collection<String> scopes;

    @ApiModelProperty(value = "重定向地址")
    private String redirectUri;
}
