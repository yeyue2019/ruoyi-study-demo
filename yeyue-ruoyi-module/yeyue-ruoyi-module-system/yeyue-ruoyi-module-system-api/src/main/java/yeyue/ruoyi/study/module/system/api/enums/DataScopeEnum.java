package yeyue.ruoyi.study.module.system.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;

/**
 * 权限范围
 *
 * @author yeyue
 * @date 2022-04-19 08:59:17
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
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

    @EnumValue
    private final Integer scope;
}
