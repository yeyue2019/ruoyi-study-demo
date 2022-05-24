package yeyue.ruoyi.study.module.system.impl.controller.dept;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.validation.core.Groups;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemDeptService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-19 23:08:19
 */
@Api(tags = "系统部门管理")
@Validated
@RestController
@RequestMapping("/web/sys/dept/")
public class SystemDeptController {

    @Resource
    SystemDeptService service;

    @ApiOperation(value = "新增系统部门")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({Groups.Create.class}) @RequestBody SystemDeptCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改系统部门")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({Groups.Update.class}) @RequestBody SystemDeptUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除系统部门")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@NotNull(message = "部门编号不能为空") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取系统部门")
    @GetMapping("/get")
    public CommonResult<SystemDeptDomain> get(@NotNull(message = "部门编号不能为空") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看全部部门")
    @PostMapping("/list")
    public CommonResult<List<SystemDeptDomain>> list(@RequestBody SystemDeptListReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
