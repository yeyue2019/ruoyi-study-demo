package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.UserTypeEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2CodeDomain;
import yeyue.ruoyi.study.module.system.api.enums.oauth2.OAuth2GrantTypeEnum;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2CodeService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2GrantService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenRefreshReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2ClientValidateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2CodeCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.user.SystemUserService;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-26 14:49:25
 */
@Slf4j
@Component
public class SystemOAuth2GrantServiceImpl implements SystemOAuth2GrantService {

    @Resource
    SystemOAuth2TokenService tokenService;
    @Resource
    SystemOAuth2CodeService codeService;
    @Resource
    SystemOAuth2ClientService clientService;

    @Resource
    SystemUserService userService;

    @Override
    public SystemOAuth2AccessTokenDomain implicit(String clientId, String userId, Integer userType, List<String> scopes, SystemOAuth2ClientDomain client) {
        // 1. 客户端校验
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setAuthorizedGrantType(OAuth2GrantTypeEnum.IMPLICIT.getGrantType())
                            .setScopes(scopes)
            );
        }
        // 2. 生成访问令牌
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                .setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds()));
    }

    @Override
    public String authorizationCode(String clientId, String userId, Integer userType, List<String> scopes, String redirectUri, String state) {
        // 1. 生成授权码
        return codeService.create(new SystemOAuth2CodeCreateReqDTO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setRedirectUri(redirectUri)
                .setState(state)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain authorizationCode(String clientId, String code, SystemOAuth2ClientDomain client) {
        // 1. 客户端校验
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setAuthorizedGrantType(OAuth2GrantTypeEnum.AUTHORIZATION_CODE.getGrantType())
            );
        }
        // 2. 验证码使用
        SystemOAuth2CodeDomain codeDomain = codeService.consume(code);
        if (!StringUtils.equals(clientId, codeDomain.getClientId())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_GRANT_CLIENT_ID_MISMATCH);
        }
        // 3. 生成访问令牌
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(codeDomain.getUserId())
                .setUserType(codeDomain.getUserType())
                .setClientId(codeDomain.getClientId())
                .setScopes(codeDomain.getScopes())
                .setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                .setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds()));
    }

    @Override
    public SystemOAuth2AccessTokenDomain password(String clientId, String username, String password, List<String> scopes, SystemOAuth2ClientDomain client) {
        // 1. 客户端校验
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setAuthorizedGrantType(OAuth2GrantTypeEnum.PASSWORD.getGrantType())
                            .setScopes(scopes)
            );
        }
        // 2. 用户密码校验
        Long userId = userService.authenticate(username, password);
        // 3. 创建访问令牌
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(String.valueOf(userId))
                .setUserType(UserTypeEnum.ADMIN.getValue())
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                .setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds()));
    }

    @Override
    public SystemOAuth2AccessTokenDomain refresh(String clientId, String refreshToken, SystemOAuth2ClientDomain client) {
        // 1. 客户端校验
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setAuthorizedGrantType(OAuth2GrantTypeEnum.REFRESH_TOKEN.getGrantType())
            );
        }
        return tokenService.refresh(new SystemOAuth2AccessTokenRefreshReqDTO()
                .setRefreshToken(refreshToken)
                .setClientId(clientId)
                .setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds()));
    }

    @Override
    public SystemOAuth2AccessTokenDomain clientCredentials(String clientId, List<String> scopes, SystemOAuth2ClientDomain client) {
        // 1. 客户端校验
        if (client == null) {
            client = clientService.validate(
                    new SystemOAuth2ClientValidateReqDTO()
                            .setClientId(clientId)
                            .setAuthorizedGrantType(OAuth2GrantTypeEnum.CLIENT_CREDENTIALS.getGrantType())
                            .setScopes(scopes)
            );
        }
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(clientId)
                .setUserType(UserTypeEnum.SYSTEM.getValue())
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                .setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds()));
    }

    @Override
    public void remove(String accessToken) {
        tokenService.remove(accessToken);
    }

    @Override
    public SystemOAuth2AccessTokenDomain check(String accessToken) {
        return tokenService.get(accessToken);
    }
}
