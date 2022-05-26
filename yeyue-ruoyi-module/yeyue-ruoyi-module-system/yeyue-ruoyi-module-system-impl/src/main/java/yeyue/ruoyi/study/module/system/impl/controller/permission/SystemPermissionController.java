package yeyue.ruoyi.study.module.system.impl.controller.permission;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignRoleMenuReqDTO;

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
    public CommonResult<Void> assignRoleMenu(@Valid @RequestBody SystemPermissionAssignRoleMenuReqDTO dto) {
        service.assignRoleMenu(dto);
        return CommonResult.success();
    }

    @ApiOperation("获得角色拥有的菜单编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true, dataTypeClass = Long.class)
    @GetMapping("/list-role-resources")
    public CommonResult<Set<Long>> listRoleMenus(@RequestParam Long roleId) {
        return CommonResult.success(service.getRoleMenuIds(roleId));
    }
}
