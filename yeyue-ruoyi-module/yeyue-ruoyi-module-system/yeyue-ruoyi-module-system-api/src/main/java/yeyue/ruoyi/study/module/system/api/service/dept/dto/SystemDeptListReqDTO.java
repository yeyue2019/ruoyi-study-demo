package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-23 17:38:08
 */
@Data
@ApiModel(description = "部门查询")
public class SystemDeptListReqDTO implements Serializable {

    @ApiModelProperty(value = "上级部门Id")
    private Long parentId;

    @ApiModelProperty(value = "部门状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;

    @ApiModelProperty(value = "是否递归子部门")
    private Boolean recursive;
}
