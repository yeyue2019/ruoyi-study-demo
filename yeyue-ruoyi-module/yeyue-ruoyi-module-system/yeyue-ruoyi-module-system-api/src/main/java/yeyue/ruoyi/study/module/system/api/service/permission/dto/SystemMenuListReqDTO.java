package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import io.swagger.annotations.*;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-05-23 17:48:45
 */
@Data
@ApiModel(description = "菜单列表查询")
public class SystemMenuListReqDTO implements Serializable {

    @ApiModelProperty(value = "菜单状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
