package yeyue.ruoyi.study.framework.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;
import yeyue.ruoyi.study.framework.common.validation.core.IntEnum;

/**
 * 通用状态枚举
 *
 * @author yeyue
 * @date 2022-04-18 14:48:51
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum implements IntEnum {

    ENABLE(0, "开启"),
    DISABLE(1, "关闭");

    /**
     * 状态值
     */
    @EnumValue
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    public static CommonStatusEnum toEnum(Integer status) {
        for (CommonStatusEnum value : CommonStatusEnum.values()) {
            if (value.getStatus().equals(status)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public int[] array() {
        return new int[]{1, 2};
    }
}
