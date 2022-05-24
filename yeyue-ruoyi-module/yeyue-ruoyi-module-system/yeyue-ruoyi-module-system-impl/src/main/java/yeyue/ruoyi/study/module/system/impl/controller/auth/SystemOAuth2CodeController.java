package yeyue.ruoyi.study.module.system.impl.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2CodeService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2CodeCreateReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-17 16:50:00
 */
@Api(tags = "OAuth2协议管理")
@RestController
@RequestMapping("/web/sys/oauth2/code")
public class SystemOAuth2CodeController {

    @Resource
    SystemOAuth2CodeService service;

    @ApiOperation(value = "生成授权码")
    @PostMapping("/create")
    public CommonResult<String> create(@Valid @RequestBody SystemOAuth2CodeCreateReqDTO dto) {
        return CommonResult.success(service.create(dto));
    }

    @ApiOperation(value = "授权获取令牌")
    @GetMapping(value = "/authorize")
    public CommonResult<SystemOAuth2AccessTokenDomain> authorize(@RequestParam String code) {
        return CommonResult.success(service.authorize(code));
    }
}
