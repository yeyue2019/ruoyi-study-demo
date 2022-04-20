package yeyue.ruoyi.study.framework.web.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.*;

/**
 * 拦截器获取请求参数
 *
 * @author yeyue
 * @date 2022-04-14 20:23:50
 */
@Slf4j
public class HttpRequestHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        // TODO: 2022/4/20 正常退出的请求执行线程缓存清除动作
    }
}
