package yeyue.ruoyi.study.framework.common.enums;

import lombok.*;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.validation.core.ValidEnum;

/**
 * 查询指定顺序
 *
 * @author yeyue
 * @date 2022-04-11 13:22:40
 */
@Getter
@AllArgsConstructor
public enum QuerySortOrderEnum implements ValidEnum {

    asc(CommonConstants.ORDER_ASC, true),
    desc(CommonConstants.ORDER_DESC, false);

    private final String order;
    private final boolean sc;

    @Override
    public String[] array() {
        return new String[]{asc.name(), desc.name()};
    }
}