package yeyue.ruoyi.study.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 用户类型枚举
 *
 * @author yeyue
 * @date 2022-05-26 10:12:18
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements EnumValuable<Integer> {

    /**
     * 面向 c 端，普通用户
     */
    MEMBER(1, "会员"),

    /**
     * 面向 b 端，管理后台
     */
    ADMIN(2, "管理员");

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    @Override
    public Integer get() {
        return this.value;
    }
}
