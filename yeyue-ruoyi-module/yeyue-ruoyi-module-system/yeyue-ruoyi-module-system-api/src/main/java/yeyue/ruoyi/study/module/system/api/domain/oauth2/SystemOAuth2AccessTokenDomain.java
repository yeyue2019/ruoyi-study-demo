package yeyue.ruoyi.study.module.system.api.domain.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-17 12:29:00
 */
@Data
@ApiModel(description = "OAuth2访问令牌")
public class SystemOAuth2AccessTokenDomain implements Serializable {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiresTime;
}
