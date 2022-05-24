package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-05-24 15:52:27
 */
@Data
@ApiModel(description = "角色分页查询")
public class SystemRolePageReqDTO extends PageParam {

    @ApiModelProperty(value = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;

    @ApiModelProperty(value = "名称模糊查询")
    private String name;

    @ApiModelProperty(value = "code匹配")
    private String code;
}
