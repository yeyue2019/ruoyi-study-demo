package yeyue.ruoyi.study.module.system.api.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 角色类型枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:55:20
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum implements EnumValuable<Integer> {

    /**
     * 系统内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    private final Integer type;

    @Override
    public Integer get() {
        return this.type;
    }
}
