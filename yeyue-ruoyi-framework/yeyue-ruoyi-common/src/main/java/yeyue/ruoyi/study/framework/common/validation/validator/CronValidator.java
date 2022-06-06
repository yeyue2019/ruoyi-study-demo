package yeyue.ruoyi.study.framework.common.validation.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.support.CronExpression;
import yeyue.ruoyi.study.framework.common.validation.annotation.Cron;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Cron表达式校验器
 *
 * @author yeyue
 * @date 2022-06-06 16:42:28
 */
public class CronValidator implements ConstraintValidator<Cron, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return CronExpression.isValidExpression(value);
    }
}
