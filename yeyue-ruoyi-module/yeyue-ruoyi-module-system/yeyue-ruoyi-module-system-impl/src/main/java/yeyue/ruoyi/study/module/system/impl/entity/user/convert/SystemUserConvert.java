package yeyue.ruoyi.study.module.system.impl.entity.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.framework.common.enums.mapstruct.BaseEnumConvert;
import yeyue.ruoyi.study.module.system.api.domain.user.SystemUserDomain;
import yeyue.ruoyi.study.module.system.impl.entity.user.SystemUserEntity;

/**
 * @author yeyue
 * @date 2022-04-18 15:33:14
 */
@Mapper
public interface SystemUserConvert extends BaseEnumConvert {
    SystemUserConvert INSTANCE = Mappers.getMapper(SystemUserConvert.class);

    SystemUserDomain convert(SystemUserEntity entity);

    SystemUserEntity convert(SystemUserDomain domain);
}
