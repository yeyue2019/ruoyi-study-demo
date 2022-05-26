package yeyue.ruoyi.study.module.system.impl.mapper.oauth2;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2RefreshTokenEntity;

/**
 * @author yeyue
 * @date 2022-05-17 15:46:39
 */
public interface SystemOAuth2RefreshTokenMapper extends MyBatisMapper<SystemOAuth2RefreshTokenEntity> {

    default SystemOAuth2RefreshTokenEntity selectByRefreshToken(String refreshToken) {
        return selectOne(SystemOAuth2RefreshTokenEntity::getRefreshToken, refreshToken);
    }
}
