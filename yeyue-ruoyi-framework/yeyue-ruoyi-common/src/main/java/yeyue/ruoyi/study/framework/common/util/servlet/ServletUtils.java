package yeyue.ruoyi.study.framework.common.util.servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.util.collection.CollectionUtils;
import yeyue.ruoyi.study.framework.common.util.network.NetworkUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import static yeyue.ruoyi.study.framework.common.constants.CommonConstants.SPILT_JOIN;

/**
 * servlet工具类
 *
 * @author yeyue
 * @date 2022-04-14 10:24:45
 */
public abstract class ServletUtils {

    /**
     * 判断是否属于JSON请求
     *
     * @param request 请求
     * @return 结果
     */
    public static boolean isJsonRequest(ServletRequest request) {
        return StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE);
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
     * 获取请求的param
     *
     * @param request 请求
     * @return 结果
     */
    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String[]> params = getParams(request);
        Map<String, String> result = CollectionUtils.newHashMap(params.size());
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            result.put(entry.getKey(), StringUtils.join(entry.getValue(), SPILT_JOIN));
        }
        return result;
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
     * 获取Body请求体
     *
     * @param request 请求
     * @return 字符串结果
     */
    public static String getBodyString(ServletRequest request) {
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
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String ip;
        // 先从默认header里查询
        for (String header : headers) {
            if (!NetworkUtils.isUnknown(ip = request.getHeader(header))) {
                return NetworkUtils.getMultistageReverseProxyIp(ip);
            }
        }
        // 再从自定义header里查询
        if (CollectionUtils.isNotEmpty(headerNames)) {
            for (String header : headerNames) {
                if (!NetworkUtils.isUnknown(ip = request.getHeader(header))) {
                    return NetworkUtils.getMultistageReverseProxyIp(ip);
                }
            }
        }
        // 最后从Remote里查询
        return NetworkUtils.getMultistageReverseProxyIp(request.getRemoteAddr());
    }
}
