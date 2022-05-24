package yeyue.ruoyi.study.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

/**
 * 查询指定顺序
 *
 * @author yeyue
 * @date 2022-04-11 13:22:40
 */
@Getter
@AllArgsConstructor
public enum FieldSortedEnum implements EnumValuable<String> {

    ASC("asc"),
    DESC("desc");

    private final String order;

    public static boolean isDesc(FieldSortedEnum st) {
        return st == DESC;
    }

    @Override
    public String get() {
        return this.order;
    }
}
