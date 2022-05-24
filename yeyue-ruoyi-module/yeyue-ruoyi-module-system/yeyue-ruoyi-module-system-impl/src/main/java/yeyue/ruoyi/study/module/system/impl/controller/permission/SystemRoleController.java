package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemRoleService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-24 16:00:21
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/web/sys/role")
public class SystemRoleController {

    @Resource
    SystemRoleService service;

    @ApiOperation(value = "新增角色")
    @PutMapping("/create")
    public CommonResult<Long> create(@Valid @RequestBody SystemRoleCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改角色")
    @PostMapping("/update")
    public CommonResult<Void> update(@Valid @RequestBody SystemRoleUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("/get")
    public CommonResult<SystemRoleDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查询角色")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemRoleDomain>> list(@Valid @RequestBody SystemRolePageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
