package yeyue.ruoyi.study.framework.validation.validator;

import yeyue.ruoyi.study.framework.common.core.ValidEnum;
import yeyue.ruoyi.study.framework.validation.annotation.InEnum;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 枚举校验过滤器
 *
 * @author yeyue
 * @date 2022-04-09 13:48:34
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
