package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.UserTypeEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2CodeDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2CodeService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2GrantService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenRefreshReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2CodeCreateReqDTO;
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

    @Override
    public SystemOAuth2AccessTokenDomain implicit(String userId, Integer userType, String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(accessTokenValiditySeconds)
                .setRefreshTokenValiditySeconds(refreshTokenValiditySeconds));
    }

    @Override
    public String authorizationCode(String userId, Integer userType, String clientId, List<String> scopes, String redirectUri, String state, Integer codeValiditySeconds) {
        return codeService.create(new SystemOAuth2CodeCreateReqDTO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setRedirectUri(redirectUri)
                .setState(state));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain authorizationCode(String code, String clientId, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        SystemOAuth2CodeDomain codeDomain = codeService.consume(code);
        if (!StringUtils.equals(clientId, codeDomain.getClientId())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_GRANT_CLIENT_ID_MISMATCH);
        }
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(codeDomain.getUserId())
                .setUserType(codeDomain.getUserType())
                .setClientId(clientId)
                .setScopes(codeDomain.getScopes())
                .setAccessTokenValiditySeconds(accessTokenValiditySeconds)
                .setRefreshTokenValiditySeconds(refreshTokenValiditySeconds));
    }

    @Override
    public SystemOAuth2AccessTokenDomain password(String userId, Integer userType, String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(accessTokenValiditySeconds)
                .setRefreshTokenValiditySeconds(refreshTokenValiditySeconds));
    }

    @Override
    public SystemOAuth2AccessTokenDomain refresh(String refreshToken, String clientId, Integer accessTokenValiditySeconds) {
        return tokenService.refresh(new SystemOAuth2AccessTokenRefreshReqDTO()
                .setRefreshToken(refreshToken)
                .setClientId(clientId)
                .setAccessTokenValiditySeconds(accessTokenValiditySeconds));
    }

    @Override
    public SystemOAuth2AccessTokenDomain clientCredentials(String clientId, List<String> scopes, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds) {
        return tokenService.create(new SystemOAuth2AccessTokenCreateReqDTO()
                .setUserId(clientId)
                .setUserType(UserTypeEnum.SYSTEM.getValue())
                .setClientId(clientId)
                .setScopes(scopes)
                .setAccessTokenValiditySeconds(accessTokenValiditySeconds)
                .setRefreshTokenValiditySeconds(refreshTokenValiditySeconds));
    }
}
