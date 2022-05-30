package yeyue.ruoyi.study.module.system.impl.controller.oauth2;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.validation.util.ValidationUtils;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ApproveService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2GrantService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ApproveReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2GrantReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2TokenReqDTO;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.framework.security.util.SystemSecurityUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.Validator;
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
    Validator validator;
    @Resource
    SystemOAuth2GrantService grantService;
    @Resource
    SystemOAuth2ApproveService approveService;
    @Resource
    SystemOAuth2ClientService clientService;

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
    public CommonResult<SystemOAuth2AccessTokenDomain> authenticate(@RequestBody @Valid SystemOAuth2GrantReqDTO dto) {
        // 1. 参数校验
        switch (Objects.requireNonNull(OAuth2GrantTypeEnum.getByGranType(dto.getGrantType()))) {
            case AUTHORIZATION_CODE:
                ValidationUtils.validate(validator, dto, SystemOAuth2GrantReqDTO.AUTHORIZATION_CODE_GROUP.class);
                break;
            case PASSWORD:
                ValidationUtils.validate(validator, dto, SystemOAuth2GrantReqDTO.PASSWORD_GROUP.class);
                break;
            case REFRESH_TOKEN:
                ValidationUtils.validate(validator, dto, SystemOAuth2GrantReqDTO.REFRESH_TOKEN_GROUP.class);
                break;
            case CLIENT_CREDENTIALS:
                break;
            default:
                throw new ServiceException(GlobalErrorCode.UNSUPPORTED_OPERATION);
        }
        // 2. 客户端校验
        SystemOAuth2ClientDomain client = clientService.validate(
                new SystemOAuth2ClientValidateReqDTO()
                        .setClientId(dto.getClientId())
                        .setSecret(dto.getClientSecret())
                        .setScopes(dto.getScopes())
                        .setAuthorizedGrantType(dto.getGrantType())
        );
        SystemOAuth2AccessTokenDomain result;
        switch (Objects.requireNonNull(OAuth2GrantTypeEnum.getByGranType(dto.getGrantType()))) {
            case AUTHORIZATION_CODE:
                result = grantService.authorizationCode(dto.getClientId(), dto.getCode(), client);
                break;
            case PASSWORD:
                result = grantService.password(dto.getClientId(), dto.getUsername(), dto.getPassword(), dto.getScopes(), client);
                break;
            case REFRESH_TOKEN:
                result = grantService.refresh(dto.getClientId(), dto.getRefreshToken(), client);
                break;
            case CLIENT_CREDENTIALS:
                result = grantService.clientCredentials(dto.getClientId(), dto.getScopes(), client);
                break;
            default:
                throw new ServiceException(GlobalErrorCode.UNSUPPORTED_OPERATION);
        }
        return CommonResult.success(result);
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "移除访问令牌")
    public CommonResult<Void> revokeToken(@RequestBody @Valid SystemOAuth2TokenReqDTO dto) {
        SystemOAuth2ClientDomain client = clientService.validate(
                new SystemOAuth2ClientValidateReqDTO()
                        .setClientId(dto.getClientId())
                        .setSecret(dto.getClientSecret())
        );
        grantService.remove(dto.getAccessToken());
        return CommonResult.success();
    }

    /**
     * 对应 Spring Security OAuth 的 CheckTokenEndpoint 类的 checkToken 方法
     */
    @PostMapping("/check")
    @ApiOperation(value = "校验访问令牌")
    public CommonResult<SystemOAuth2AccessTokenDomain> checkToken(@RequestBody @Valid SystemOAuth2TokenReqDTO dto) {
        SystemOAuth2ClientDomain client = clientService.validate(
                new SystemOAuth2ClientValidateReqDTO()
                        .setClientId(dto.getClientId())
                        .setSecret(dto.getClientSecret())
        );
        return CommonResult.success(grantService.check(dto.getAccessToken()));
    }

    @PostMapping("/authorization")
    @ApiOperation(value = "获得授权信息")
    @ApiImplicitParam(name = "clientId", required = true, value = "客户端编号", example = "yeyue", dataTypeClass = String.class)
    public CommonResult<Set<String>> authorize(@RequestParam String clientId) {
        LoginUser loginUser = SystemSecurityUtils.getLoginUser();
        return CommonResult.success(approveService.get(loginUser.getId(), loginUser.getUserType(), clientId, null));
    }

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
    public CommonResult<String> authorize(@RequestBody @Valid SystemOAuth2ApproveReqDTO dto) {
        //  校验用户已经登录。通过 Spring Security 实现
        LoginUser loginUser = SystemSecurityUtils.getLoginUser();

        // 参数校验
        if (StringUtils.equals(dto.getGrantType(), OAuth2GrantTypeEnum.AUTHORIZATION_CODE.getGrantType())) {
            ValidationUtils.validate(validator, dto, SystemOAuth2ApproveReqDTO.AUTHORIZATION_CODE_GROUP.class);
        }

        // 客户端校验
        SystemOAuth2ClientDomain client = clientService.validate(
                new SystemOAuth2ClientValidateReqDTO()
                        .setClientId(dto.getClientId())
                        .setAuthorizedGrantType(dto.getGrantType())
                        .setScopes(dto.getScopes())
                        .setRedirectUri(dto.getRedirectUri())
        );

        if (Objects.equals(dto.getAutoApprove(), Boolean.TRUE)) {
            // 自动授权
            if (!approveService.check(loginUser.getId(), loginUser.getUserType(), dto.getClientId(), dto.getScopes(), client)) {
                return CommonResult.error(SystemErrorCode.OAUTH2_GRANT_SCOPE_HAS_NOT_APPROVE);
            }
        } else {
            // 手动授权
            approveService.update(loginUser.getId(), loginUser.getUserType(), dto.getClientId(), dto.getScopes(), client);
        }
        switch (Objects.requireNonNull(OAuth2GrantTypeEnum.getByGranType(dto.getGrantType()))) {
            case AUTHORIZATION_CODE:
                String code = grantService.authorizationCode(dto.getClientId(), loginUser.getId(), loginUser.getUserType(), dto.getScopes(), dto.getRedirectUri(), dto.getState());
                return CommonResult.success(String.format("%s?code=%s&state=%s", dto.getRedirectUri(), code, dto.getState()));
            case IMPLICIT:
                SystemOAuth2AccessTokenDomain result = grantService.implicit(dto.getClientId(), loginUser.getId(), loginUser.getUserType(), dto.getScopes(), client);
                return CommonResult.success(JSON.toJSONString(result));
            default:
                throw new ServiceException(GlobalErrorCode.UNSUPPORTED_OPERATION);
        }
    }
}
