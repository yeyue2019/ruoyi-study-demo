package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenPageReqDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-17 20:29:47
 */
@Api(tags = "OAuth2协议")
@RestController
@RequestMapping("/web/sys/oauth2/token")
public class SystemOAuth2TokenController {

    @Resource
    SystemOAuth2TokenService service;

    @ApiOperation(value = "删除访问令牌")
    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "accessToken", value = "访问令牌", required = true, dataTypeClass = String.class, example = "xxx")
    public CommonResult<SystemOAuth2AccessTokenDomain> remove(@RequestParam String accessToken) {
        return CommonResult.success(service.remove(accessToken));
    }

    @ApiOperation(value = "查看访问令牌")
    @PostMapping("/list")
    public CommonResult<PageResult<SystemOAuth2AccessTokenDomain>> list(@Valid @RequestBody SystemOAuth2AccessTokenPageReqDTO dto) {
        return CommonResult.success(service.list(dto));
    }
}
