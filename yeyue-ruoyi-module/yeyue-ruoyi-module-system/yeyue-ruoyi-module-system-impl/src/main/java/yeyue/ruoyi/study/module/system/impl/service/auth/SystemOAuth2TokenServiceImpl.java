package yeyue.ruoyi.study.module.system.impl.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.domain.auth.SystemOAuth2ClientDomain;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2ClientService;
import yeyue.ruoyi.study.module.system.api.service.auth.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.auth.dto.SystemOAuth2AccessTokenRefreshReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2AccessTokenEntity;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2RefreshTokenEntity;
import yeyue.ruoyi.study.module.system.impl.entity.auth.convert.SystemOAuth2AccessTokenConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.auth.SystemOAuth2AccessTokenMapper;
import yeyue.ruoyi.study.module.system.impl.mapper.auth.SystemOAuth2RefreshTokenMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yeyue
 * @date 2022-05-17 15:44:09
 */
@Slf4j
@Component
public class SystemOAuth2TokenServiceImpl implements SystemOAuth2TokenService {

    @Resource
    SystemOAuth2ClientService clientService;
    @Resource
    SystemOAuth2AccessTokenMapper accessTokenMapper;
    @Resource
    SystemOAuth2RefreshTokenMapper refreshTokenMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain create(SystemOAuth2AccessTokenCreateReqDTO reqDTO) {
        // 1. 校验客户端
        SystemOAuth2ClientDomain client = clientService.getByClientId(reqDTO.getClientId());
        // 2. 生成刷新令牌
        SystemOAuth2RefreshTokenEntity refreshToken = createRefreshToken(client, reqDTO.getUserId());
        // 3. 生成访问令牌
        SystemOAuth2AccessTokenEntity accessToken = createAccessToken(client, refreshToken);
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(accessToken);
    }

    @Override
    public SystemOAuth2AccessTokenDomain refresh(SystemOAuth2AccessTokenRefreshReqDTO reqDTO) {
        // 1. 校验客户端
        SystemOAuth2ClientDomain client = clientService.getByClientId(reqDTO.getClientId());
        // 2. 获取刷新令牌
        SystemOAuth2RefreshTokenEntity refreshToken = refreshTokenMapper.selectByRefreshToken(reqDTO.getRefreshToken());
        if (refreshToken == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_NOT_EXISTS);
        }
        if (!StringUtils.equals(refreshToken.getClientId(), reqDTO.getClientId())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_UNSUPPORTED_CLIENT);
        }
        if (refreshToken.getExpiresTime() != null && refreshToken
                .getExpiresTime()
                .isBefore(LocalDateTime.now())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_EXPIRES);
        }
        // 3. 创建访问令牌
        SystemOAuth2AccessTokenEntity accessToken = createAccessToken(client, refreshToken);
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(accessToken);
    }

    @Override
    public SystemOAuth2AccessTokenDomain get(String accessToken) {
        // 1. 获取访问令牌
        SystemOAuth2AccessTokenEntity entity = accessTokenMapper.selectByAccessToken(accessToken);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_ACCESS_TOKEN_NOT_EXISTS);
        }
        if (entity.getExpiresTime() != null && entity
                .getExpiresTime()
                .isBefore(LocalDateTime.now())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_ACCESS_TOKEN_EXPIRES);
        }
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain remove(String accessToken) {
        // 1. 获取访问令牌
        SystemOAuth2AccessTokenEntity entity = accessTokenMapper.selectByAccessToken(accessToken);
        // 2. 移除访问令牌
        if (entity == null) {
            return null;
        }
        accessTokenMapper.deleteById(entity);
        // 3. 移除刷新令牌
        SystemOAuth2RefreshTokenEntity refreshToken = refreshTokenMapper.selectByRefreshToken(entity.getRefreshToken());
        if (refreshToken != null) {
            refreshTokenMapper.deleteById(refreshToken);
        }
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(entity);
    }

    private SystemOAuth2RefreshTokenEntity createRefreshToken(SystemOAuth2ClientDomain client, String userId) {
        SystemOAuth2RefreshTokenEntity entity = new SystemOAuth2RefreshTokenEntity();
        entity.setClientId(client.getClientId());
        entity.setUserId(userId);
        entity.setRefreshToken(IdUtils.uuid(true));
        if (client.getRefreshTokenValiditySeconds() != null && client.getRefreshTokenValiditySeconds() > 0) {
            entity.setExpiresTime(LocalDateTime
                    .now()
                    .plusSeconds(client.getRefreshTokenValiditySeconds()));
        }
        refreshTokenMapper.insert(entity);
        return entity;
    }

    private SystemOAuth2AccessTokenEntity createAccessToken(SystemOAuth2ClientDomain client, SystemOAuth2RefreshTokenEntity refreshToken) {
        SystemOAuth2AccessTokenEntity entity = new SystemOAuth2AccessTokenEntity();
        entity.setClientId(client.getClientId());
        entity.setUserId(refreshToken.getUserId());
        entity.setRefreshToken(refreshToken.getRefreshToken());
        entity.setAccessToken(IdUtils.uuid(true));
        if (client.getAccessTokenValiditySeconds() != null && client.getAccessTokenValiditySeconds() > 0) {
            entity.setExpiresTime(LocalDateTime
                    .now()
                    .plusSeconds(client.getAccessTokenValiditySeconds()));
        }
        accessTokenMapper.insert(entity);
        return entity;
    }
}
