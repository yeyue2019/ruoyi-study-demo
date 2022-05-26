package yeyue.ruoyi.study.framework.common.validation.util;

import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author yeyue
 * @date 2022-05-18 23:17:25
 */
public abstract class ValidationUtils {

    /**
     * validation手动校验
     *
     * @param validator 校验器
     * @param object    校验对象
     * @param groups    对象所属分组
     */
    public static void validate(Validator validator, Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
