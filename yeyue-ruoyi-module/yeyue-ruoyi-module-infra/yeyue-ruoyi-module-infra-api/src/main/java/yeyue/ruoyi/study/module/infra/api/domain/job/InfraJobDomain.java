package yeyue.ruoyi.study.module.infra.api.domain.job;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-06-02 14:23:42
 */
@Data
@ApiModel(description = "任务内容")
public class InfraJobDomain implements Serializable {

    @ApiModelProperty(value = "任务编号")
    private Long id;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "任务状态")
    private Integer status;

    @ApiModelProperty(value = "处理器名称")
    private String handlerName;

    @ApiModelProperty(value = "处理器参数")
    private String handlerParam;

    @ApiModelProperty(value = "CRON 表达式")
    private String cronExpression;

    @ApiModelProperty(value = "重试次数")
    private Integer retryCount;

    @ApiModelProperty(value = "重试间隔")
    private Integer retryInterval;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;

}
