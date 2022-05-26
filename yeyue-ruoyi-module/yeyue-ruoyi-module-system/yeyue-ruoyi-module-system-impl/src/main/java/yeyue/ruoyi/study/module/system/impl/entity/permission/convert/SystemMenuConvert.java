package yeyue.ruoyi.study.module.system.impl.entity.permission.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.module.system.api.domain.permission.SystemMenuDomain;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuCreateReqDTO;
import yeyue.ruoyi.study.module.system.api.service.permission.dto.SystemMenuUpdateReqDTO;
import yeyue.ruoyi.study.module.system.impl.entity.permission.SystemMenuEntity;

/**
 * @author yeyue
 * @date 2022-05-23 15:13:08
 */
@Mapper
public interface SystemMenuConvert {
    SystemMenuConvert INSTANCE = Mappers.getMapper(SystemMenuConvert.class);

    SystemMenuEntity toEntity(SystemMenuDomain domain);

    SystemMenuDomain toDomain(SystemMenuEntity entity);

    SystemMenuEntity toEntity(SystemMenuCreateReqDTO reqDTO);

    SystemMenuEntity toEntity(SystemMenuUpdateReqDTO reqDTO);
}
