package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yeyue
 * @date 2022-05-25 16:24:05
 */
@Data
@ApiModel(description = "用户角色赋予")
public class SystemPermissionAssignUserRoleReqDTO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色编号列表", example = "1,3,5")
    @NotNull(message = "角色编号不能为空")
    private Set<Long> roleIds;
}
