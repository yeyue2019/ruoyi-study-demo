package yeyue.ruoyi.study.module.system.impl.entity.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yeyue.ruoyi.study.framework.common.enums.*;
import yeyue.ruoyi.study.module.system.api.domain.user.AdminUserDomain;
import yeyue.ruoyi.study.module.system.impl.entity.user.AdminUserEntity;

/**
 * @author yeyue
 * @date 2022-04-18 15:33:14
 */
@Mapper
public interface AdminUserConvert {
    AdminUserConvert INSTANCE = Mappers.getMapper(AdminUserConvert.class);

    AdminUserDomain convert(AdminUserEntity entity);

    AdminUserEntity convert(AdminUserDomain domain);

    default GenderEnum genderConvert(String gender) {
        return GenderEnum.toEnum(gender);
    }

    default String genderConvert(GenderEnum gender) {
        if (gender == null) {
            return null;
        }
        return gender.getGender();
    }

    default CommonStatusEnum statusConvert(Integer status) {
        return CommonStatusEnum.toEnum(status);
    }

    default Integer customConvert(CommonStatusEnum statusEnum) {
        if (statusEnum == null) {
            return null;
        }
        return statusEnum.getStatus();
    }
}
