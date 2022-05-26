package yeyue.ruoyi.study.module.system.api.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 角色标识枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:51:09
 */
@Getter
@AllArgsConstructor
public enum RoleCodeEnum implements EnumValuable<String> {

    SUPER_ADMIN("super_admin", "超级管理员"),

    ;

    /**
     * 管理员
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;

    @Override
    public String get() {
        return this.code;
    }

    /**
     * 身份标识是否是超级管理员
     *
     * @param code 编号
     * @return 结果
     */
    public static boolean isSuperAdmin(String code) {
        return StringUtils.equalsIgnoreCase(SUPER_ADMIN.get(), code);
    }
}
