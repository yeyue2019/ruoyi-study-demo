package yeyue.ruoyi.study.module.system.impl.service.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.oauth2.SystemOAuth2AccessTokenDomain;
import yeyue.ruoyi.study.module.system.api.service.oauth2.SystemOAuth2TokenService;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenPageReqDTO;
import yeyue.ruoyi.study.module.system.api.service.oauth2.dto.SystemOAuth2AccessTokenRefreshReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2AccessTokenEntity;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2RefreshTokenEntity;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.convert.SystemOAuth2AccessTokenConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.oauth2.SystemOAuth2AccessTokenMapper;
import yeyue.ruoyi.study.module.system.impl.mapper.oauth2.SystemOAuth2RefreshTokenMapper;

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
    SystemOAuth2AccessTokenMapper accessTokenMapper;
    @Resource
    SystemOAuth2RefreshTokenMapper refreshTokenMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemOAuth2AccessTokenDomain create(SystemOAuth2AccessTokenCreateReqDTO reqDTO) {
        // 1. 生成刷新令牌
        SystemOAuth2RefreshTokenEntity refreshToken = createRefreshToken(reqDTO);
        // 2. 生成访问令牌
        SystemOAuth2AccessTokenEntity accessToken = createAccessToken(refreshToken, reqDTO.getAccessTokenValiditySeconds());
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(accessToken);
    }

    @Override
    public SystemOAuth2AccessTokenDomain refresh(SystemOAuth2AccessTokenRefreshReqDTO reqDTO) {
        // 1. 获取刷新令牌
        SystemOAuth2RefreshTokenEntity refreshToken = refreshTokenMapper.selectByRefreshToken(reqDTO.getRefreshToken());
        if (refreshToken == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_NOT_EXISTS);
        }
        if (!StringUtils.equals(refreshToken.getClientId(), reqDTO.getClientId())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_UNSUPPORTED_CLIENT);
        }
        if (refreshToken.getExpiresTime() != null && refreshToken.getExpiresTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(SystemErrorCode.OAUTH2_REFRESH_TOKEN_EXPIRES);
        }
        // 2. 创建访问令牌
        SystemOAuth2AccessTokenEntity accessToken = createAccessToken(refreshToken, reqDTO.getAccessTokenValiditySeconds());
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(accessToken);
    }

    @Override
    public SystemOAuth2AccessTokenDomain get(String accessToken) {
        // 1. 获取访问令牌
        SystemOAuth2AccessTokenEntity entity = accessTokenMapper.selectByAccessToken(accessToken);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.OAUTH2_ACCESS_TOKEN_NOT_EXISTS);
        }
        if (entity.getExpiresTime() != null && entity.getExpiresTime().isBefore(LocalDateTime.now())) {
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
        return SystemOAuth2AccessTokenConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public PageResult<SystemOAuth2AccessTokenDomain> list(SystemOAuth2AccessTokenPageReqDTO reqDTO) {
        PageResult<SystemOAuth2AccessTokenEntity> pageResult = accessTokenMapper.selectPage(reqDTO, new MyBatisLambdaQueryWrapper<>());
        return CollectionUtils.funcPage(pageResult, SystemOAuth2AccessTokenConvert.INSTANCE::toDomain);
    }

    private SystemOAuth2RefreshTokenEntity createRefreshToken(SystemOAuth2AccessTokenCreateReqDTO reqDTO) {
        SystemOAuth2RefreshTokenEntity entity = new SystemOAuth2RefreshTokenEntity();
        entity.setClientId(reqDTO.getClientId());
        entity.setScopes(reqDTO.getScopes());
        entity.setUserId(reqDTO.getUserId());
        entity.setUserType(reqDTO.getUserType());
        entity.setRefreshToken(IdUtils.uuid(true));
        if (reqDTO.getRefreshTokenValiditySeconds() != null && reqDTO.getRefreshTokenValiditySeconds() > 0) {
            entity.setExpiresTime(LocalDateTime.now().plusSeconds(reqDTO.getRefreshTokenValiditySeconds()));
        }
        refreshTokenMapper.insert(entity);
        return entity;
    }

    private SystemOAuth2AccessTokenEntity createAccessToken(SystemOAuth2RefreshTokenEntity refreshToken, Integer accessTokenValiditySeconds) {
        SystemOAuth2AccessTokenEntity entity = new SystemOAuth2AccessTokenEntity();
        entity.setClientId(refreshToken.getClientId());
        entity.setUserId(refreshToken.getUserId());
        entity.setUserType(refreshToken.getUserType());
        entity.setScopes(refreshToken.getScopes());
        entity.setRefreshToken(refreshToken.getRefreshToken());
        entity.setAccessToken(IdUtils.uuid(true));
        if (accessTokenValiditySeconds != null && accessTokenValiditySeconds > 0) {
            entity.setExpiresTime(LocalDateTime.now().plusSeconds(accessTokenValiditySeconds));
        }
        accessTokenMapper.insert(entity);
        return entity;
    }
}
