package yeyue.ruoyi.study.module.system.impl.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-30 16:29:52
 */
@Data
@ApiModel(description = "登录用户权限信息")
public class SystemAuthPermissionRespVO implements Serializable {
    @ApiModelProperty(value = "角色标识数组", required = true)
    private Set<String> roles;

    @ApiModelProperty(value = "操作权限数组", required = true)
    private Set<String> permissions;
}
