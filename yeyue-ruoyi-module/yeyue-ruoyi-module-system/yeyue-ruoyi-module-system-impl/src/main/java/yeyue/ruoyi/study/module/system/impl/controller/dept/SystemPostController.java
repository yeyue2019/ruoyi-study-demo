package yeyue.ruoyi.study.module.system.impl.controller.dept;

import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.validation.core.Groups;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemPostDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemPostService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-04-28 17:25:47
 */
@Api(tags = "系统岗位管理")
@Validated
@RestController
@RequestMapping("/web/sys/post/")
public class SystemPostController {

    @Resource
    SystemPostService service;

    @ApiOperation(value = "新增系统岗位")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({Groups.Create.class}) @RequestBody SystemPostCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改系统岗位")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({Groups.Update.class}) @RequestBody SystemPostUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改系统岗位状态")
    @PostMapping("/update/status")
    public CommonResult<Integer> update(@Validated({Groups.Update.class}) @RequestBody SystemPostStatusUpdateReqDTO dto) {
        return CommonResult.success(service.updateStatus(dto));
    }

    @ApiOperation(value = "删除系统岗位")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@NotNull(message = "岗位编号不能为空") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取系统岗位")
    @GetMapping("/get")
    public CommonResult<SystemPostDomain> get(@NotNull(message = "岗位编号不能为空") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看系统岗位")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemPostDomain>> list(@Validated({Groups.List.class}) @RequestBody SystemPostPageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
