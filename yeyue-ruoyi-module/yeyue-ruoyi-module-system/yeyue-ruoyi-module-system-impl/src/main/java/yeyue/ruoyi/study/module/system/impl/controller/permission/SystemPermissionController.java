package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignMenuReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public CommonResult<Void> assignRoleMenu(@Valid @RequestBody SystemPermissionAssignMenuReqDTO dto) {
        service.assignRoleMenu(dto);
        return CommonResult.success();
    }
}
