package yeyue.ruoyi.study.module.system.api.enums.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 性别枚举
 *
 * @author yeyue
 * @date 2022-04-18 14:36:58
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements StringEnum {

    MALE("M", "女"),
    FEMALE("F", "男"),
    UNKNOWN("O", "未知");

    @EnumValue
    private final String gender;

    private final String name;

    @Override
    public String[] array() {
        return new String[]{MALE.gender, FEMALE.gender, UNKNOWN.gender};
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
