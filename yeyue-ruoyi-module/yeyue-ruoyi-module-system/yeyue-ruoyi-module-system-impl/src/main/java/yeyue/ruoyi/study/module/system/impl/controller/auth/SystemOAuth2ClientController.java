package yeyue.ruoyi.study.module.system.impl.controller.auth;

import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.validation.core.Groups;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author yeyue
 * @date 2022-05-17 10:58:40
 */
@Api(tags = "OAuth2协议管理")
@Validated
@RestController
@RequestMapping("/web/sys/oauth2/client")
public class SystemOAuth2ClientController {

    @Resource
    SystemOAuth2ClientService service;

    @ApiOperation(value = "新增客户端")
    @PutMapping("/create")
    public CommonResult<Long> create(@Validated({Groups.Create.class}) @RequestBody SystemOAuth2ClientCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改客户端")
    @PostMapping("/update")
    public CommonResult<Void> update(@Validated({Groups.Update.class}) @RequestBody SystemOAuth2ClientUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除客户端")
    @DeleteMapping("/delete")
    public CommonResult<Void> delete(@NotNull(message = "客户端编号不能为空") @RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取客户端")
    @GetMapping("/get")
    public CommonResult<SystemOAuth2ClientDomain> get(@NotNull(message = "客户端编号不能为空") @RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看客户端")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemOAuth2ClientDomain>> get(@Validated({Groups.List.class}) @RequestBody SystemOAuth2ClientPageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
