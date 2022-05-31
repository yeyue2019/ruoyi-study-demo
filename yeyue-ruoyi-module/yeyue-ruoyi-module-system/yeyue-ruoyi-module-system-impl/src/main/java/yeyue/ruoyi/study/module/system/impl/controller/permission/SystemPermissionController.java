package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignRoleMenuReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignUserRoleReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-24 23:03:35
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/web/sys/permission")
public class SystemPermissionController {

    @Resource
    SystemPermissionService service;

    @PostMapping("/assign-role-menu")
    @ApiOperation("赋予角色菜单")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-role-menu')")
    public CommonResult<Void> assignRoleMenu(@Valid @RequestBody SystemPermissionAssignRoleMenuReqDTO dto) {
        service.assignRoleMenu(dto);
        return CommonResult.success();
    }

    @ApiOperation("获取角色菜单")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true, dataTypeClass = Long.class)
    @GetMapping("/list-role-menus")
    @PreAuthorize("@ss.hasPermission('system:permission:list-role-menus')")
    public CommonResult<Set<SystemMenuDomain>> listRoleMenus(@RequestParam Long roleId, @RequestParam(required = false) Integer status) {
        return CommonResult.success(service.getRoleMenuIds(roleId, status));
    }

    @ApiOperation("赋予用户角色")
    @PostMapping("/assign-user-role")
    @PreAuthorize("@ss.hasPermission('system:permission:assign-user-role')")
    public CommonResult<Void> assignUserRole(@Valid @RequestBody SystemPermissionAssignUserRoleReqDTO dto) {
        service.assignUserRole(dto);
        return CommonResult.success();
    }

    @ApiOperation("获取用户角色")
    @ApiImplicitParam(name = "userId", value = "用户编号", required = true, dataTypeClass = Long.class)
    @GetMapping("/list-user-roles")
    @PreAuthorize("@ss.hasPermission('system:permission:list-user-roles')")
    public CommonResult<Set<SystemRoleDomain>> listAdminRoles(@RequestParam Long userId, @RequestParam(required = false) Integer status) {
        return CommonResult.success(service.getUserRoleIds(userId, status));
    }
}
