package yeyue.ruoyi.study.framework.web.web.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
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

    // 执行顺序  Filter -> Advice -> Interceptor

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // TODO: 2022/4/15 仅能记录正常返回的对象结果,异常返回则会有SkyWalking直接发现
        // TODO: 2022/4/15 可以直接拿对象结果处理
        log.info("[beforeBodyWrite]打印Http请求执行结果:{}", JSON.toJSONString(body));
        return body;
    }
}
