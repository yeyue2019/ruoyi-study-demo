package yeyue.ruoyi.study.framework.web.web.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;

/**
 * 全局返回收集
 *
 * @author yeyue
 * @date 2022-04-14 21:57:19
 */
@Slf4j
@ControllerAdvice
@SuppressWarnings({"rawtypes"})
public class GlobalResponseHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (returnType.getMethod() == null) {
            return false;
        }
        return returnType.getMethod().getReturnType() == CommonResult.class;
    }

    // 执行顺序 Filter -> Advice -> Interceptor

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // TODO: 2022/4/20 所有经过该方法处理的都是业务功能接口，如果需要记录操作日志可以在这里记录
        return body;
    }
}
