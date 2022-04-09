package yeyue.ruoyi.study.framework.validation.annotation;

import yeyue.ruoyi.study.framework.common.core.ValidEnum;
import yeyue.ruoyi.study.framework.validation.validator.InEnumValidator;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 校验枚举Name在枚举中
 *
 * @author yeyue
 * @date 2022-04-09 13:45:50
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InEnumValidator.class)
public @interface InEnum {

    /**
     * @return 实现 ValidEnum 接口的
     */
    Class<? extends ValidEnum> value();

    String message() default "必须在指定范围 %s";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
