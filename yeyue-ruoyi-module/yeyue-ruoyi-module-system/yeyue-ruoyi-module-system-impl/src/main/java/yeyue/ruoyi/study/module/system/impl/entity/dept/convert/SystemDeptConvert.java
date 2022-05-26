package yeyue.ruoyi.study.module.system.impl.entity.dept.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.SystemDeptUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemDeptEntity;

/**
 * @author yeyue
 * @date 2022-05-17 21:24:27
 */
@Mapper
public interface SystemDeptConvert {
    SystemDeptConvert INSTANCE = Mappers.getMapper(SystemDeptConvert.class);

    SystemDeptEntity toEntity(SystemDeptDomain domain);

    SystemDeptDomain toDomain(SystemDeptEntity entity);

    SystemDeptEntity toEntity(SystemDeptCreateReqDTO reqDTO);

    SystemDeptEntity toEntity(SystemDeptUpdateReqDTO reqDTO);
}
