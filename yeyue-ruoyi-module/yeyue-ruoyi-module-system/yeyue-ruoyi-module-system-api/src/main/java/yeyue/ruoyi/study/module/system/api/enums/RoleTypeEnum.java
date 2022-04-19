package yeyue.ruoyi.study.module.system.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;

/**
 * 角色类型枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:55:20
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 系统内置角色
     */
    SYSTEM(1),
    /**
     * 自定义角色
     */
    CUSTOM(2);

    @EnumValue
    private final Integer type;
}
