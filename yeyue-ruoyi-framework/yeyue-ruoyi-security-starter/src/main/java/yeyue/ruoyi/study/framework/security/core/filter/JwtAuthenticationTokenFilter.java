package yeyue.ruoyi.study.framework.security.core.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.security.core.userdetails.WebLoginUser;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;

import javax.annotation.Resource;
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

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取Token
        String token = SecurityUtils.obtainAuthorization(request);
        if (StringUtils.isNotEmpty(token)) {
            // 根据Token读取用户
            WebLoginUser loginUser = new WebLoginUser().setId(0L).setUsername(token);
            if (StringUtils.equals(loginUser.getUsername(), ServletConstants.TEST_TOKEN_NAME)) {
                // 将用户放入权限拦截之前
                SecurityUtils.buildAuthentication(loginUser, request);
            }
        }
        // 执行过滤器
        filterChain.doFilter(request, response);
    }
}
