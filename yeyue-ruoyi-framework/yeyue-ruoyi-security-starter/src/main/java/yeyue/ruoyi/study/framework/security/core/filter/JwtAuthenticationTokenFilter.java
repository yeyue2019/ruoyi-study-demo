package yeyue.ruoyi.study.framework.security.core.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * JWT过滤器
 *
 * @author yeyue
 * @date 2022-04-19 14:48:31
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = SecurityUtils.obtainAuthorization(request);
        if (StringUtils.isNotEmpty(token)) {
            // TODO: 2022/4/19 对于登录用户的 token有效期校验, 缓存用户信息到上下文
        }
        filterChain.doFilter(request, response);
    }
}
