package yeyue.ruoyi.study.framework.web.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.monitor.trace.util.TracerUtils;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;
import yeyue.ruoyi.study.framework.common.servlet.wrapper.HttpResponseCopyWrapper;

import javax.servlet.http.*;

/**
 * 拦截器获取请求参数
 *
 * @author yeyue
 * @date 2022-04-14 20:23:50
 */
@Slf4j
public class HttpRequestHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        // TODO: 2022/4/15 执行后再获取,防止无法合并到skywalking的Span中,也防止Filter执行报错导致获取无法执行到当前步骤
        if (response instanceof HttpResponseCopyWrapper) {
            TracerUtils.put(CommonConstants.TRACE_RES_STATUS, response.getStatus());
            TracerUtils.put(CommonConstants.TRACE_RES_BODY, new String(((HttpResponseCopyWrapper) response).toByteArray()));
            TracerUtils.put(CommonConstants.TRACE_HEADER, ServletUtils.getHeaderMap(response));
        }
    }
}
