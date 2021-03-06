package yeyue.ruoyi.study.module.system.impl.mapper.oauth2;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2ClientEntity;

/**
 * @author yeyue
 * @date 2022-05-17 09:31:41
 */
public interface SystemOAuth2ClientMapper extends MyBatisMapper<SystemOAuth2ClientEntity> {

    default SystemOAuth2ClientEntity selectByClientId(String clientId) {
        return selectOne(SystemOAuth2ClientEntity::getClientId, clientId);
    }
}
