package yeyue.ruoyi.study.framework.web.web.handler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;

import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.exception.util.ExceptionUtils;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;

/**
 * 全局异常处理器
 *
 * @author yeyue
 * @date 2022-04-09 12:03:14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常，主要是提供给 Filter 使用 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex 异常
     * @return 通用返回
     */
    public CommonResult<?> filterExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException)ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException)ex);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return httpMessageNotReadableExceptionHandler((HttpMessageNotReadableException)ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException)ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException)ex);
        }
        if (ex instanceof ValidationException) {
            return validationException((ValidationException)ex);
        }
        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler((NoHandlerFoundException)ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException)ex);
        }
        if (ex instanceof DataAccessException) {
            return dataAccessExceptionHandler((DataAccessException)ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler((AccessDeniedException)ex);
        }
        if (ex instanceof AuthenticationException) {
            return authenticationExceptionHandler((AuthenticationException)ex);
        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException)ex);
        }
        return defaultExceptionHandler(ex);
    }

    // 全局使用GlobalErrorCode 便于Swagger管理

    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, "请求参数缺失:{}", ex.getParameterName());
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, "请求参数类型错误:{}不属于{}类型", ex.getValue(),
            ex.getRequiredType());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public CommonResult<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        String msg = "请求参数数据内容错误";
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException)ex.getCause();
            msg = String.format("请求参数数据内容错误:%s不属于%s类型", invalidFormatException.getValue(),
                invalidFormatException.getTargetType());
        }
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, msg);
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public CommonResult<?> bindExceptionHandler(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, "请求参数不正确:{}",
            Objects.requireNonNull(fieldError).getDefaultMessage());
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, "请求参数不正确:{}", constraintViolation.getMessage());
    }

    /**
     * 处理 Dubbo Consumer 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public CommonResult<?> validationException(ValidationException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项： 1. spring.mvc.throw-exception-if-no-handler-found 为 true 2. spring.mvc.static-path-pattern 为
     * /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.NOT_FOUND, "请求地址不存在:{}", ex.getRequestURL());
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.METHOD_NOT_ALLOWED, "请求方法不正确:{},支持的方法为:{}", ex.getMethod(),
            Arrays.toString(ex.getSupportedMethods()));
    }

    // /**
    // * 处理 Resilience4j 限流抛出的异常
    // */
    // @ExceptionHandler(value = RequestNotPermitted.class)
    // public CommonResult<?> requestNotPermittedExceptionHandler(HttpServletRequest req, RequestNotPermitted ex) {
    // log.warn("[requestNotPermittedExceptionHandler][url({}) 访问过于频繁]", req.getRequestURL(), ex);
    // return CommonResult.error(TOO_MANY_REQUESTS);
    // }

    /**
     * 处理数据库操作异常 SQLException
     */
    @ExceptionHandler(value = DataAccessException.class)
    public CommonResult<?> dataAccessExceptionHandler(DataAccessException ex) {
        if (ex.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException)ex.getRootCause();
            return ExceptionUtils.handle(GlobalErrorCode.SQL_EXECUTE_BAD, "pstmt:{}, reason:{}, ex:{}",
                sqlEx.getErrorCode(), sqlEx.getMessage(), sqlEx.getClass().getName());
        }
        return defaultExceptionHandler(ex);
    }

    /**
     * 处理 Spring Security 权限不足的异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(AccessDeniedException ex) {
        return ExceptionUtils.handle(GlobalErrorCode.FORBIDDEN, "没有操作权限");
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public CommonResult<?> authenticationExceptionHandler(AuthenticationException ex) {
        String message = "用户信息获取失败";
        if (ex instanceof AccountStatusException) {
            if (ex instanceof AccountExpiredException) {
                message = "用户身份过期";
            } else if (ex instanceof CredentialsExpiredException) {
                message = "用户登录过期";
            } else if (ex instanceof DisabledException) {
                message = "用户状态禁用";
            } else {
                message = "用户已被锁定";
            }
        } else if (ex instanceof BadCredentialsException || ex instanceof UsernameNotFoundException) {
            message = "账号或密码错误";
        }
        return ExceptionUtils.handle(GlobalErrorCode.UNAUTHORIZED, message);
    }

    /**
     * 处理业务异常 ServiceException
     * <p>
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> serviceExceptionHandler(ServiceException ex) {
        return ExceptionUtils.handle(ex, null);
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> defaultExceptionHandler(Throwable ex) {
        log.error("捕捉到未定义异常:", ex);
        return ExceptionUtils.handle(GlobalErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
