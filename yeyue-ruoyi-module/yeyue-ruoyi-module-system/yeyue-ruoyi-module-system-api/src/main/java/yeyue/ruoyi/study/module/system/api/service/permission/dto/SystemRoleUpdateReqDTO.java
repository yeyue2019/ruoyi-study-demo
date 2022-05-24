package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-05-24 10:58:10
 */
@Data
@ApiModel(description = "修改系统角色")
public class SystemRoleUpdateReqDTO extends SystemRoleCreateReqDTO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1024")
    @NotNull(message = "角色编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
