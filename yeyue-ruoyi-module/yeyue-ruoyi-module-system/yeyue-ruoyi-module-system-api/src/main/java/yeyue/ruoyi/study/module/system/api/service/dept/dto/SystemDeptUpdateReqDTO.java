package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-05-17 21:30:47
 */
@Data
@ApiModel(description = "部门信息更新")
public class SystemDeptUpdateReqDTO extends SystemDeptCreateReqDTO {

    @ApiModelProperty(value = "部门编号", required = true, example = "1024")
    @NotNull(message = "部门编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
