package yeyue.ruoyi.study.module.system.impl.mapper.oauth2;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.impl.entity.oauth2.SystemOAuth2ApproveEntity;

import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-26 16:25:17
 */
public interface SystemOAuth2ApproveMapper extends MyBatisMapper<SystemOAuth2ApproveEntity> {

    default List<SystemOAuth2ApproveEntity> selectListByUserIdAndUserTypeAndClientId(String userId, Integer userType, String clientId) {
        return selectList(new MyBatisLambdaQueryWrapper<SystemOAuth2ApproveEntity>()
                .eq(SystemOAuth2ApproveEntity::getUserId, userId)
                .eq(SystemOAuth2ApproveEntity::getUserType, userType)
                .eq(SystemOAuth2ApproveEntity::getClientId, clientId));
    }

    default void deleteByByUserIdAndUserTypeAndClientId(String userId, Integer userType, String clientId) {
        delete(new MyBatisLambdaQueryWrapper<SystemOAuth2ApproveEntity>()
                .eq(SystemOAuth2ApproveEntity::getUserId, userId)
                .eq(SystemOAuth2ApproveEntity::getUserType, userType)
                .eq(SystemOAuth2ApproveEntity::getClientId, clientId));
    }
}
