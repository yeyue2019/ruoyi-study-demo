package yeyue.ruoyi.study.module.system.api.enums.dept;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 部门编号枚举
 *
 * @author yeyue
 * @date 2022-04-19 08:59:17
 */
@Getter
@AllArgsConstructor
public enum DeptIdEnum {

    /**
     * 根节点
     */
    ROOT(0L, "default");

    private final Long id;

    private final String name;

}
