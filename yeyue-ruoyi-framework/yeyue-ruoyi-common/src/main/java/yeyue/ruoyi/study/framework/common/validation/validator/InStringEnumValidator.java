package yeyue.ruoyi.study.framework.common.validation.validator;

import yeyue.ruoyi.study.framework.common.validation.annotation.InStringEnum;
import yeyue.ruoyi.study.framework.common.validation.core.StringEnum;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-04-11 13:44:14
 */
public class InStringEnumValidator implements ConstraintValidator<InStringEnum, String> {

    /**
     * 储存枚举名称的集合
     */
    private Set<String> values;

    @Override
    public void initialize(InStringEnum constraintAnnotation) {
        StringEnum[] values = constraintAnnotation.value().getEnumConstants();
        if (values == null) {
            this.values = Collections.emptySet();
        } else {
            this.values = Arrays.stream(values[0].array()).collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return this.values.contains(value);
    }
}
