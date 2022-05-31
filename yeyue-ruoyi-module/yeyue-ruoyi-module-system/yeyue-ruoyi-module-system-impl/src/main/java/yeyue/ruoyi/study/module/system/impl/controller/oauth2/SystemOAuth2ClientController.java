package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientPageReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientUpdateReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-17 10:58:40
 */
@Api(tags = "OAuth2协议")
@RestController
@RequestMapping("/web/sys/oauth2/client")
public class SystemOAuth2ClientController {

    @Resource
    SystemOAuth2ClientService service;

    @ApiOperation(value = "新增客户端")
    @PutMapping("/create")
    @PreAuthorize("@ss.hasPermission('system:oauth2:client-create')")
    public CommonResult<Long> create(@Valid @RequestBody SystemOAuth2ClientCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "修改客户端")
    @PostMapping("/update")
    @PreAuthorize("@ss.hasPermission('system:oauth2:client-update')")
    public CommonResult<Void> update(@Valid @RequestBody SystemOAuth2ClientUpdateReqDTO dto) {
        service.update(dto);
        return CommonResult.success();
    }

    @ApiOperation(value = "删除客户端")
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermission('system:oauth2:client-delete')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<Void> delete(@RequestParam Long id) {
        service.delete(id);
        return CommonResult.success();
    }

    @ApiOperation(value = "获取客户端")
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('system:oauth2:client-get')")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<SystemOAuth2ClientDomain> get(@RequestParam Long id) {
        return CommonResult.success(service.get(id));
    }

    @ApiOperation(value = "查看客户端")
    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:oauth2:client-list')")
    public CommonResult<PageResult<SystemOAuth2ClientDomain>> list(@Valid @RequestBody SystemOAuth2ClientPageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
