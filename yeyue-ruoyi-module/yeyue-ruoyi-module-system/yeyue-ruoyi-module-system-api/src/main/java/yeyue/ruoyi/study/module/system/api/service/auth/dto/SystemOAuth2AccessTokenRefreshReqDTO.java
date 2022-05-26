package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-17 20:05:05
 */
@Data
@ApiModel(description = "刷新访问令牌")
public class SystemOAuth2AccessTokenRefreshReqDTO implements Serializable {

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "客户端Id")
    private String clientId;
}
