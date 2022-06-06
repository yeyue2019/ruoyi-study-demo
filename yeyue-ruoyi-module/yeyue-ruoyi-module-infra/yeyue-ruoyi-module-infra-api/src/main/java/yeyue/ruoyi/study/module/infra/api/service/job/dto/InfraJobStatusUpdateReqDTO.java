package yeyue.ruoyi.study.module.infra.api.service.job.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-06-06 16:55:36
 */
@Data
@ApiModel(description = "任务状态变更")
public class InfraJobStatusUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;
}
