package yeyue.ruoyi.study.module.system.api.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yeyue
 * @date 2022-05-23 15:37:48
 */
@Getter
@AllArgsConstructor
public enum MenuIdEnum {

    /**
     * 根节点
     */
    ROOT(0L, "default");

    private final Long id;

    private final String name;
}
