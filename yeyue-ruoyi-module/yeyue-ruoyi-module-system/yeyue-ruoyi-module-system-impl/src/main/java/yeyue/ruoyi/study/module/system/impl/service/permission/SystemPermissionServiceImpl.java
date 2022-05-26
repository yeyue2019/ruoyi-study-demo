package yeyue.ruoyi.study.module.system.impl.service.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemRoleService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuListReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignRoleMenuReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignUserRoleReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleMenuEntity;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemUserRoleEntity;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.SystemRoleMenuMapper;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.SystemUserRoleMapper;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-05-24 16:49:53
 */
@Slf4j
@Component
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Resource
    SystemRoleMenuMapper roleMenuMapper;
    @Resource
    SystemUserRoleMapper userRoleMapper;
    @Resource
    SystemMenuService menuService;
    @Resource
    SystemRoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoleMenu(SystemPermissionAssignRoleMenuReqDTO reqDTO) {
        if (roleService.hasAnySuperAdmin(Collections.singleton(reqDTO.getRoleId()))) {
            throw new ServiceException(SystemErrorCode.ROLE_CAN_NOT_UPDATE_CODE_VALUE_SUPER_ADMIN);
        }
        // 1. 获取全部可用的菜单
        Set<Long> menuIds = CollectionUtils.funcSet(
                menuService.list(new SystemMenuListReqDTO().setStatus(CommonStatusEnum.ENABLE.getStatus())),
                SystemMenuDomain::getId);
        Set<Long> assignIds = reqDTO.getMenuIds().stream().filter(menuIds::contains).collect(Collectors.toSet());
        // 2. 查询角色拥有的菜单编号
        Set<Long> ruleIds = CollectionUtils.funcSet(roleMenuMapper.selectListByRoleId(reqDTO.getRoleId()),
                SystemRoleMenuEntity::getMenuId);
        // 3. 计算删除和新增的菜单
        Set<Long> deleteIds = ruleIds.stream().filter(r -> !assignIds.contains(r)).collect(Collectors.toSet());
        Set<Long> createIds = assignIds.stream().filter(r -> !ruleIds.contains(r)).collect(Collectors.toSet());
        // 执行删除和新增的操作
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            roleMenuMapper.deleteListByRoleIdAndMenuIds(reqDTO.getRoleId(), deleteIds);
        }
        if (CollectionUtils.isNotEmpty(createIds)) {
            roleMenuMapper.insertBatchSomeColumn(CollectionUtils.funcList(createIds, id -> {
                SystemRoleMenuEntity entity = new SystemRoleMenuEntity().setMenuId(id).setRoleId(reqDTO.getRoleId());
                entity.setDeleted(false); // insertBatchSomeColumn方法有问题
                return entity;
            }));
        }
    }

    @Override
    public Set<Long> getRoleMenuIds(Long roleId) {
        if (roleService.hasAnySuperAdmin(Collections.singleton(roleId))) {
            return CollectionUtils.funcSet(menuService.list(new SystemMenuListReqDTO()), SystemMenuDomain::getId);
        } else {
            return CollectionUtils.funcSet(roleMenuMapper.selectListByRoleId(roleId), SystemRoleMenuEntity::getMenuId);
        }
    }

    @Override
    public Set<Long> getRoleMenuIds(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptySet();
        } else {
            return CollectionUtils.funcSet(roleMenuMapper.selectListByRoleIds(roleIds),
                    SystemRoleMenuEntity::getMenuId);
        }
    }

    @Override
    public void assignUserRole(SystemPermissionAssignUserRoleReqDTO reqDTO) {
        // 1. 获取用户拥有的角色
        Set<Long> ruleIds = CollectionUtils.funcSet(userRoleMapper.selectListByUserId(reqDTO.getUserId()),
                SystemUserRoleEntity::getRoleId);
        // 3. 计算删除和新增的菜单
        Set<Long> deleteIds =
                ruleIds.stream().filter(r -> !reqDTO.getRoleIds().contains(r)).collect(Collectors.toSet());
        Set<Long> createIds =
                reqDTO.getRoleIds().stream().filter(r -> !ruleIds.contains(r)).collect(Collectors.toSet());
        // 执行删除和新增的操作
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            userRoleMapper.deleteListByUserIdAndRoleIds(reqDTO.getUserId(), deleteIds);
        }
        if (CollectionUtils.isNotEmpty(createIds)) {
            userRoleMapper.insertBatchSomeColumn(CollectionUtils.funcList(createIds, id -> {
                SystemUserRoleEntity entity = new SystemUserRoleEntity().setRoleId(id).setUserId(reqDTO.getUserId());
                entity.setDeleted(false); // insertBatchSomeColumn方法有问题
                return entity;
            }));
        }
    }

    @Override
    public Set<Long> getUserRoleIds(Long userId) {
        return CollectionUtils.funcSet(userRoleMapper.selectListByUserId(userId), SystemUserRoleEntity::getRoleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processRoleDeleted(Long roleId) {
        userRoleMapper.deleteListByRoleId(roleId);
        roleMenuMapper.deleteListByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processMenuDeleted(Long menuId) {
        roleMenuMapper.deleteListByMenuId(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processUserDeleted(Long userId) {
        userRoleMapper.deleteListByUserId(userId);
    }
}
