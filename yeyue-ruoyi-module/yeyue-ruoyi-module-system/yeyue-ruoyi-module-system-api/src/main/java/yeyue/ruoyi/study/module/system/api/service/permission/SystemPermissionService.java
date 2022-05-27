package yeyue.ruoyi.study.module.system.api.service.permission;

import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
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
     * @param status 菜单状态
     * @return 菜单集合
     */
    Set<SystemMenuDomain> getRoleMenuIds(Long roleId, Integer status);

    /**
     * 获取角色们所拥有的菜单
     *
     * @param roleIds 角色集合
     * @return 菜单集合
     */
    Set<SystemMenuDomain> getRoleMenuIds(Collection<Long> roleIds, Integer status);

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
    Set<SystemRoleDomain> getUserRoleIds(Long userId, Integer status);

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

    /**
     * 用户是否拥有权限
     *
     * @param userId      用户Id
     * @param all         是否全部权限
     * @param permissions 权限
     * @return 结果
     */
    boolean hasPermissions(Long userId, boolean all, String... permissions);
}
