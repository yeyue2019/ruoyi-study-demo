package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-30 14:25:25
 */
@Data
@ApiModel(description = "访问令牌请求")
public class SystemOAuth2TokenReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    @NotEmpty(message = "客户端Id不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥")
    @NotEmpty(message = "客户端密钥不能为空")
    private String clientSecret;

    @ApiModelProperty(value = "访问令牌")
    @NotEmpty(message = "访问令牌不能为空")
    private String accessToken;
}
