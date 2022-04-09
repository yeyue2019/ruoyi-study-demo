package yeyue.ruoyi.study.validation.dto;

import lombok.*;
import yeyue.ruoyi.study.framework.common.core.ValidEnum;

import java.util.Arrays;

/**
 * 性别
 *
 * @author yeyue
 * @date 2022-04-09 14:05:55
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements ValidEnum {

    男, 女, 未知;

    @Override
    public String[] array() {
        return Arrays.stream(values()).map(GenderEnum::name).toArray(String[]::new);
    }
}
