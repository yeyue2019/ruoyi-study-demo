package yeyue.ruoyi.study.module.system.impl.entity.dept.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.framework.common.enums.mapstruct.BaseEnumConvert;
import yeyue.ruoyi.study.module.system.api.domain.dept.SystemPostDomain;
import yeyue.ruoyi.study.module.system.api.service.dept.dto.*;
import yeyue.ruoyi.study.module.system.impl.entity.dept.SystemPostEntity;

/**
 * @author yeyue
 * @date 2022-04-28 16:57:10
 */
@Mapper
public interface SystemPostConvert extends BaseEnumConvert {
    SystemPostConvert INSTANCE = Mappers.getMapper(SystemPostConvert.class);

    SystemPostDomain toDomain(SystemPostEntity entity);

    SystemPostEntity toEntity(SystemPostDomain domain);

    SystemPostEntity dtoToEntity(SystemPostCreateReqDTO reqDTO);

    SystemPostEntity dtoToEntity(SystemPostUpdateReqDTO reqDTO);

    SystemPostEntity dtoToEntity(SystemPostStatusUpdateReqDTO reqDTO);
}
