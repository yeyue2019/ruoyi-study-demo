package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.validation.core.Groups;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemRoleService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-05-24 16:00:21
 */
@Api(tags = "系统角色管理")
@Validated
@RestController
@RequestMapping("/web/sys/role/")
public class SystemRoleController {

    @Resource
    SystemRoleService service;

    @ApiOperation(value = "新增系统角色")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({Groups.Create.class}) @RequestBody SystemRoleCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改系统角色")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({Groups.Update.class}) @RequestBody SystemRoleUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除系统角色")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@NotNull(message = "角色编号不能为空") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取系统角色")
    @GetMapping("/get")
    public CommonResult<SystemRoleDomain> get(@NotNull(message = "角色编号不能为空") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查询系统角色")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemRoleDomain>> list(@RequestBody SystemRolePageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
