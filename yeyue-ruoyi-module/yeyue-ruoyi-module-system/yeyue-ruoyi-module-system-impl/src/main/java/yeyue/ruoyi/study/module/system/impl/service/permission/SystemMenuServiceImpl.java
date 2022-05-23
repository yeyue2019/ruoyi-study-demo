package yeyue.ruoyi.study.module.system.impl.service.permission;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.enums.CommonStatusEnum;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.enums.permission.*;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptListReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemMenuService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemMenuEntity;
import yeyue.ruoyi.study.module.system.impl.entity.permission.convert.SystemMenuConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.SystemMenuMapper;

import javax.annotation.Resource;
import java.util.*;

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
        // 名称校验
        if (menuMapper.selectByParentIdAndName(reqDTO.getParentId(), reqDTO.getName()) != null) {
            throw new ServiceException(SystemErrorCode.MENU_NAME_DUPLICATE);
        }
        // 父菜单校验
        if (MenuIdEnum.ROOT.getId().compareTo(reqDTO.getParentId()) != 0) {
            // 父菜单不存在
            SystemMenuEntity menu = menuMapper.selectById(reqDTO.getParentId());
            if (menu == null) {
                throw new ServiceException(SystemErrorCode.MENU_PARENT_NOT_EXISTS);
            }
            // 父菜单被禁用
            if (EnumUtils.notEquals(CommonStatusEnum.ENABLE, CommonStatusEnum::getStatus, menu.getStatus())) {
                throw new ServiceException(SystemErrorCode.MENU_NOT_EXISTS);
            }
            // 父菜单必须是目录或者菜单类型
            if (EnumUtils.containsNone(menu.getType(), MenuTypeEnum::getType, MenuTypeEnum.DIR, MenuTypeEnum.MENU)) {
                throw new ServiceException(SystemErrorCode.MENU_PARENT_NOT_DIR_OR_MENU);
            }
        }
        SystemMenuEntity entity = SystemMenuConvert.INSTANCE.toEntity(reqDTO);
        initMenuProperty(entity);
        menuMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemMenuUpdateReqDTO reqDTO) {
        if (menuMapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.MENU_NOT_EXISTS);
        }
        SystemMenuEntity nameCompare = menuMapper.selectByParentIdAndName(reqDTO.getParentId(), reqDTO.getName());
        if (nameCompare != null && !Objects.equals(nameCompare.getId(), reqDTO.getId())) {
            throw new ServiceException(SystemErrorCode.MENU_NAME_DUPLICATE);
        }
        SystemMenuEntity entity = SystemMenuConvert.INSTANCE.toEntity(reqDTO);
        initMenuProperty(entity);
        menuMapper.updateById(entity);
    }

    @Override
    public SystemMenuDomain get(Long id) {
        SystemMenuEntity entity = menuMapper.selectById(id);
        return SystemMenuConvert.INSTANCE.toDomain(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (menuMapper.selectById(id) == null) {
            throw new ServiceException(SystemErrorCode.MENU_NOT_EXISTS);
        }
        if (menuMapper.selectCount(SystemMenuEntity::getParentId, id) > 0) {
            throw new ServiceException(SystemErrorCode.MENU_EXISTS_CHILDREN);
        }
        menuMapper.deleteById(id);
        // TODO: 2022/5/23 角色权限关联表删除
    }

    @Override
    public List<SystemMenuDomain> list(SystemMenuListReqDTO reqDTO) {
        List<SystemMenuEntity> list = menuMapper.selectList(new MyBatisLambdaQueryWrapper<SystemMenuEntity>().eq(SystemMenuEntity::getStatus, reqDTO.getStatus()));
        return CollectionUtils.convertList(list, SystemMenuConvert.INSTANCE::toDomain);
    }

    /**
     * 初始化菜单的通用属性。
     * <p>
     * 例如说，只有目录或者菜单类型的菜单，才设置 icon
     *
     * @param menu 菜单
     */
    private void initMenuProperty(SystemMenuEntity menu) {
        // 菜单为按钮类型时，无需 component、icon、path 属性，进行置空
        if (EnumUtils.equals(MenuTypeEnum.BUTTON, MenuTypeEnum::getType, menu.getType())) {
            menu.setComponent(StringUtils.EMPTY);
            menu.setIcon(StringUtils.EMPTY);
            menu.setPath(StringUtils.EMPTY);
        }
    }
}