package yeyue.ruoyi.study.framework.security.core.filter;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import yeyue.ruoyi.study.framework.common.pojo.core.CommonResult;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;
import yeyue.ruoyi.study.framework.security.core.service.SecurityTokenService;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;
import yeyue.ruoyi.study.framework.security.core.util.SecurityUtils;
import yeyue.ruoyi.study.framework.web.web.handler.GlobalExceptionHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token 过滤器，验证 token 的有效性 验证通过后，获得 {@link LoginUser} 信息，并加入到 Spring Security 上下文
 *
 * @author yeyue
 * @date 2022-04-19 14:48:31
 */
@RequiredArgsConstructor
public class TokenAuthenticationTokenFilter extends OncePerRequestFilter {

    private final GlobalExceptionHandler exceptionHandler;

    private final SecurityTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取Token
        String token = SecurityUtils.obtainAuthorization(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 根据Token读取用户
                LoginUser loginUser = tokenService.validation(token);
                // 用户存在则将其存入上下文
                if (loginUser != null) {
                    SecurityUtils.setLoginUser(loginUser, request);
                }
            } catch (Throwable ex) {
                CommonResult<?> result = exceptionHandler.filterExceptionHandler(request, ex);
                ServletUtils.writeJSON(response, result);
                return;
            }
        }
        // 执行过滤器
        filterChain.doFilter(request, response);
    }
}
