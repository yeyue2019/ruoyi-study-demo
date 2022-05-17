package yeyue.ruoyi.study.framework.common.servlet.constants;

/**
 * @author yeyue
 * @date 2022-04-21 15:37:03
 */
public abstract class ServletConstants {

    /**
     * http请求的追踪信息
     */
    public static final String TRACE_REQUEST = "trace.request";

    public static final String TRACE_RESPONSE = "trace.response";

    public static final String TRACE_HEADER = "TRACE-ID";

    public static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String TEST_TOKEN_NAME = "ruoyi-study-demo";

    public static final String PATTERN_ALL = "/**";

    public static final String CORS_ALLOW = "*";

    // servlet拦截器执行顺序

    public static final int CORS_FILTER_ORDER = Integer.MIN_VALUE;

    public static final int TRACE_FILTER_ORDER = CORS_FILTER_ORDER + 1;

    public static final int SERVICE_FILTER_ORDER = Integer.MIN_VALUE + 2;

    // 自定义参数

    public static final String REQUEST_ATTRIBUTE_SECURITY_USER_ID = "security.userId";

}
