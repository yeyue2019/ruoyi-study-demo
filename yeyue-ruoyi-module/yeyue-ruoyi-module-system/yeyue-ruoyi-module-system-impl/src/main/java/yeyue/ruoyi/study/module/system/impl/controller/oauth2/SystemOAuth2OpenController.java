package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.constants.StringConstants;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ApproveService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2GrantService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveCheckReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveGetReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveUpdateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.framework.security.util.SystemSecurityUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-26 11:25:11
 */
@Api(tags = "OAuth2协议")
@RestController
@RequestMapping("/web/sys/oauth2/open")
public class SystemOAuth2OpenController {

    @Resource
    SystemOAuth2ClientService clientService;
    @Resource
    SystemOAuth2GrantService grantService;
    @Resource
    SystemOAuth2ApproveService approveService;

    /**
     * 对应 Spring Security OAuth 的 TokenEndpoint 类的 postAccessToken 方法
     * <p>
     * 授权码 authorization_code 模式时：code + redirectUri + state 参数 密码 password 模式时：username + password + scope 参数 刷新
     * refresh_token 模式时：refreshToken 参数 客户端 client_credentials 模式：scope 参数 简化 implicit 模式时：不支持
     * <p>
     * 注意，默认需要传递 client_id + client_secret 参数
     */
    @PostMapping("/authenticate")
    @ApiOperation(value = "授权获取令牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientId", required = true, value = "客户端Id", example = "yeyue", dataTypeClass = String.class, paramType = "header"),
            @ApiImplicitParam(name = "clientSecret", required = true, value = "客户端密钥", example = "xxx", dataTypeClass = String.class, paramType = "header"),
            @ApiImplicitParam(name = "grantType", required = true, value = "授权类型", example = "password", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "授权码", example = "xxx", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "username", example = "yeyue", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "password", example = "123456", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "scope", example = "user_info", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "refreshToken", example = "xxx", dataTypeClass = String.class, paramType = "query")})
    public CommonResult<SystemOAuth2AccessTokenDomain> authenticate(@RequestHeader String clientId,
                                                                    @RequestHeader String clientSecret,
                                                                    @InEnum(value = OAuth2GrantTypeEnum.class, message = "授权类型错误") @RequestParam String grantType,
                                                                    @RequestParam(required = false) String scope,
                                                                    @RequestParam(required = false) String code,
                                                                    @RequestParam(required = false) String username,
                                                                    @RequestParam(required = false) String password,
                                                                    @RequestParam(required = false) String refreshToken) {
        List<String> scopes = CollectionUtils.arrayToList(StringUtils.split(scope, StringConstants.SPLIT_JOIN));
        SystemOAuth2ClientDomain client = clientService.validate(new SystemOAuth2ClientValidateReqDTO()
                .setClientId(clientId).setSecret(clientSecret).setAuthorizedGrantType(grantType).setScopes(scopes));
        SystemOAuth2AccessTokenDomain result;
        switch (Objects.requireNonNull(OAuth2GrantTypeEnum.getByGranType(grantType))) {
            case AUTHORIZATION_CODE:
                if (StringUtils.isBlank(code)) {
                    throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), "授权码不能为空");
                }
                result = grantService.authorizationCode(code, clientId, client.getAccessTokenValiditySeconds(), client.getRefreshTokenValiditySeconds());
                break;
            case PASSWORD:
                if (StringUtils.isAnyBlank(username, password)) {
                    throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), "账号或密码不能为空");
                }
                // TODO: 2022/5/30 校验方法
                LoginUser loginUser = SystemSecurityUtils.getLoginUser();
                result = grantService.password(loginUser.getId(), loginUser.getUserType(), clientId, scopes, client.getAccessTokenValiditySeconds(), client.getRefreshTokenValiditySeconds());
                break;
            case REFRESH_TOKEN:
                if (StringUtils.isBlank(refreshToken)) {
                    throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), "刷新令牌不能为空");
                }
                result = grantService.refresh(refreshToken, clientId, client.getAccessTokenValiditySeconds());
                break;
            case CLIENT_CREDENTIALS:
                result = grantService.clientCredentials(clientId, scopes, client.getAccessTokenValiditySeconds(), client.getRefreshTokenValiditySeconds());
                break;
            default:
                throw new ServiceException(GlobalErrorCode.UNSUPPORTED_OPERATION);
        }
        return CommonResult.success(result);
    }

    // TODO: 2022/5/27 接口需要授权

    @PostMapping("/authorization")
    @ApiOperation(value = "获得授权信息")
    @ApiImplicitParam(name = "clientId", required = true, value = "客户端编号", example = "yeyue", dataTypeClass = String.class)
    public CommonResult<Set<String>> authorize(@RequestParam String clientId) {
        LoginUser loginUser = SystemSecurityUtils.getLoginUser();
        SystemOAuth2ClientDomain client = clientService.validate(new SystemOAuth2ClientValidateReqDTO()
                .setClientId(clientId));
        SystemOAuth2ApproveGetReqDTO reqDTO = new SystemOAuth2ApproveGetReqDTO()
                .setClientId(clientId).setAutoApproveScopes(client.getAutoApproveScopes())
                .setUserType(loginUser.getUserType()).setUserId(loginUser.getId());
        return CommonResult.success(approveService.get(reqDTO));
    }

    // TODO: 2022/5/27 接口需要授权

    /**
     * 对应 Spring Security OAuth 的 AuthorizationEndpoint 类的 approveOrDeny 方法
     * <p>
     * 场景一：【自动授权 autoApprove = true】
     * 刚进入 sso.vue 界面，调用该接口，用户历史已经给该应用做过对应的授权，或者 OAuth2Client 支持该 scope 的自动授权
     * 场景二：【手动授权 autoApprove = false】
     * 在 sso.vue 界面，用户选择好 scope 授权范围，调用该接口，进行授权。此时，approved 为 true 或者 false
     * <p>
     * 因为前后端分离，Axios 无法很好的处理 302 重定向，所以和 Spring Security OAuth 略有不同，返回结果是重定向的 URL，剩余交给前端处理
     */
    @PostMapping("/authorize")
    @ApiOperation(value = "发起授权申请", notes = "适合 code 授权码模式，或者 implicit 简化模式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "responseType", required = true, value = "响应类型", example = "code", dataTypeClass = String.class),
            @ApiImplicitParam(name = "clientId", required = true, value = "客户端编号", example = "yeyue", dataTypeClass = String.class),
            @ApiImplicitParam(name = "scope", value = "授权范围", example = "userinfo.read", dataTypeClass = String.class),
            @ApiImplicitParam(name = "redirectUri", required = true, value = "重定向 URI", example = "https://www.iocoder.cn", dataTypeClass = String.class),
            @ApiImplicitParam(name = "autoApprove", value = "是否自动同意", example = "true", dataTypeClass = Boolean.class),
            @ApiImplicitParam(name = "state", example = "123321", dataTypeClass = String.class)
    })
    public CommonResult<String> authorize(@RequestParam @InEnum(value = OAuth2GrantTypeEnum.class, message = "授权类型错误") String responseType,
                                          @RequestParam String clientId,
                                          @RequestParam String scope,
                                          @RequestParam String redirectUri,
                                          @RequestParam(required = false) Boolean autoApprove,
                                          @RequestParam(required = false) String state) {
        List<String> scopes = CollectionUtils.arrayToList(StringUtils.split(scope, StringConstants.SPLIT_JOIN));
        // 0. 校验用户已经登录。通过 Spring Security 实现
        LoginUser loginUser = SystemSecurityUtils.getLoginUser();

        // 1. 校验 redirectUri 重定向域名是否合法 + 校验 scope 是否在 Client 授权范围内
        SystemOAuth2ClientDomain client = clientService.validate(new SystemOAuth2ClientValidateReqDTO()
                .setClientId(clientId).setAuthorizedGrantType(responseType).setScopes(scopes).setRedirectUri(redirectUri));

        // 2.1 假设 approved 为 null，说明是场景一
        if (Objects.equals(autoApprove, Boolean.TRUE)) {
            // 如果无法自动授权通过，则返回空 url，前端不进行跳转
            if (!approveService.check((SystemOAuth2ApproveCheckReqDTO) new SystemOAuth2ApproveCheckReqDTO()
                    .setScopes(scopes)
                    .setUserId(loginUser.getId())
                    .setUserType(loginUser.getUserType())
                    .setClientId(clientId)
                    .setAutoApproveScopes(client.getAutoApproveScopes()))) {
                return CommonResult.error(SystemErrorCode.OAUTH2_GRANT_SCOPE_HAS_NOT_APPROVE);
            }
        } else { // 2.2 假设 approved 非 null，说明是场景二
            // 如果计算后不通过，则跳转一个错误链接
            approveService.update((SystemOAuth2ApproveUpdateReqDTO) new SystemOAuth2ApproveUpdateReqDTO()
                    .setScopes(scopes)
                    .setApproveValiditySeconds(client.getApproveValiditySeconds())
                    .setClientId(clientId)
                    .setUserId(loginUser.getId())
                    .setUserType(loginUser.getUserType()));
        }
        switch (Objects.requireNonNull(OAuth2GrantTypeEnum.getByGranType(responseType))) {
            case AUTHORIZATION_CODE:
                String code = grantService.authorizationCode(loginUser.getId(), loginUser.getUserType(), clientId, scopes, redirectUri, state, client.getCodeValiditySeconds());
                return CommonResult.success(String.format("%s?code=%s&state=%s", redirectUri, code, state));
            case IMPLICIT:
                SystemOAuth2AccessTokenDomain accessTokenDomain = grantService.implicit(loginUser.getId(), loginUser.getUserType(), clientId, scopes, client.getAccessTokenValiditySeconds(), client.getRefreshTokenValiditySeconds());
                return CommonResult.success(JSON.toJSONString(accessTokenDomain));
            default:
                throw new ServiceException(GlobalErrorCode.UNSUPPORTED_OPERATION);
        }
    }
}
