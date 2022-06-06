package yeyue.ruoyi.study.module.infra.api.service.job.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.validation.annotation.Cron;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yeyue
 * @date 2022-06-06 16:28:40
 */
@Data
@ApiModel(description = "定时更新")
public class InfraJobUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "任务编号", required = true, example = "1024")
    @NotNull(message = "任务编号不能为空")
    private Long id;

    @ApiModelProperty(value = "任务名称", required = true, example = "测试任务")
    @NotNull(message = "任务名称不能为空")
    private String name;

    @ApiModelProperty(value = "处理器的参数", example = "yudao")
    private String handlerParam;

    @ApiModelProperty(value = "CRON 表达式", required = true, example = "0/10 * * * * ? *")
    @NotNull(message = "CRON 表达式不能为空")
    @Cron
    private String cronExpression;

    @ApiModelProperty(value = "重试次数", required = true, example = "3")
    @NotNull(message = "重试次数不能为空")
    private Integer retryCount;

    @ApiModelProperty(value = "重试间隔", required = true, example = "1000")
    @NotNull(message = "重试间隔不能为空")
    private Integer retryInterval;
}
