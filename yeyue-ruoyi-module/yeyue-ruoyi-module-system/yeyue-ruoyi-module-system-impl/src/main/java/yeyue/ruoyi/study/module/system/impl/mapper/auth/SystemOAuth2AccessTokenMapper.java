package yeyue.ruoyi.study.module.system.impl.mapper.auth;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.auth.SystemOAuth2AccessTokenEntity;

/**
 * @author yeyue
 * @date 2022-05-17 15:46:05
 */
public interface SystemOAuth2AccessTokenMapper extends MyBatisMapper<SystemOAuth2AccessTokenEntity> {

    default SystemOAuth2AccessTokenEntity selectByAccessToken(String accessToken) {
        return selectOne(SystemOAuth2AccessTokenEntity::getAccessToken, accessToken);
    }
}
