package yeyue.ruoyi.study.framework.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.validation.core.StringEnum;

import java.util.Arrays;

/**
 * 性别枚举
 *
 * @author yeyue
 * @date 2022-04-18 14:36:58
 */
@AllArgsConstructor
@Getter
public enum GenderEnum implements StringEnum {

    MALE("M"),
    FEMALE("F"),
    UNKNOWN("O");

    @EnumValue
    private final String gender;

    @Override
    public String[] array() {
        return Arrays.stream(values()).map(GenderEnum::getGender).toArray(String[]::new);
    }

    public static GenderEnum toEnum(String gender) {
        for (GenderEnum enums : GenderEnum.values()) {
            if (StringUtils.equals(enums.gender, gender)) {
                return enums;
            }
        }
        return null;
    }
}
