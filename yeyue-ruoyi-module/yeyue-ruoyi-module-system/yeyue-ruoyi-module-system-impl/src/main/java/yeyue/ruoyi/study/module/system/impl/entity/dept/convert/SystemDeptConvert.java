package yeyue.ruoyi.study.module.system.impl.entity.dept.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.framework.common.enums.mapstruct.BaseEnumConvert;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemDeptDomain;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemDeptEntity;

/**
 * @author yeyue
 * @date 2022-05-17 21:24:27
 */
@Mapper
public interface SystemDeptConvert extends BaseEnumConvert {

    SystemDeptConvert INSTANCE = Mappers.getMapper(SystemDeptConvert.class);

    SystemDeptEntity toEntity(SystemDeptDomain domain);

    SystemDeptDomain toDomain(SystemDeptEntity entity);
}
