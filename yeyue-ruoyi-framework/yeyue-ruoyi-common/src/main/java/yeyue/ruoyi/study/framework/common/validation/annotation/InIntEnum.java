package yeyue.ruoyi.study.framework.common.validation.annotation;

import yeyue.ruoyi.study.framework.common.validation.core.IntEnum;
import yeyue.ruoyi.study.framework.common.validation.validator.InIntEnumValidator;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 校验枚举
 *
 * @author yeyue
 * @date 2022-04-18 21:17:10
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InIntEnumValidator.class)
public @interface InIntEnum {

    Class<? extends IntEnum> value();

    String message() default "输入值不在可选范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
