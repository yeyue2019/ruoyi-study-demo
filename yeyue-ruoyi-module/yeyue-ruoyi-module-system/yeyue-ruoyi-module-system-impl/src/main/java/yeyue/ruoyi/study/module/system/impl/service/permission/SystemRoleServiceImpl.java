package yeyue.ruoyi.study.module.system.impl.service.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.pojo.pageable.PageResult;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.mybatis.core.query.MyBatisLambdaQueryWrapper;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.enums.permission.RoleCodeEnum;
import yeyue.ruoyi.study.module.system.api.enums.permission.RoleTypeEnum;
import yeyue.ruoyi.study.module.system.api.service.permission.SystemRoleService;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleEntity;
import yeyue.ruoyi.study.module.system.impl.entity.permission.convert.SystemRoleConvert;
import yeyue.ruoyi.study.module.system.impl.framework.exception.SystemErrorCode;
import yeyue.ruoyi.study.module.system.impl.mapper.permission.SystemRoleMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author yeyue
 * @date 2022-05-24 11:01:31
 */
@Slf4j
@Component
public class SystemRoleServiceImpl implements SystemRoleService {

    @Resource
    SystemRoleMapper roleMapper;

    @Override
    public Long create(SystemRoleCreateReqDTO reqDTO) {
        // 角色编码校验
        if (roleMapper.selectOne(SystemRoleEntity::getCode, reqDTO.getCode()) != null) {
            throw new ServiceException(SystemErrorCode.ROLE_CODE_DUPLICATE);
        }
        // 角色名称校验
        if (roleMapper.selectOne(SystemRoleEntity::getName, reqDTO.getName()) != null) {
            throw new ServiceException(SystemErrorCode.ROLE_NAME_DUPLICATE);
        }
        SystemRoleEntity entity = SystemRoleConvert.INSTANCE.toEntity(reqDTO);
        roleMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(SystemRoleUpdateReqDTO reqDTO) {
        if (roleMapper.selectById(reqDTO.getId()) == null) {
            throw new ServiceException(SystemErrorCode.ROLE_NOT_EXISTS);
        }
        SystemRoleEntity nameCompare = roleMapper.selectOne(SystemRoleEntity::getName, reqDTO.getName());
        if (nameCompare != null && !Objects.equals(nameCompare.getId(), reqDTO.getId())) {
            throw new ServiceException(SystemErrorCode.ROLE_NAME_DUPLICATE);
        }
        SystemRoleEntity entity = SystemRoleConvert.INSTANCE.toEntity(reqDTO);
        roleMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        SystemRoleEntity entity = roleMapper.selectById(id);
        if (entity == null) {
            throw new ServiceException(SystemErrorCode.ROLE_NOT_EXISTS);
        }
        if (RoleCodeEnum.isSuperAdmin(entity.getCode())) {
            throw new ServiceException(SystemErrorCode.ROLE_CAN_NOT_UPDATE_CODE_VALUE_SUPER_ADMIN);
        }
        if (EnumUtils.equals(RoleTypeEnum.SYSTEM, RoleTypeEnum::getType, entity.getType())) {
            throw new ServiceException(SystemErrorCode.ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
        roleMapper.deleteById(id);
        // TODO: 2022/5/24 删除相关数据
    }

    @Override
    public SystemRoleDomain get(Long id) {
        SystemRoleEntity entity = roleMapper.selectById(id);
        return SystemRoleConvert.INSTANCE.toDomain(entity);
    }

    @Override
    public List<SystemRoleDomain> list(SystemRoleListReqDTO reqDTO) {
        List<SystemRoleEntity> list = roleMapper.selectList(new MyBatisLambdaQueryWrapper<SystemRoleEntity>()
                .in(SystemRoleEntity::getId, reqDTO.getIds())
                .eq(SystemRoleEntity::getStatus, reqDTO.getStatus()));
        return CollectionUtils.funcList(list, SystemRoleConvert.INSTANCE::toDomain);
    }

    @Override
    public PageResult<SystemRoleDomain> list(SystemRolePageReqDTO reqDTO) {
        PageResult<SystemRoleEntity> pageResult = roleMapper.list(reqDTO);
        return CollectionUtils.funcPage(pageResult, SystemRoleConvert.INSTANCE::toDomain);
    }
}
