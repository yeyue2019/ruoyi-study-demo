package yeyue.ruoyi.study.module.system.impl.mapper.oauth2;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2CodeEntity;

/**
 * @author yeyue
 * @date 2022-05-17 13:05:20
 */
public interface SystemOAuth2CodeMapper extends MyBatisMapper<SystemOAuth2CodeEntity> {

    default SystemOAuth2CodeEntity selectByCode(String code) {
        return selectOne(SystemOAuth2CodeEntity::getCode, code);
    }
}
