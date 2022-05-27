package yeyue.ruoyi.study.module.system.impl.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPageReq;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserUpdatePwdReq;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * @author yeyue
 * @date 2022-04-18 17:01:43
 */
@Api(tags = "用户管理")
@Validated
@RestController
@RequestMapping("/web/sys/user/")
public class SystemUserController {

    @Resource
    SystemUserService service;

    @ApiOperation(value = "新增用户")
    @PutMapping("/create")
    public CommonResult<Long> create(@Valid @RequestBody SystemUserCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/update")
    public CommonResult<Void> update(@RequestBody SystemUserDomain update) {
        service.update(update);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public CommonResult<SystemUserDomain> get(@Positive(message = "用户Id格式错误") @PathVariable Long id) {
        return CommonResult.success(service.getById(id));
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get")
    public CommonResult<SystemUserDomain> get(@NotEmpty(message = "用户账户不能为空") @RequestParam String username) {
        return CommonResult.success(service.getByUsername(username));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    public CommonResult<Integer> delete(@Positive(message = "用户Id格式错误") @RequestParam Long id) {
        return CommonResult.success(service.delete(id));
    }

    @ApiOperation(value = "用户列表")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemUserDomain>> list(@Valid @RequestBody SystemUserPageReq req) {
        return CommonResult.success(service.list(req));
    }

    @ApiOperation(value = "更改密码")
    @PostMapping("/passwd/update")
    public CommonResult<Void> update(@Positive(message = "用户Id格式错误") @RequestParam Long id,
                                     @Valid @RequestBody SystemUserUpdatePwdReq req) {
        service.updatePwd(id, req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户Id", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "用户状态", paramType = "query", dataType = "Integer"),})
    @PostMapping("/status/update")
    public CommonResult<Void> update(@Positive(message = "用户Id格式错误") @RequestParam Long id,
                                     @InEnum(CommonStatusEnum.class) @RequestParam Integer status) {
        service.updateStatus(id, status);
        return CommonResult.success();
    }
}
