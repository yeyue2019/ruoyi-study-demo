package yeyue.ruoyi.study.framework.web.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.monitor.trace.util.TracerUtils;
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

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // TODO: 2022/4/15 对于正常的请求返回记录
        TracerUtils.put(CommonConstants.TAG_TRACE_RESPONSE, body);
        return body;
    }
}