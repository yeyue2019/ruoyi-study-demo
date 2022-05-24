package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 岗位修改
 *
 * @author yeyue
 * @date 2022-05-11 23:36:31
 */
@Data
@ApiModel(description = "岗位修改")
public class SystemPostUpdateReqDTO extends SystemPostCreateReqDTO {

    @ApiModelProperty(value = "岗位编号", required = true, example = "1024")
    @NotNull(message = "岗位编号不能为空")
    private Long id;
}
