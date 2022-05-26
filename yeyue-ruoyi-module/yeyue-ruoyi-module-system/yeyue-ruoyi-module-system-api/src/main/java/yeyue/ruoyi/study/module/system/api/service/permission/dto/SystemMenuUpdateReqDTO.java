package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-23 15:28:56
 */
@Data
@ApiModel(description = "更新菜单")
public class SystemMenuUpdateReqDTO extends SystemMenuCreateReqDTO {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1024")
    @NotNull(message = "菜单编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
