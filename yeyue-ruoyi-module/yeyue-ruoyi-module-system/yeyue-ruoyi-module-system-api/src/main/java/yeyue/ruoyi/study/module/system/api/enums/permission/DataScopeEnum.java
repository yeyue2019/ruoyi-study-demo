package yeyue.ruoyi.study.module.system.api.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 权限范围
 *
 * @author yeyue
 * @date 2022-04-19 08:59:17
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum implements EnumValuable<Integer> {
    /**
     * 全部数据权限
     */
    ALL(1),
    /**
     * 指定部门
     */
    DEPT_CUSTOM(2),
    /**
     * 仅部门
     */
    DEPT_ONLY(3),
    /**
     * 部门及以下
     */
    DEPT_AND_CHILD(4),
    /**
     * 仅自身
     */
    SELF(5);

    private final Integer scope;

    @Override
    public Integer get() {
        return this.scope;
    }
}
