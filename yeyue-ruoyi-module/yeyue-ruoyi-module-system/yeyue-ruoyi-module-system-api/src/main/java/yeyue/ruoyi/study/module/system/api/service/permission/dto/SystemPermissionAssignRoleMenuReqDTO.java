package yeyue.ruoyi.study.module.system.api.service.permission.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yeyue
 * @date 2022-05-24 16:43:34
 */
@Data
@ApiModel(description = "角色权限赋予")
public class SystemPermissionAssignRoleMenuReqDTO implements Serializable {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    @ApiModelProperty(value = "菜单编号列表", example = "1,3,5")
    @NotNull(message = "菜单权限编号不能为空")
    private Set<Long> menuIds;

}
