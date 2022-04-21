package yeyue.ruoyi.study.framework.security.core.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants;
import yeyue.ruoyi.study.framework.security.core.authentication.YeyueUsernamePasswordAuthenticationToken;
import yeyue.ruoyi.study.framework.security.core.userdetails.WebLoginUser;

import javax.servlet.http.HttpServletRequest;

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
     * 构建用户登录身份
     */
    public static void buildAuthentication(WebLoginUser loginUser, HttpServletRequest request) {
        // 创建 UsernamePasswordAuthenticationToken 对象
        UsernamePasswordAuthenticationToken authenticationToken = new YeyueUsernamePasswordAuthenticationToken(
                loginUser, null, loginUser.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
