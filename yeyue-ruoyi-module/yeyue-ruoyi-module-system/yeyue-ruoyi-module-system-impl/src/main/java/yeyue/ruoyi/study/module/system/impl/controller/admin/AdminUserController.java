package yeyue.ruoyi.study.module.system.impl.controller.admin;

import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.validation.annotation.InIntEnum;
import yeyue.ruoyi.study.framework.common.validation.core.ApiCommand;
import yeyue.ruoyi.study.module.system.api.domain.user.AdminUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.AdminUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author yeyue
 * @date 2022-04-18 17:01:43
 */
@Api(tags = "系统用户管理")
@Validated
@RestController
@RequestMapping("/web/sys/user/admin")
public class AdminUserController {

    @Resource
    AdminUserService service;

    @ApiOperation(value = "新增系统用户")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({ApiCommand.Create.class}) @RequestBody AdminUserDomain create) {
        return CommonResult.success(service.create(create));
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({ApiCommand.Update.class}) @RequestBody AdminUserDomain update) {
        service.update(update);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/get/{id}")
    public CommonResult<AdminUserDomain> get(@Positive(message = "用户Id格式错误") @PathVariable Long id) {
        return CommonResult.success(service.getById(id));
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/get")
    public CommonResult<AdminUserDomain> get(@NotEmpty(message = "用户账户不能为空") @RequestParam String username) {
        return CommonResult.success(service.getByUsername(username));
    }

    @ApiOperation(value = "删除系统用户")
    @DeleteMapping("/delete")
    public CommonResult<Integer> delete(@Positive(message = "用户Id格式错误") @RequestParam Long id) {
        return CommonResult.success(service.delete(id));
    }

    @ApiOperation(value = "系统用户列表")
    @PostMapping("/list")
    public CommonResult<PageResult<AdminUserDomain>> list(@Valid @RequestBody AdminUserPageReqDTO req) {
        return CommonResult.success(service.list(req));
    }

    @ApiOperation(value = "更改用户密码")
    @PostMapping("/passwd/update")
    public CommonResult<Void> update(@Positive(message = "用户Id格式错误") @RequestParam Long id, @Valid @RequestBody AdminUserUpdatePasswdReqDTO req) {
        service.updatePasswd(id, req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改用户状态")
    @PostMapping("/status/update/{id}/{status}")
    public CommonResult<Void> update(
            @Positive(message = "用户Id格式错误")
            @PathVariable Long id,
            @InIntEnum(CommonStatusEnum.class) @PathVariable Integer status) {
        service.updateStatus(id, status);
        return CommonResult.success();
    }
}
