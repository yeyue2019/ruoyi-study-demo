package yeyue.ruoyi.study.module.infra.api.service.job.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageParam;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;

/**
 * @author yeyue
 * @date 2022-06-06 17:04:02
 */
@Data
@ApiModel(description = "任务查询")
public class InfraJobPageReqDTO extends PageParam {

    @ApiModelProperty(value = "任务名称", example = "测试任务", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "任务状态", example = "1", notes = "精确查询")
    @InEnum(value = CommonStatusEnum.class, message = "状态枚举错误")
    private Integer status;

    @ApiModelProperty(value = "处理器的名字", example = "sysUserSessionTimeoutJob", notes = "模糊匹配")
    private String handlerName;
}
