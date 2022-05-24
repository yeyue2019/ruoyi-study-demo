package yeyue.ruoyi.study.module.system.impl.mapper.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.mybatis.core.mapper.MyBatisMapper;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleMenuEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author yeyue
 * @date 2022-05-24 16:42:08
 */
public interface SystemRoleMenuMapper extends MyBatisMapper<SystemRoleMenuEntity> {

    default List<SystemRoleMenuEntity> selectListByRoleId(Long roleId) {
        return selectList(new LambdaQueryWrapper<SystemRoleMenuEntity>().eq(SystemRoleMenuEntity::getRoleId, roleId));
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        if (roleId == null || CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        delete(new MyBatisLambdaQueryWrapper<SystemRoleMenuEntity>()
                .eq(SystemRoleMenuEntity::getRoleId, roleId)
                .in(SystemRoleMenuEntity::getMenuId, menuIds));
    }
}
