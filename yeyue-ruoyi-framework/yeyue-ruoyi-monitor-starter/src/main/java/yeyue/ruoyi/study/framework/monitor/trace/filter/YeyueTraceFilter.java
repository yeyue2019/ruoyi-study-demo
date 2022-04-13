package yeyue.ruoyi.study.framework.monitor.trace.filter;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.TRACE_ID_NAME;

/**
 * 日志输出过滤器
 *
 * @author yeyue
 * @date 2022-04-13 16:04:46
 */
public class YeyueTraceFilter extends OncePerRequestFilter {

    // TODO: 2022/4/13 确认是第一个执行的过滤器


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}