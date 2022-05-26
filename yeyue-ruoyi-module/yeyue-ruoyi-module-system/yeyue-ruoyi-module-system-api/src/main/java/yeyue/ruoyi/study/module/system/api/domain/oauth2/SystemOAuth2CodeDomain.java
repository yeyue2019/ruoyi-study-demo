package yeyue.ruoyi.study.module.system.api.domain.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiresTime;

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "授权范围")
    private List<String> scopes;

    @ApiModelProperty(value = "重定向地址")
    private String redirectUri;

    @ApiModelProperty(value = "状态")
    private String state;
}
