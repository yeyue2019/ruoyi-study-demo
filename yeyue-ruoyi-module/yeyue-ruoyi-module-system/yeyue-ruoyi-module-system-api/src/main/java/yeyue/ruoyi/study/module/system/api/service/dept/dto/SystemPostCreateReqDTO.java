package yeyue.ruoyi.study.module.system.api.service.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 岗位创建
 *
 * @author yeyue
 * @date 2022-04-28 17:00:31
 */
@Data
@ApiModel(description = "创建岗位")
public class SystemPostCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "岗位名称", required = true, example = "总经理")
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 50, message = "岗位名称长度不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "岗位编码", required = true, example = "ceo")
    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64个字符")
    private String code;

    @ApiModelProperty(value = "显示顺序不能为空", required = true, example = "1")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @ApiModelProperty(value = "备注", example = "岗位备注")
    private String remark;
}
