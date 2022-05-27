package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-27 17:32:19
 */
@Data
@ApiModel(description = "修改用户状态")
public class SystemUserStatusUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
