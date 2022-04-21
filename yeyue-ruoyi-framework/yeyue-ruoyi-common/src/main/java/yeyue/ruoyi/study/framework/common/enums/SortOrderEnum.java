package yeyue.ruoyi.study.framework.common.enums;

import lombok.*;
import yeyue.ruoyi.study.framework.common.validation.core.StringEnum;

/**
 * 查询指定顺序
 *
 * @author yeyue
 * @date 2022-04-11 13:22:40
 */
@Getter
@AllArgsConstructor
public enum SortOrderEnum implements StringEnum {

    ASC("asc", true),
    DESC("desc", false);

    private final String order;
    private final boolean sc;

    @Override
    public String[] array() {
        return new String[]{ASC.order, DESC.order};
    }
}
