package yeyue.ruoyi.study.server.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.security.core.authentication.YeyueUsernamePasswordAuthenticationToken;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.server.security.controller.req.UserLoginVO;

import javax.annotation.Resource;

/**
 * @author yeyue
 * @date 2022-04-19 15:39:39
 */
@Slf4j
@Api(tags = "用户权限测试类")
@Validated
@RestController
@RequestMapping("/ruoyi/test/security")
public class SecurityController {

    // 延迟加载，因为存在相互依赖的问题

    @Resource
    @Lazy
    AuthenticationManager authenticationManager;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult<LoginUser> login(@RequestBody UserLoginVO vo) {
        // 调用 Spring Security 的 AuthenticationManager#authenticate(...) 方法，使用账号密码进行认证
        // 在其内部，会调用到 loadUserByUsername 方法，获取 User 信息
        Authentication authentication = authenticationManager.authenticate(new YeyueUsernamePasswordAuthenticationToken(vo.getUsername(), vo.getPassword()));
        return CommonResult.success((LoginUser) authentication.getPrincipal());
    }
}
