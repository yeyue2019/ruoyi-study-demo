package yeyue.ruoyi.study.module.system.impl.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;
import yeyue.ruoyi.study.module.system.impl.controller.user.vo.SystemUserRespVO;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * @author yeyue
 * @date 2022-04-18 17:01:43
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/web/sys/user")
public class SystemUserController {

    @Resource
    SystemUserService service;

    @ApiOperation(value = "新增用户")
    @PutMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:user:create')")
    public CommonResult<Long> create(@Valid @RequestBody SystemUserCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get-id")
    @PreAuthorize("@ss.hasPermission('system:user:query')")
    public CommonResult<SystemUserRespVO> get(@Positive(message = "用户Id格式错误") @RequestParam Long id) {
        SystemUserDomain domain = service.get(new SystemUserGetReqDTO().setId(id));
        return CommonResult.success(SystemUserConvert.INSTANCE.toVo(domain));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:user:delete')")
    public CommonResult<Void> delete(@Positive(message = "用户Id格式错误") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "用户列表")
    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:user:query')")
    public CommonResult<PageResult<SystemUserRespVO>> list(@Valid @RequestBody SystemUserPageReqDTO req) {
        PageResult<SystemUserDomain> pageResult = service.list(req);
        return CommonResult.success(CollectionUtils.funcPage(pageResult, SystemUserConvert.INSTANCE::toVo));
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("/replace-password")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserPasswordReplaceReqDTO req) {
        service.replace(req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改状态")
    @PostMapping("/update-status")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserStatusUpdateReqDTO req) {
        service.update(req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改部门")
    @PostMapping("/update-dept")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserDeptUpdateReqDTO req) {
        service.update(req);
        return CommonResult.success();
    }
}
