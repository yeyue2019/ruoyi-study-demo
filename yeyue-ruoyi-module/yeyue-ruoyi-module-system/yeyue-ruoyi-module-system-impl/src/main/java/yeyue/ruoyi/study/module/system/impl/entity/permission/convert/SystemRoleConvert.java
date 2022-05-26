package yeyue.ruoyi.study.module.system.impl.entity.permission.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import yeyue.ruoyi.study.module.system.api.domain.permission.SystemRoleDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemRoleCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemRoleUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemRoleEntity;

/**
 * @author yeyue
 * @date 2022-05-24 10:39:56
 */
@Mapper
public interface SystemRoleConvert {
    SystemRoleConvert INSTANCE = Mappers.getMapper(SystemRoleConvert.class);

    SystemRoleDomain toDomain(SystemRoleEntity entity);

    SystemRoleEntity toEntity(SystemRoleDomain domain);

    SystemRoleEntity toEntity(SystemRoleCreateReqDTO reqDTO);

    SystemRoleEntity toEntity(SystemRoleUpdateReqDTO reqDTO);

}
