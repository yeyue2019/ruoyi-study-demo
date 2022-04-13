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
    public static final String TRACE_ID_NAME = "r-trace";

    // servlet拦截器执行顺序

    public static final int CORS_FILTER_ORDER = Integer.MIN_VALUE;

    public static final int TRACE_FILTER_ORDER = CORS_FILTER_ORDER + 1;
}
