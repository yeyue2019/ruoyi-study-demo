package yeyue.ruoyi.study.framework.monitor.trace.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import yeyue.ruoyi.study.framework.common.monitor.trace.util.TracerUtils;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;

/**
 * 日志输出过滤器
 *
 * @author yeyue
 * @date 2022-04-13 16:04:46
 */
public class TracerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        response.addHeader(ServletConstants.TRACE_HEADER, TracerUtils.getTraceId());
        filterChain.doFilter(request, response);
    }
}
