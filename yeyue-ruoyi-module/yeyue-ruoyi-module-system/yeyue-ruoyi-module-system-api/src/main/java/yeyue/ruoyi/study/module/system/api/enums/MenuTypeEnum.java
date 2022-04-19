package yeyue.ruoyi.study.module.system.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;
import yeyue.ruoyi.study.framework.common.validation.core.IntEnum;

/**
 * 菜单类型枚举
 *
 * @author yeyue
 * @date 2022-04-18 23:32:34
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements IntEnum {

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
    BUTTON(3)
    ;

    /**
     * 类型
     */
    @EnumValue
    private final Integer type;

    @Override
    public int[] array() {
        return new int[]{1,2,3};
    }
}
