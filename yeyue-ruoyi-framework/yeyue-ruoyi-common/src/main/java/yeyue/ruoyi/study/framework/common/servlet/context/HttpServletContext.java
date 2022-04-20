package yeyue.ruoyi.study.framework.common.servlet.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import yeyue.ruoyi.study.framework.common.pojo.http.*;
import yeyue.ruoyi.study.framework.common.servlet.util.ServletUtils;

import javax.servlet.http.*;

/**
 * HttpServlet请求上下文处理
 *
 * @author yeyue
 * @date 2022-04-19 18:10:52
 */
public abstract class HttpServletContext {

    private static final ThreadLocal<HttpRequest> REQUEST_INSTANCE = new TransmittableThreadLocal<>();

    private static final ThreadLocal<HttpResponse> RESPONSE_INSTANCE = new TransmittableThreadLocal<>();

    // private static final ThreadLocal<String> CLIENT_IP = new TransmittableThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        HttpRequest source = ServletUtils.getRequest(request);
        if (source != null) {
            REQUEST_INSTANCE.set(ServletUtils.getRequest(request));
        }
    }

    public static HttpRequest getRequest() {
        return REQUEST_INSTANCE.get();
    }

    public static void removeRequest() {
        REQUEST_INSTANCE.remove();
    }

    public static void setResponse(HttpServletResponse response) {
        HttpResponse source = ServletUtils.getResponse(response);
        if (source != null) {
            RESPONSE_INSTANCE.set(source);
        }
    }

    public static HttpResponse getResponse() {
        return RESPONSE_INSTANCE.get();
    }

    public static void removeResponse() {
        RESPONSE_INSTANCE.remove();
    }


    public static void removeAll() {
        removeRequest();
        removeResponse();
    }
}
