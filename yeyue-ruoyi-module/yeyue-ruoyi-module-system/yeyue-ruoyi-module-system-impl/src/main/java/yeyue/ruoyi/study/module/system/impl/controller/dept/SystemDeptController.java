package yeyue.ruoyi.study.module.system.impl.controller.dept;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.SystemDeptService;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptListReqDTO;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptUpdateReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-19 23:08:19
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/web/sys/dept")
public class SystemDeptController {

    @Resource
    SystemDeptService service;

    @ApiOperation(value = "新增部门")
    @PutMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public CommonResult<Long> create(@Valid @RequestBody SystemDeptCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改部门")
    @PostMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:dept:update')")
    public CommonResult<Void> update(@Valid @RequestBody SystemDeptUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:dept:delete')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "100", dataTypeClass = Long.class)
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取部门")
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:dept:get')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "100", dataTypeClass = Long.class)
    public CommonResult<SystemDeptDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "全部部门")
    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:dept:list')")
    public CommonResult<List<SystemDeptDomain>> list(@Valid @RequestBody SystemDeptListReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
