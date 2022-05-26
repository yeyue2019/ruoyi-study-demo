package yeyue.ruoyi.study.framework.security.core.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.common.servlet.security.WebSecurityUtils;
import yeyue.ruoyi.study.framework.security.core.userdetails.LoginUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author yeyue
 * @date 2022-04-19 14:53:06
 */
public abstract class SecurityUtils {

    /**
     * 从请求中，获得认证 Token
     *
     * @param request 请求
     * @return 认证 Token
     */
    public static String obtainAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader(ServletConstants.AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        int index = authorization.indexOf(ServletConstants.AUTHORIZATION_TOKEN_PREFIX);
        if (index == -1) {
            return null;
        }
        return authorization.substring(index + ServletConstants.AUTHORIZATION_TOKEN_PREFIX.length()).trim();
    }

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
    }

    /**
     * 设置当前用户
     *
     * @param loginUser 当前用户
     * @param request   请求
     */
    public static void setLoginUser(LoginUser loginUser, HttpServletRequest request) {
        // 创建 Authentication，并设置到上下文
        Authentication authentication = buildAuthentication(loginUser, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放到common需要的上下文中
        WebSecurityUtils.setLoginUserId(request, loginUser.getId());
        WebSecurityUtils.setLoginUserType(request, loginUser.getUserType());
    }

    /**
     * 构建用户登录身份
     */
    private static Authentication buildAuthentication(LoginUser loginUser, HttpServletRequest request) {
        // 创建 UsernamePasswordAuthenticationToken 对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, Collections.emptyList());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }
}
