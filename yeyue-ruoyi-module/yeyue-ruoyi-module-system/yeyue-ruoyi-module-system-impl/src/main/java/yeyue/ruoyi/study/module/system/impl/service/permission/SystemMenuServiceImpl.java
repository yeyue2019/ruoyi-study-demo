package yeyue.ruoyi.study.module.system.impl.service.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.enums.dept.DeptIdEnum;
import yeyue.ruoyi.study.module.system.api.enums.permission.MenuIdEnum;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemMenuEntity;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.SystemMenuMapper;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yeyue
 * @date 2022-05-23 15:35:16
 */
@Slf4j
@Component
public class SystemMenuServiceImpl implements SystemMenuService {

    @Resource
    SystemMenuMapper menuMapper;

    @Override
    public Long create(SystemMenuCreateReqDTO reqDTO) {
        if (reqDTO.getParentId() == null) {
            reqDTO.setParentId(MenuIdEnum.ROOT.getId());
        }
    }

    @Override
    public void update(SystemMenuUpdateReqDTO reqDTO) {

    }

    @Override
    public SystemMenuDomain get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private void checkParentMenuEnable(Long parentId, Long childId) {
        if (Objects.equals(parentId, childId)) {
            throw new ServiceException(SystemErrorCode.MENU_PARENT_ERROR);
        }
        if (MenuIdEnum.ROOT.getId().compareTo(parentId) != 0) {
            // 父菜单不存在
            SystemMenuEntity menu = menuMapper.selectById(parentId);
            if (menu == null) {
                throw new ServiceException(SystemErrorCode.MENU_PARENT_NOT_EXISTS);
            }
            // 父菜单被禁用
            if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, menu.getStatus())) {
                throw new ServiceException(SystemErrorCode.MENU_NOT_EXISTS);
            }
        }
    }
}
