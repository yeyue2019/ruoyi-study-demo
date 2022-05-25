package yeyue.ruoyi.study.module.system.impl.mapper.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    default List<SystemRoleMenuEntity> selectListByRoleIds(Collection<Long> roleIds) {
        return selectList(new LambdaQueryWrapper<SystemRoleMenuEntity>().in(SystemRoleMenuEntity::getRoleId, roleIds));
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(new MyBatisLambdaQueryWrapper<SystemRoleMenuEntity>()
                .eq(SystemRoleMenuEntity::getRoleId, roleId)
                .in(SystemRoleMenuEntity::getMenuId, menuIds));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(new MyBatisLambdaQueryWrapper<SystemRoleMenuEntity>().eq(SystemRoleMenuEntity::getRoleId, roleId));
    }

    default void deleteListByMenuId(Long menuId) {
        delete(new MyBatisLambdaQueryWrapper<SystemRoleMenuEntity>().eq(SystemRoleMenuEntity::getMenuId, menuId));
    }
}
