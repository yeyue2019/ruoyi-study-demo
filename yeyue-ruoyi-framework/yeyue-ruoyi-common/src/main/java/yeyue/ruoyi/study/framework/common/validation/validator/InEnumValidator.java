package yeyue.ruoyi.study.framework.common.validation.validator;

import yeyue.ruoyi.study.framework.common.validation.annotation.InEnum;
import yeyue.ruoyi.study.framework.common.validation.core.ValidEnum;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yeyue
 * @date 2022-04-11 13:44:14
 */
public class InEnumValidator implements ConstraintValidator<InEnum, String> {

    /**
     * 储存枚举名称的集合
     */
    private Set<String> values;

    @Override
    public void initialize(InEnum constraintAnnotation) {
        ValidEnum[] values = constraintAnnotation.value().getEnumConstants();
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
        if (this.values.contains(value)) {
            return true;
        }
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        // 禁用默认的 message 的值
        context.disableDefaultConstraintViolation();
        // 重新添加错误提示语句
        context.buildConstraintViolationWithTemplate(String.format(context.getDefaultConstraintMessageTemplate(), this.values.toString())).addConstraintViolation();
        return false;
    }
}
