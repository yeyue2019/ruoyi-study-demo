package yeyue.ruoyi.study.module.system.impl.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-23 17:55:57
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/web/sys/menu")
public class SystemMenuController {

    @Resource
    SystemMenuService service;

    @ApiOperation(value = "新增菜单")
    @PutMapping("/create")
    public CommonResult<Long> create(@Valid @RequestBody SystemMenuCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("/update")
    public CommonResult<Void> update(@Valid @RequestBody SystemMenuUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取菜单")
    @GetMapping("/get")
    public CommonResult<SystemMenuDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看菜单")
    @PostMapping("/list")
    public CommonResult<List<SystemMenuDomain>> list(@Valid @RequestBody SystemMenuListReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
