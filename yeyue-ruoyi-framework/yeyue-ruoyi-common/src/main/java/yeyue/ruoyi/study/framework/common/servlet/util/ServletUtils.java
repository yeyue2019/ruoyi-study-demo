package yeyue.ruoyi.study.framework.common.servlet.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.servlet.pojo.HttpRequest;
import yeyue.ruoyi.study.framework.common.servlet.pojo.HttpResponse;
import yeyue.ruoyi.study.framework.common.servlet.wrapper.HttpRequestCopyWrapper;
import yeyue.ruoyi.study.framework.common.servlet.wrapper.HttpResponseCopyWrapper;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.network.NetworkUtils;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * servlet工具类
 *
 * @author yeyue
 * @date 2022-04-14 10:24:45
 */
@Slf4j
public abstract class ServletUtils {
    public static final String[] HEADERS = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    /**
     * 获取当前Http请求
     *
     * @return 结果
     */
    public static HttpServletRequest withRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    /**
     * 向Response输出JSON
     *
     * @param response 响应
     * @param object   结果
     * @throws IOException 异常
     */
    @SuppressWarnings("spellCheckingInspection")
    public static void writeJSON(HttpServletResponse response, Object object) throws IOException {
        String content = JSON.toJSONString(object);
        response.getOutputStream().write(content.getBytes());
    }

    /**
     * 获取请求URI
     *
     * @param request 请求
     * @return 结果
     */
    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * 获取Http请求的方法
     *
     * @param request 请求
     * @return 结果
     */
    public static String getMethod(HttpServletRequest request) {
        return request.getMethod();
    }

    /**
     * 获取请求的param
     *
     * @param request 请求
     * @return 结果
     */
    public static Map<String, String[]> getParams(ServletRequest request) {
        return Collections.unmodifiableMap(request.getParameterMap());
    }

    /**
     * 获取请求的header
     *
     * @param request 请求
     * @return 结果
     */
    public static Map<String, String> getHeaderMap(HttpServletRequest request) {
        final Map<String, String> result = new HashMap<>(20);
        final Enumeration<String> names = request.getHeaderNames();
        String name;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            result.put(name, request.getHeader(name));
        }
        return result;
    }

    /**
     * 获取响应的header
     *
     * @param response 响应
     * @return 结果
     */
    public static Map<String, String> getHeaderMap(HttpServletResponse response) {
        final Map<String, String> result = new HashMap<>(20);
        final Collection<String> names = response.getHeaderNames();
        for (String name : names) {
            result.put(name, response.getHeader(name));
        }
        return result;
    }

    /**
     * 获取响应的状态码
     *
     * @param response 响应
     * @return 结果
     */
    public static int getStatus(HttpServletResponse response) {
        return response.getStatus();
    }

    /**
     * 获取Body请求体
     *
     * @param request 请求
     * @return 字符串结果
     */
    public static String getBodyString(HttpRequestCopyWrapper request) {
        return getBodyString(request, Charset.defaultCharset().name());
    }

    /**
     * 获取body请求体
     *
     * @param request 请求
     * @return 结果
     */
    public static String getBodyString(ServletRequest request, String charset) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new ServiceException(GlobalErrorCode.IO_EXCEPTION, e);
        }
        return sb.toString();
    }

    /**
     * 获取Body响应结果
     *
     * @param response 响应
     * @return 结果
     */
    public static String getBodyString(HttpResponseCopyWrapper response) {
        return new String(response.toByteArray(), Charset.defaultCharset());
    }

    /**
     * 转化Http请求对象
     *
     * @param request 请求
     * @return 结果
     */
    public static HttpRequest getRequest(HttpServletRequest request) {
        if (request instanceof HttpRequestCopyWrapper) {
            return new HttpRequest().setUrl(getUrl(request)).setMethod(getMethod(request))
                    .setHeaders(getHeaderMap(request)).setParams(getParams(request))
                    .setBody(getBodyString((HttpRequestCopyWrapper) request)).setIp(getClientIp(request));
        }
        return null;
    }

    /**
     * 转化Http响应结果
     *
     * @param response 响应
     * @return 结果
     */
    public static HttpResponse getResponse(HttpServletResponse response) {
        if (response instanceof HttpResponseCopyWrapper) {
            return new HttpResponse().setStatus(getStatus(response)).setHeaders(getHeaderMap(response))
                    .setBody(getBodyString((HttpResponseCopyWrapper) response));
        }
        return null;
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * 默认检测的Header:
     *
     * <pre>
     * 1、X-Forwarded-For
     * 2、X-Real-IP
     * 3、Proxy-Client-IP
     * 4、WL-Proxy-Client-IP
     * </pre>
     *
     * <p>
     * otherHeaderNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request     请求对象{@link HttpServletRequest}
     * @param headerNames 其他自定义头文件，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     */
    public static String getClientIp(HttpServletRequest request, String... headerNames) {

        String ip;
        // 先从默认header里查询
        for (String header : HEADERS) {
            if (!NetworkUtils.isUnknown(ip = request.getHeader(header))) {
                return NetworkUtils.getMultistageReverseProxyIp(ip);
            }
        }
        // 再从自定义header里查询
        if (CollectionUtils.isNotNull((Object[]) headerNames)) {
            for (String header : headerNames) {
                if (!NetworkUtils.isUnknown(ip = request.getHeader(header))) {
                    return NetworkUtils.getMultistageReverseProxyIp(ip);
                }
            }
        }
        // 最后从Remote里查询
        return NetworkUtils.getMultistageReverseProxyIp(request.getRemoteAddr());
    }

    public static String getClientIp(String... headerNames) {
        return getClientIp(withRequest(), headerNames);
    }

    /**
     * 创建Servlet Filter
     *
     * @param filter 过滤器
     * @param order  指定过滤顺序
     * @param <T>    类型
     * @return 创建成功的bean
     */
    public static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }
}
