package yeyue.ruoyi.study.framework.common.validation.annotation;

import javax.validation.Payload;

/**
 * @author yeyue
 * @date 2022-06-06 16:34:08
 */
public @interface Cron {

    String message() default "CRON 表达式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
