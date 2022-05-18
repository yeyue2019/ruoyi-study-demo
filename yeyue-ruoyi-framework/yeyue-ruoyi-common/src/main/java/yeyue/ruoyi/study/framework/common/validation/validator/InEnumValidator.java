package yeyue.ruoyi.study.framework.common.validation.validator;

import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;

import javax.validation.*;
import java.util.*;

/**
 * @author yeyue
 * @date 2022-04-11 13:44:14
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Object> {

    /**
     * 存储枚举的集合
     */
    private Set<Object> values;

    @Override
    public void initialize(InEnum constraintAnnotation) {
        EnumValuable<?>[] values = constraintAnnotation.value().getEnumConstants();
        if (values == null) {
            this.values = Collections.emptySet();
        } else {
            this.values = CollectionUtils.arrayToSet(values[0].enums());
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return this.values.contains(value);
    }
}
