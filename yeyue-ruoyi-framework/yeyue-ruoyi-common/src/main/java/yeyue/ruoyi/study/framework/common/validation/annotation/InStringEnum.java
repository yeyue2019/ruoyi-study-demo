package yeyue.ruoyi.study.framework.common.validation.annotation;

import yeyue.ruoyi.study.framework.common.validation.core.StringEnum;
import yeyue.ruoyi.study.framework.common.validation.validator.InStringEnumValidator;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 校验枚举
 *
 * @author yeyue
 * @date 2022-04-09 13:45:50
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InStringEnumValidator.class)
public @interface InStringEnum {

    Class<? extends StringEnum> value();

    String message() default "输入值不在可选范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
