package yeyue.ruoyi.study.module.system.api.service.auth.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

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

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
