package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @PostMapping("/authorize")
    @ApiOperation(value = "授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", required = true, value = "授权类型", example = "code", dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "授权范围", example = "userinfo.read", dataTypeClass = String.class),
            @ApiImplicitParam(name = "redirect_uri", value = "重定向 URI", example = "https://www.baidu.com", dataTypeClass = String.class),
            @ApiImplicitParam(name = "username", example = "yeyue", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", example = "123456", dataTypeClass = String.class),
            @ApiImplicitParam(name = "scope", example = "user_info", dataTypeClass = String.class)})
    public CommonResult<Void> postAccessToken(
            HttpServletRequest request, @RequestParam("grant_type") String grantType,
            @RequestParam(value = "code", required = false) String code, // 授权码模式
            @RequestParam(value = "redirect_uri", required = false) String redirectUri, // 授权码模式
            @RequestParam(value = "state", required = false) String state, // 授权码模式
            @RequestParam(value = "username", required = false) String username, // 密码模式
            @RequestParam(value = "password", required = false) String password, // 密码模式
            @RequestParam(value = "scope", required = false) String scope, // 密码模式
            @RequestParam(value = "refresh_token", required = false) String refreshToken
    ) { // 刷新模式
        List<String>
    }
}
