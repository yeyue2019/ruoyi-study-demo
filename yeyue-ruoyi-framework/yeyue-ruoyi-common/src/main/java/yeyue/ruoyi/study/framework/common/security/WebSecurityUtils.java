package yeyue.ruoyi.study.framework.common.security;

import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import static yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants.REQUEST_ATTRIBUTE_SECURITY_USER_ID;
import static yeyue.ruoyi.study.framework.common.servlet.constants.ServletConstants.REQUEST_ATTRIBUTE_SECURITY_USER_TYPE;

/**
 * web-security 上下文工具类
 *
 * @author yeyue
 * @date 2022-05-17 10:10:35
 */
public abstract class WebSecurityUtils {

    public static void setLoginUserId(ServletRequest request, String userId) {
        request.setAttribute(REQUEST_ATTRIBUTE_SECURITY_USER_ID, userId);
    }

    public static void setLoginUserType(ServletRequest request, Integer userType) {
        request.setAttribute(REQUEST_ATTRIBUTE_SECURITY_USER_TYPE, userType);
    }

    /**
     * 从请求中获得当前登录的用户的编号
     *
     * @param request 请求
     * @return 用户编号
     */
    public static String getLoginUserId(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (String) request.getAttribute(REQUEST_ATTRIBUTE_SECURITY_USER_ID);
    }

    /**
     * 从请求中获得当前登录的用户的类型
     *
     * @param request 请求
     * @return 用户类型
     */
    public static Integer getLoginUserType(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (Integer) request.getAttribute(REQUEST_ATTRIBUTE_SECURITY_USER_TYPE);
    }

    /**
     * 从请求中获得当前登录的用户的编号
     *
     * @return 用户编号
     */
    public static String getLoginUserId() {
        return getLoginUserId(ServletUtils.withRequest());
    }

    /**
     * 从请求中获得当前登录的用户的类型
     *
     * @return 用户类型
     */
    public static Integer getLoginUserType() {
        return getLoginUserType(ServletUtils.withRequest());
    }
}
