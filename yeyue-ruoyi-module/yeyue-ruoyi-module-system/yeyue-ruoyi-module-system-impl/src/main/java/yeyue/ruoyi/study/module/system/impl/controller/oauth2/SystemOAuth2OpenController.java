package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;

/**
 * @author yeyue
 * @date 2022-05-26 11:25:11
 */
@Api(tags = "OAuth2协议")
@RestController
@RequestMapping("/web/sys/oauth2/open")
public class SystemOAuth2OpenController {

    /**
     * 对应 Spring Security OAuth 的 TokenEndpoint 类的 postAccessToken 方法
     * <p>
     * 授权码 authorization_code 模式时：code + redirectUri + state 参数 密码 password 模式时：username + password + scope 参数 刷新
     * refresh_token 模式时：refreshToken 参数 客户端 client_credentials 模式：scope 参数 简化 implicit 模式时：不支持
     * <p>
     * 注意，默认需要传递 client_id + client_secret 参数
     */
    @PostMapping("/token")
    @ApiOperation(value = "获得访问令牌", notes = "适合 code 授权码模式，或者 implicit 简化模式；在 sso.vue 单点登录界面被【获取】调用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "grant_type", required = true, value = "授权类型", example = "code",
            dataTypeClass = String.class),
        @ApiImplicitParam(name = "code", value = "授权范围", example = "userinfo.read", dataTypeClass = String.class),
        @ApiImplicitParam(name = "redirect_uri", value = "重定向 URI", example = "https://www.iocoder.cn",
            dataTypeClass = String.class),
        @ApiImplicitParam(name = "username", example = "tudou", dataTypeClass = String.class),
        @ApiImplicitParam(name = "password", example = "cai", dataTypeClass = String.class), // 多个使用空格分隔
        @ApiImplicitParam(name = "scope", example = "user_info", dataTypeClass = String.class)})
    public CommonResult<OAuth2OpenAccessTokenRespVO> postAccessToken(HttpServletRequest request,
        @RequestParam("grant_type") String grantType, @RequestParam(value = "code", required = false) String code, // 授权码模式
        @RequestParam(value = "redirect_uri", required = false) String redirectUri, // 授权码模式
        @RequestParam(value = "state", required = false) String state, // 授权码模式
        @RequestParam(value = "username", required = false) String username, // 密码模式
        @RequestParam(value = "password", required = false) String password, // 密码模式
        @RequestParam(value = "scope", required = false) String scope, // 密码模式
        @RequestParam(value = "refresh_token", required = false) String refreshToken) { // 刷新模式

    }
}
