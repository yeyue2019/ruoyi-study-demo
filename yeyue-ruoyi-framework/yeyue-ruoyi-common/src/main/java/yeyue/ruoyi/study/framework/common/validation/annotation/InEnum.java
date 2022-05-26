package yeyue.ruoyi.study.framework.common.validation.annotation;

import yeyue.ruoyi.study.framework.common.validation.core.EnumValuable;
import yeyue.ruoyi.study.framework.common.validation.validator.InEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Constraint(validatedBy = InEnumValidator.class)
public @interface InEnum {

    Class<? extends EnumValuable<?>> value();

    String message() default "输入值不在可选范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
