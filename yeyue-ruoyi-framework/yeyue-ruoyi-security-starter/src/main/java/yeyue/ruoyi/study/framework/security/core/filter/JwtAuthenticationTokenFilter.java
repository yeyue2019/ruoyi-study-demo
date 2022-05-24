package yeyue.ruoyi.study.framework.security.core.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        // 获取Token
        String token = SecurityUtils.obtainAuthorization(request);
        if (StringUtils.isNotEmpty(token)) {
            // 根据Token读取用户
            LoginUser loginUser = buildLoginUserByToken(token);
            // 存在登录用户
            if (loginUser != null) {
                SecurityUtils.setLoginUser(loginUser, request);
            }
        }
        // 执行过滤器
        filterChain.doFilter(request, response);
    }

    private LoginUser buildLoginUserByToken(String token) {
        if (!StringUtils.equals(token, ServletConstants.TEST_TOKEN_NAME)) {
            return null;
        }
        return new LoginUser()
                .setId(0L)
                .setUsername(token);
    }
}
