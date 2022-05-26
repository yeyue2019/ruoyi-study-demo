package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-17 12:31:40
 */
@Data
@ApiModel(description = "创建访问令牌")
public class SystemOAuth2AccessTokenCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @NotEmpty(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "授权范围")
    private List<String> scopes;

    @ApiModelProperty(value = "访问令牌的有效期")
    private Integer accessTokenValiditySeconds;

    @ApiModelProperty(value = "刷新令牌的有效期")
    private Integer refreshTokenValiditySeconds;
}
