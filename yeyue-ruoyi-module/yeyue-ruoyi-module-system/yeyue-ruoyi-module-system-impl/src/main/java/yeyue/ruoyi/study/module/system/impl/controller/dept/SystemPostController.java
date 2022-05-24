package yeyue.ruoyi.study.module.system.impl.controller.dept;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.SortedParam;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemPostDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemPostService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-04-28 17:25:47
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/web/sys/post/")
public class SystemPostController {

    @Resource
    SystemPostService service;

    @ApiOperation(value = "新增岗位")
    @PutMapping("/create")
    public CommonResult<Long> create(@Valid @RequestBody SystemPostCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改岗位")
    @PostMapping("/update")
    public CommonResult<Void> update(@Valid @RequestBody SystemPostUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "修改岗位状态")
    @PostMapping("/update/status")
    public CommonResult<Integer> update(@Valid @RequestBody SystemPostStatusUpdateReqDTO dto) {
        return CommonResult.success(service.updateStatus(dto));
    }

    @ApiOperation(value = "删除系统岗位")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取系统岗位")
    @GetMapping("/get")
    public CommonResult<SystemPostDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看系统岗位")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemPostDomain>> list(@Valid @RequestBody SystemPostPageReqDTO dto) {
        dto.addSortParam(SortedParam.asc("sort"));
        return CommonResult.success(service.list(dto));
    }
}
