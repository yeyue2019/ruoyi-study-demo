package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @author yeyue
 * @date 2022-05-26 16:51:48
 */
@Data
@ApiModel(description = "申请批准授权")
public class SystemOAuth2ApproveUpdateReqDTO extends SystemOAuth2ApproveGetReqDTO {

    @ApiModelProperty(value = "申请的权限")
    Collection<String> scopes;

    @ApiModelProperty(value = "同意授权的有效期")
    private Integer approveValiditySeconds;
}
