package yeyue.ruoyi.study.framework.web.web.filter;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.common.constants.CommonConstants;
import yeyue.ruoyi.study.framework.common.monitor.trace.util.TracerUtils;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.pojo.http.*;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;
import yeyue.ruoyi.study.framework.common.servlet.wrapper.*;
import yeyue.ruoyi.study.framework.common.util.match.AntPathMatchUtils;
import yeyue.ruoyi.study.framework.web.web.handler.GlobalExceptionHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Http业务请求收集过滤器
 *
 * @author yeyue
 * @date 2022-04-14 10:23:09
 */
@Slf4j
@AllArgsConstructor
public class ServiceFilter extends OncePerRequestFilter {

    private final GlobalExceptionHandler handler;

    private static final String[] CACHE_PATTERNS = new String[]{
            "/ruoyi/**",
    };


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 业务外接口不进行缓存过滤
        if (AntPathMatchUtils.noneMatch(request.getRequestURI(), CACHE_PATTERNS)) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpRequestCopyWrapper reqWrapper = new HttpRequestCopyWrapper(request);
        HttpResponseCopyWrapper resWrapper = new HttpResponseCopyWrapper(response);
        HttpRequest logReq = ServletUtils.getRequest(reqWrapper);
        HttpResponse logRes;
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(reqWrapper, resWrapper);
            logRes = ServletUtils.getResponse(resWrapper);
            log.info("打印正常执行完成的请求:请求内容:{},响应内容:{},执行毫秒数:{}", logReq, logRes, System.currentTimeMillis() - start);
            traceTag(logReq, logRes);
        } catch (Throwable e) {
            CommonResult<?> errorCode = handler.filterExceptionHandler(reqWrapper, e);
            logRes = new HttpResponse(200, JSON.toJSONString(errorCode), ServletUtils.getHeaderMap(reqWrapper));
            log.warn("打印异常执行完成的请求:请求内容:{},响应内容:{},执行毫秒数:{}", logReq, logRes, System.currentTimeMillis() - start);
            traceTag(logReq, logRes);
            ServletUtils.writeJSON(resWrapper, errorCode);
        }
    }

    public static void traceTag(HttpRequest request, HttpResponse response) {
        TracerUtils.tag(CommonConstants.TRACE_REQ_ALL, request);
        TracerUtils.tag(CommonConstants.TRACE_RES_ALL, response);
    }

}
