package yeyue.ruoyi.study.module.system.api.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 性别枚举
 *
 * @author yeyue
 * @date 2022-04-18 14:36:58
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements EnumValuable<String> {

    MALE("M", "女"), FEMALE("F", "男"), UNKNOWN("O", "未知");

    private final String gender;

    private final String name;

    @Override
    public String get() {
        return this.gender;
    }
}
