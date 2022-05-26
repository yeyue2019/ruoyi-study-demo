package yeyue.ruoyi.study.module.system.api.service.oauth2.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @author yeyue
 * @date 2022-05-26 16:37:39
 */
@Data
@ApiModel(description = "校验批准授权")
public class SystemOAuth2ApproveCheckReqDTO extends SystemOAuth2ApproveGetReqDTO {

    @ApiModelProperty(value = "本次校验权限范围")
    private Collection<String> scopes;
}
