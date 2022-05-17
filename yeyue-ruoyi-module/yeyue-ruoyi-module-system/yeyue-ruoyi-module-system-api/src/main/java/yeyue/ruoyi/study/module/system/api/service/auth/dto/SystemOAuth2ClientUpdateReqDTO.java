package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 更新 OAuth2 客户端请求
 *
 * @author yeyue
 * @date 2022-05-17 09:41:04
 */
@Data
@ApiModel(description = "修改OAuth2客户端")
public class SystemOAuth2ClientUpdateReqDTO extends SystemOAuth2ClientCreateReqDTO {

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;
}
