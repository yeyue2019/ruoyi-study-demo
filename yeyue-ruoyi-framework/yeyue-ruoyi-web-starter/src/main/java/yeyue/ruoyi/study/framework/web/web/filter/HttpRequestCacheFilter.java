package yeyue.ruoyi.study.framework.web.web.filter;

import io.opentracing.Tracer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.web.web.wrapper.HttpRequestCopyWrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 拷贝HttpRequest的Body作为全局参数
 *
 * @author yeyue
 * @date 2022-04-14 10:23:09
 */
@Slf4j
@AllArgsConstructor
public class HttpRequestCacheFilter extends OncePerRequestFilter {

    private final Tracer TRACER = new SkywalkingTracer();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new HttpRequestCopyWrapper(request), response);
    }

}
