package yeyue.ruoyi.study.framework.web.web.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;
import org.springframework.validation.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.exception.util.ExceptionUtils;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.util.object.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.sql.SQLException;
import java.util.Arrays;

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
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex      异常
     * @return 通用返回
     */
    public CommonResult<?> filterExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return httpMessageNotReadableExceptionHandler((HttpMessageNotReadableException) ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException) ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ValidationException) {
            return validationException((ValidationException) ex);
        }
        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler((NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
        if (ex instanceof DataAccessException) {
            return dataAccessExceptionHandler(request, (DataAccessException) ex);
        }
        if (ex instanceof BadCredentialsException) {
            return badCredentialsExceptionHandler(request, (BadCredentialsException) ex);
        }
        if (ex instanceof DisabledException) {
            return disabledExceptionHandler(request, (DisabledException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler(request, (AccessDeniedException) ex);
        }
        if (ex instanceof AuthenticationException) {
            return authenticationExceptionHandler(request, (AuthenticationException) ex);
        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }

    // 全局使用GlobalErrorCode 便于Swagger管理

    // TODO: 2022/4/11 后面考虑错误日志输出的去留

    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, String.format("请求参数缺失:%s", ex.getParameterName()), false, null);
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, String.format("请求参数类型错误:%s不属于%s类型", ex.getValue(), ex.getRequiredType()), false, null);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public CommonResult<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        String msg = "请求参数数据内容错误";
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            msg = String.format("请求参数数据内容错误:%s不属于%s类型", invalidFormatException.getValue(), invalidFormatException.getTargetType());
        }
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, msg, false, null);
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        Assert.notNull(fieldError, "fieldError不允许为空！！！");
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, String.format("请求参数不正确:%s", fieldError.getDefaultMessage()), false, null);
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public CommonResult<?> bindExceptionHandler(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        Assert.notNull(fieldError, "fieldError不允许为空！！！");
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, String.format("请求参数不正确:%s", fieldError.getDefaultMessage()), false, null);
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, String.format("请求参数不正确:%s", constraintViolation.getMessage()), false, null);
    }

    /**
     * 处理 Dubbo Consumer 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public CommonResult<?> validationException(ValidationException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.BAD_REQUEST, null, true, null);
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult<?> noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.NOT_FOUND, String.format("请求地址不存在:%s", ex.getRequestURL()), false, null);
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.METHOD_NOT_ALLOWED, String.format("请求方法不正确:%s,支持的方法为:%s", ex.getMethod(), Arrays.toString(ex.getSupportedMethods())), false, null);
    }

    // /**
    //  * 处理 Resilience4j 限流抛出的异常
    //  */
    // @ExceptionHandler(value = RequestNotPermitted.class)
    // public CommonResult<?> requestNotPermittedExceptionHandler(HttpServletRequest req, RequestNotPermitted ex) {
    //     log.warn("[requestNotPermittedExceptionHandler][url({}) 访问过于频繁]", req.getRequestURL(), ex);
    //     return CommonResult.error(TOO_MANY_REQUESTS);
    // }

    /**
     * 处理数据库操作异常 SQLException
     */
    @ExceptionHandler(value = DataAccessException.class)
    public CommonResult<?> dataAccessExceptionHandler(HttpServletRequest req, DataAccessException ex) {
        if (ex.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) ex.getRootCause();
            String logMsg = String.format("SQL语句执行报错, pstmt:%s, reason:%s, ex:%s", sqlEx.getErrorCode(), sqlEx.getMessage(), sqlEx.getClass().getSimpleName());
            return ExceptionUtils.print(ex, GlobalErrorCode.SQL_EXECUTE_BAD, ObjectUtils.indexJoin(sqlEx.getErrorCode(), sqlEx.getMessage()), true, logMsg);
        }
        return defaultExceptionHandler(req, ex);
    }

    // Spring Security 异常拦截

    @ExceptionHandler(value = BadCredentialsException.class)
    public CommonResult<?> badCredentialsExceptionHandler(HttpServletRequest req, BadCredentialsException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.UNAUTHORIZED, "账号或密码错误", false, null);
    }

    @ExceptionHandler(value = DisabledException.class)
    public CommonResult<?> disabledExceptionHandler(HttpServletRequest req, DisabledException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.UNAUTHORIZED, "登录失败，账号被禁用", false, null);
    }

    /**
     * 处理 Spring Security 权限不足的异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.FORBIDDEN, null, false, null);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public CommonResult<?> authenticationExceptionHandler(HttpServletRequest req, AuthenticationException ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.UNAUTHORIZED, "用户权限模块未检出错误", true, null);
    }

    /**
     * 处理业务异常 ServiceException
     * <p>
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> serviceExceptionHandler(ServiceException ex) {
        return ExceptionUtils.print(ex, ex, null, true, null);
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        return ExceptionUtils.print(ex, GlobalErrorCode.INTERNAL_SERVER_ERROR, ExceptionUtils.getMessage(ex), true, null);
    }
}
