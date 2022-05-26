package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * 岗位状态修改
 *
 * @author yeyue
 * @date 2022-05-12 14:25:40
 */
@Data
@ApiModel(description = "岗位状态修改")
public class SystemPostStatusUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "岗位id集合")
    @NotNull(message = "岗位信息不能为空")
    @Size(min = 1, max = 100, message = "修改岗位的数量介于1-100之间")
    private Set<Long> ids;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
