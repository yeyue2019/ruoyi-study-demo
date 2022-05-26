package yeyue.ruoyi.study.module.system.api.domain.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-26 16:21:37
 */
@Data
@ApiModel(description = "用户批准授权")
public class SystemOAuth2ApproveDomain implements Serializable {

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "客户端编号")
    private String clientId;

    @ApiModelProperty(value = "授权范围")
    private String scope;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiresTime;
}
