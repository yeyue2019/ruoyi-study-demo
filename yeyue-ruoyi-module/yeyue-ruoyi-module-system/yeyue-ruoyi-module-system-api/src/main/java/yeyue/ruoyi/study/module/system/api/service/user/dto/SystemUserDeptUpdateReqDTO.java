package yeyue.ruoyi.study.module.system.api.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-27 16:55:08
 */
@Data
@ApiModel(description = "更新用户部门信息")
public class SystemUserDeptUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long id;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "部门ID", example = "1")
    @NotNull(message = "部门不能为空")
    private Long deptId;

    @ApiModelProperty(value = "岗位编号数组", example = "1")
    @NotNull(message = "岗位不能为空")
    private Set<Long> postIds;
}
