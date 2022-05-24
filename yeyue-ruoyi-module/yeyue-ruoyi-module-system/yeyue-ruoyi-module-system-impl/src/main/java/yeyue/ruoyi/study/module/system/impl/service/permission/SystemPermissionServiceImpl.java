package yeyue.ruoyi.study.module.system.impl.service.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemPermissionService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuListReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemPermissionAssignMenuReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleMenuEntity;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.*;

import javax.annotation.Resource;
import java.util.List;
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
    SystemRoleMapper roleMapper;
    @Resource
    SystemRoleMenuMapper roleMenuMapper;
    @Resource
    SystemMenuService menuService;
    @Resource
    SystemUserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoleMenu(SystemPermissionAssignMenuReqDTO reqDTO) {
        // 1. 获取全部可用的菜单
        List<SystemMenuDomain> menus = menuService.list(new SystemMenuListReqDTO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        Set<Long> menuIds = CollectionUtils.funcSet(menus, SystemMenuDomain::getId);
        Set<Long> assignIds = reqDTO
                .getMenuIds()
                .stream()
                .filter(menuIds::contains)
                .collect(Collectors.toSet());
        // 2. 查询角色拥有的菜单编号
        List<SystemRoleMenuEntity> roleMenus = roleMenuMapper.selectListByRoleId(reqDTO.getRoleId());
        Set<Long> ruleIds = CollectionUtils.funcSet(roleMenus, SystemRoleMenuEntity::getMenuId);
        // 3. 计算删除和新增的菜单
        Set<Long> deleteIds = ruleIds
                .stream()
                .filter(r -> !assignIds.contains(r))
                .collect(Collectors.toSet());
        Set<Long> createIds = assignIds
                .stream()
                .filter(r -> !ruleIds.contains(r))
                .collect(Collectors.toSet());
        // 执行删除和新增的操作
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            roleMenuMapper.deleteListByRoleIdAndMenuIds(reqDTO.getRoleId(), deleteIds);
        }
        if (CollectionUtils.isNotEmpty(createIds)) {
            roleMenuMapper.insertBatchSomeColumn(CollectionUtils.funcList(createIds, id -> {
                SystemRoleMenuEntity entity = new SystemRoleMenuEntity()
                        .setMenuId(id)
                        .setRoleId(reqDTO.getRoleId());
                entity.setDeleted(false); // insertBatchSomeColumn方法有问题
                return entity;
            }));
        }
    }
}
