package yeyue.ruoyi.study.module.system.api.domain.auth;

import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-17 12:46:28
 */
@Data
@ApiModel(description = "OAuth2授权码")
public class SystemOAuth2CodeDomain implements Serializable {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "授权码")
    private String code;

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiresTime;

    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;
}
