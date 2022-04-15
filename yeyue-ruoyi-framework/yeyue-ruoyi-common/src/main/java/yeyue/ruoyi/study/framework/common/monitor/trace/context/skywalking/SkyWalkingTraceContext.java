package yeyue.ruoyi.study.framework.common.monitor.trace.context.skywalking;

import com.alibaba.fastjson.*;
import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import yeyue.ruoyi.study.framework.common.monitor.trace.context.YeyueTraceContext;

import java.util.*;

/**
 * @author yeyue
 * @date 2022-04-15 12:03:14
 */
public class SkyWalkingTraceContext implements YeyueTraceContext {
    private static final Tracer TRACER = new SkywalkingTracer();
    private static final Set<Class<?>> PRIMITIVE_TYPES = new HashSet<>(Arrays.asList(Boolean.class, Character.class, Number.class, String.class, Void.class));

    @Override
    public String traceId() {
        return TraceContext.traceId();
    }

    @Override
    public <T> void put(String name, T value) {
        String source;
        if (PRIMITIVE_TYPES.contains(value.getClass().getSuperclass())) {
            source = value.toString();
            TraceContext.putCorrelation(name, value.toString());
        } else {
            source = JSON.toJSONString(value);
        }
        TraceContext.putCorrelation(name, source);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String name, Class<T> clazz) {
        String source = TraceContext.getCorrelation(name).orElse(null);
        if (source == null) {
            return null;
        }
        if (PRIMITIVE_TYPES.contains(clazz)) {
            return (T) source;
        } else {
            return JSONObject.parseObject(name, clazz);
        }
    }

}
