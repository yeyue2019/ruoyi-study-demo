package yeyue.ruoyi.study.module.system.impl.mapper.permission;

import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemUserRoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-24 16:41:32
 */
public interface SystemUserRoleMapper extends MyBatisMapper<SystemUserRoleEntity> {

    default List<SystemUserRoleEntity> selectListByUserId(Long userId) {
        return selectList(new MyBatisLambdaQueryWrapper<SystemUserRoleEntity>().eq(SystemUserRoleEntity::getUserId, userId));
    }

    default void deleteListByUserIdAndRoleIds(Long userId, Collection<Long> ruleIds) {
        delete(new MyBatisLambdaQueryWrapper<SystemUserRoleEntity>()
                .eq(SystemUserRoleEntity::getUserId, userId)
                .in(SystemUserRoleEntity::getRoleId, ruleIds));
    }
}
