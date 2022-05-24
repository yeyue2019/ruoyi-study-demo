package yeyue.ruoyi.study.framework.common.enums;

import lombok.*;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 通用状态枚举
 *
 * @author yeyue
 * @date 2022-04-18 14:48:51
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements EnumValuable<Integer> {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;


    @Override
    public Integer get() {
        return this.status;
    }
}
