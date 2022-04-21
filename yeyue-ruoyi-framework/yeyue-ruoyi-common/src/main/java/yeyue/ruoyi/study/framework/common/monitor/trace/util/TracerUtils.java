package yeyue.ruoyi.study.framework.common.monitor.trace.util;

import com.alibaba.fastjson.JSON;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import yeyue.ruoyi.study.framework.common.util.ids.IdUtils;

/**
 * TraceId 获取的工具类
 *
 * @author yeyue
 * @date 2022-04-20 14:40:22
 */
@Slf4j
public abstract class TracerUtils {
    private static final Tracer TRACER = new SkywalkingTracer();

    /**
     * 获取Skywalking的traceId
     *
     * @return 结果
     */
    public static String getTraceId() {
        return TraceContext.traceId();
    }

    /**
     * 生成一个新的TraceId
     *
     * @return 结果
     */
    public static String newTraceId() {
        return IdUtils.uuid(false);
    }

    /**
     * 将标签打入上下文
     *
     * @param name  标签名
     * @param value 标签内容
     */
    public static void tag(String name, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Boolean) {
            TRACER.activeSpan().setTag(name, (Boolean) value);
        } else if (value instanceof Number) {
            TRACER.activeSpan().setTag(name, (Number) value);
        } else if (value instanceof String) {
            TRACER.activeSpan().setTag(name, (String) value);
        } else {
            TRACER.activeSpan().setTag(name, JSON.toJSONString(value));
        }
    }
}
