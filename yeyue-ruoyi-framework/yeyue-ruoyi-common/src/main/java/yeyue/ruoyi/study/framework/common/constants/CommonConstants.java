package yeyue.ruoyi.study.framework.common.constants;

/**
 * 全局通用变量声明
 *
 * @author yeyue
 * @date 2022-04-09 17:42:29
 */
public abstract class CommonConstants {

    /**
     * 业务成功返回码
     */
    public static final int CODE_SUCCESS_INT = 0;
    public static final String CODE_SUCCESS_STR = String.valueOf(CODE_SUCCESS_INT);

    /**
     * 时间Pattern实例
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    public static final String INDEX_JOIN = ":";
    public static final String SPLIT_JOIN = ",";
    /**
     * 分页查询关键字
     */
    public static final Integer PAGE_NO = 1;
    public static final Integer PAGE_SIZE = 10;

    /**
     * 顺序 - 升序
     */
    public static final String ORDER_ASC = "asc";
    /**
     * 顺序 - 降序
     */
    public static final String ORDER_DESC = "desc";

    /**
     * TraceId的使用名称
     */
    public static final String TRACE_ID_NAME = "trace-id";

    /**
     * http请求的追踪信息
     */

    public static final String TRACE_REQ_URL = "req-url";
    public static final String TRACE_REQ_METHOD = "req_method";
    public static final String TRACE_REQ_PARAM = "req_param";
    public static final String TRACE_REQ_HEADER = "req_header";
    public static final String TRACE_REQ_BODY = "req_body";
    public static final String TRACE_RES_CODE = "res_code";
    public static final String TRACE_RES_BODY = "res_body";
    public static final String TRACE_RES_MSG = "res_msg";

    public static final String TRACE_RES_HEADER = "res-header";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String PATTERN_ALL = "/**";

    public static final String CORS_ALLOW = "*";

    // servlet拦截器执行顺序

    public static final int CORS_FILTER_ORDER = Integer.MIN_VALUE;

    public static final int TRACE_FILTER_ORDER = CORS_FILTER_ORDER + 1;

    public static final int REQUEST_CACHE_FILTER_ORDER = Integer.MIN_VALUE + 2;
}
