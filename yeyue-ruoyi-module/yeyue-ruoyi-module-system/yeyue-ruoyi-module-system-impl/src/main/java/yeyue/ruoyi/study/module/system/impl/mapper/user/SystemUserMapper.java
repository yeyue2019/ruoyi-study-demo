package yeyue.ruoyi.study.module.system.impl.mapper.user;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;

/**
 * @author yeyue
 * @date 2022-04-18 16:15:20
 */
public interface SystemUserMapper extends MyBatisMapper<SystemUserEntity> {

    default SystemUserEntity selectByUsername(String username) {
        return selectOne(SystemUserEntity::getUsername, username);
    }

    default SystemUserEntity selectByMobile(String mobile) {
        return selectOne(SystemUserEntity::getMobile, mobile);
    }

    default SystemUserEntity selectByEmail(String email) {
        return selectOne(SystemUserEntity::getEmail, email);
    }
}
