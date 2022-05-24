package yeyue.ruoyi.study.module.system.impl.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenRefreshReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-17 20:29:47
 */
@Api(tags = "OAuth2协议管理")
@Validated
@RestController
@RequestMapping("/web/sys/oauth2/token")
public class SystemOAuth2TokenController {

    @Resource
    SystemOAuth2TokenService service;

    @ApiOperation(value = "生成访问令牌")
    @PutMapping("/create")
    public CommonResult<SystemOAuth2AccessTokenDomain> create(@Valid @RequestBody SystemOAuth2AccessTokenCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "刷新访问令牌")
    @PostMapping("/refresh")
    public CommonResult<SystemOAuth2AccessTokenDomain> refresh(@Valid @RequestBody SystemOAuth2AccessTokenRefreshReqDTO dto) {
        return CommonResult.success(service.refresh(dto));
    }

    @ApiOperation(value = "获取访问令牌")
    @GetMapping("/get")
    public CommonResult<SystemOAuth2AccessTokenDomain> get(@RequestParam String accessToken) {
        return CommonResult.success(service.get(accessToken));
    }

    @ApiOperation(value = "移除访问令牌")
    @DeleteMapping("/remove")
    public CommonResult<SystemOAuth2AccessTokenDomain> remove(@RequestParam String accessToken) {
        return CommonResult.success(service.remove(accessToken));
    }
}
