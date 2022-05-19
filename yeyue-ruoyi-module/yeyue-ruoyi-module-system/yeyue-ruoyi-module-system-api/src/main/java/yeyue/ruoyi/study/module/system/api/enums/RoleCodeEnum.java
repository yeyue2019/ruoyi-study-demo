package yeyue.ruoyi.study.module.system.api.enums;

import lombok.*;

/**
 * 角色标识枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:51:09
 */
@Getter
@AllArgsConstructor
public enum RoleCodeEnum {

    SUPER_ADMIN("super_admin", "超级管理员"),
    COMMON_ADMIN("common_admin", "一般管理员");

    /**
     * 超级管理员
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;
}
