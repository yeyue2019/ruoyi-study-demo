package yeyue.ruoyi.study.module.system.api.enums;

import lombok.*;
import yeyue.ruoyi.study.framework.common.util.enums.EnumUtils;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 菜单类型枚举
 *
 * @author yeyue
 * @date 2022-04-18 23:32:34
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements EnumValuable<Integer> {

    /**
     * 目录
     */
    DIR(1),
    /**
     * 菜单
     */
    MENU(2),
    /**
     * 按钮
     */
    BUTTON(3);

    /**
     * 类型
     */
    private final Integer type;

    @Override
    public Integer[] enums() {
        return EnumUtils.getArray(MenuTypeEnum.class, MenuTypeEnum::getType);
    }
}
