package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import java.io.Serializable;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-24 14:22:58
 */
@Data
@ApiModel(description = "角色列表")
public class SystemRoleListReqDTO implements Serializable {

    @ApiModelProperty(value = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;

    @ApiModelProperty(value = "特定的角色id")
    private Set<Long> ids;
}
