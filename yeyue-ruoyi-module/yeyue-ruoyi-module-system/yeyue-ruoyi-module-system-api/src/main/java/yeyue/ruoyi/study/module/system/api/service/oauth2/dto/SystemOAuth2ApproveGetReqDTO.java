package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-26 16:26:51
 */
@Data
@ApiModel(description = "获取用户批准的授权")
public class SystemOAuth2ApproveGetReqDTO implements Serializable {

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

    @ApiModelProperty(value = "自动授权的 Scope")
    private List<String> autoApproveScopes;
}
