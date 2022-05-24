package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.validation.core.Groups;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-23 17:55:57
 */
@Api(tags = "系统菜单管理")
@Validated
@RestController
@RequestMapping("/web/sys/menu/")
public class SystemMenuController {

    @Resource
    SystemMenuService service;

    @ApiOperation(value = "新增系统菜单")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({Groups.Create.class}) @RequestBody SystemMenuCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改系统菜单")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({Groups.Update.class}) @RequestBody SystemMenuUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除系统菜单")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@NotNull(message = "菜单编号不能为空") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取系统菜单")
    @GetMapping("/get")
    public CommonResult<SystemMenuDomain> get(@NotNull(message = "菜单编号不能为空") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看全部菜单")
    @PostMapping("/list")
    public CommonResult<List<SystemMenuDomain>> list(@RequestBody SystemMenuListReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
