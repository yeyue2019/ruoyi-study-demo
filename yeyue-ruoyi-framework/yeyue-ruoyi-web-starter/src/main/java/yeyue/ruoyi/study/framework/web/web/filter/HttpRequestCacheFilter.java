package yeyue.ruoyi.study.framework.web.web.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.web.web.wrapper.*;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new HttpRequestCopyWrapper(request), new HttpResponseCopyWrapper(response));
        // TODO: 2022/4/15 不缺人执行后能够返回，所以在后面添加逻辑
    }

}
