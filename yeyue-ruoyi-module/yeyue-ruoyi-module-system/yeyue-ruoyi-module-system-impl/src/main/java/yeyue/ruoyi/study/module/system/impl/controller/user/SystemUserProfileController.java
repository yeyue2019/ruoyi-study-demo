package yeyue.ruoyi.study.module.system.impl.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserGetReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserPasswordUpdateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.dto.SystemUserProfileUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.controller.user.vo.SystemUserRespVO;
import yeyue.ruoyi.study.module.system.impl.entity.user.convert.SystemUserConvert;
import yeyue.ruoyi.study.module.system.impl.framework.security.util.SystemSecurityUtils;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yeyue
 * @date 2022-05-29 22:44:57
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/web/sys/user-profile")
@Validated
@Slf4j
public class SystemUserProfileController {

    @Resource
    SystemUserService service;

    @ApiOperation(value = "个人信息")
    @GetMapping("/get")
    public CommonResult<SystemUserRespVO> get() {
        SystemUserDomain domain = service.get(new SystemUserGetReqDTO().setId(SystemSecurityUtils.getUserId()));
        return CommonResult.success(SystemUserConvert.INSTANCE.toVo(domain));
    }

    @ApiOperation(value = "更改资料")
    @PostMapping("/update-profile")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserProfileUpdateReqDTO req) {
        service.update(SystemSecurityUtils.getUserId(), req);
        return CommonResult.success();
    }

    @ApiOperation(value = "更改密码")
    @PostMapping("/update-password")
    public CommonResult<Void> update(@Valid @RequestBody SystemUserPasswordUpdateReqDTO req) {
        service.update(SystemSecurityUtils.getUserId(), req);
        return CommonResult.success();
    }
}
