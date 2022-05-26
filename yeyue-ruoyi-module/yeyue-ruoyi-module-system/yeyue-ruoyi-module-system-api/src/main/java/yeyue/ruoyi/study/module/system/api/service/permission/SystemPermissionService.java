package yeyue.ruoyi.study.module.system.api.service.permission;

import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignRoleMenuReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignUserRoleReqDTO;

import java.util.Collection;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-24 16:42:47
 */
public interface SystemPermissionService {

    /**
     * 赋予角色权限
     *
     * @param reqDTO 信息
     */
    void assignRoleMenu(SystemPermissionAssignRoleMenuReqDTO reqDTO);

    /**
     * 获取角色所拥有的菜单
     *
     * @param roleId 角色id
     * @return 菜单集合
     */
    Set<Long> getRoleMenuIds(Long roleId);

    /**
     * 获取角色们所拥有的菜单
     *
     * @param roleIds 角色集合
     * @return 菜单集合
     */
    Set<Long> getRoleMenuIds(Collection<Long> roleIds);

    /**
     * 服务用户角色
     *
     * @param reqDTO 请求信息
     */
    void assignUserRole(SystemPermissionAssignUserRoleReqDTO reqDTO);

    /**
     * 获取用户拥有的角色
     *
     * @param userId 用户Id
     * @return 角色Id
     */
    Set<Long> getUserRoleIds(Long userId);

    /**
     * 删除角色时的互联操作
     *
     * @param roleId 角色Id
     */
    void processRoleDeleted(Long roleId);

    /**
     * 删除菜单时的互联操作
     *
     * @param menuId 菜单Id
     */
    void processMenuDeleted(Long menuId);

    /**
     * 删除用户时的互联操作
     *
     * @param userId 用户Id
     */
    void processUserDeleted(Long userId);
}
