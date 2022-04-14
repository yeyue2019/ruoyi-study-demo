package yeyue.ruoyi.study.framework.web.web.interceptor;

import com.alibaba.fastjson.JSON;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.web.servlet.HandlerInterceptor;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.util.servlet.ServletUtils;

import javax.servlet.http.*;

/**
 * @author yeyue
 * @date 2022-04-14 20:23:50
 */
@Slf4j
public class HttpTraceInterceptor implements HandlerInterceptor {

    private static final String BIZ_OPERATION_NAME_PREFIX = "Biz/";

    private final Tracer TRACER = new SkywalkingTracer();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        TRACER.activeSpan()
                .setTag(CommonConstants.TAG_PARAM, JSON.toJSONString(ServletUtils.getParamMap(request)))
                .setTag(CommonConstants.TAG_HEADER, JSON.toJSONString(ServletUtils.getHeaderMap(request)))
                .setTag(CommonConstants.TAG_BODY, ServletUtils.getBodyString(request));

    }
}
