package yeyue.ruoyi.study.framework.common.monitor.trace.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.StringUtils;

/**
 * traceId 上下文
 *
 * @author yeyue
 * @date 2022-04-20 01:03:32
 */
public abstract class TracerContext {

    private static final ThreadLocal<String> TRACER_INSTANCE = new TransmittableThreadLocal<>();

    /**
     * 获取TraceId
     * 如果线程中不存在则自动设置
     */
    public static String get() {
        String result = TRACER_INSTANCE.get();
        if (result == null) {
            return StringUtils.EMPTY;
        }
        return result;
    }

    /**
     * 设置TraceId
     */
    public static void set(String traceId) {
        TRACER_INSTANCE.set(traceId);
    }

    public static void remove() {
        TRACER_INSTANCE.remove();
    }
}
