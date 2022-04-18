package yeyue.ruoyi.study.framework.common.validation.validator;

import yeyue.ruoyi.study.framework.common.validation.annotation.InIntEnum;
import yeyue.ruoyi.study.framework.common.validation.core.IntEnum;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-04-11 13:44:14
 */
public class InIntEnumValidator implements ConstraintValidator<InIntEnum, Integer> {

    /**
     * 储存枚举名称的集合
     */
    private Set<Integer> values;

    @Override
    public void initialize(InIntEnum constraintAnnotation) {
        IntEnum[] values = constraintAnnotation.value().getEnumConstants();
        if (values == null) {
            this.values = Collections.emptySet();
        } else {
            this.values = Arrays.stream(values[0].array()).boxed().collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (this.values.contains(value)) {
            return true;
        }
        return false;
    }
}
