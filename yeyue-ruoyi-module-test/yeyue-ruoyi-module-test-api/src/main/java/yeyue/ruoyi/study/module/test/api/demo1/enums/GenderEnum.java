package yeyue.ruoyi.study.module.test.api.demo1.enums;

import lombok.*;
import yeyue.ruoyi.study.framework.common.validation.core.StringEnum;

import java.util.Arrays;

/**
 * 性别
 *
 * @author yeyue
 * @date 2022-04-09 14:05:55
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements StringEnum {

    男, 女, 未知;

    @Override
    public String[] array() {
        return Arrays.stream(values()).map(GenderEnum::name).toArray(String[]::new);
    }
}
