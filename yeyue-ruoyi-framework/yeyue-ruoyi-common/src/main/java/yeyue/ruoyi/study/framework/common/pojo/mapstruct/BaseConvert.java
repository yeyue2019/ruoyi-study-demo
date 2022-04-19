package yeyue.ruoyi.study.framework.common.pojo.mapstruct;

import yeyue.ruoyi.study.framework.common.enums.*;

/**
 * 基本转化类
 *
 * @author yeyue
 * @date 2022-04-18 23:48:37
 */
public interface BaseConvert {

    default GenderEnum convertGender(String gender) {
        return GenderEnum.toEnum(gender);
    }

    default String convertGender(GenderEnum gender) {
        if (gender == null) {
            return null;
        }
        return gender.getGender();
    }

    default CommonStatusEnum convertStatus(Integer status) {
        return CommonStatusEnum.toEnum(status);
    }

    default Integer convertStatus(CommonStatusEnum statusEnum) {
        if (statusEnum == null) {
            return null;
        }
        return statusEnum.getStatus();
    }
}
