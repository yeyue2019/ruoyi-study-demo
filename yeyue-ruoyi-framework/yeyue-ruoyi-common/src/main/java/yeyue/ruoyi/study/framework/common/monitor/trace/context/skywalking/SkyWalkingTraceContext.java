package yeyue.ruoyi.study.framework.common.monitor.trace.context.skywalking;

import com.alibaba.fastjson.JSON;
import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import yeyue.ruoyi.study.framework.common.exception.ServiceException;
import yeyue.ruoyi.study.framework.common.exception.common.GlobalErrorCode;
import yeyue.ruoyi.study.framework.common.monitor.trace.context.YeyueTraceContext;

/**
 * @author yeyue
 * @date 2022-04-15 12:03:14
 */
public class SkyWalkingTraceContext implements YeyueTraceContext {

    private static final Tracer TRACER = new SkywalkingTracer();

    private SkyWalkingTraceContext() {

    }

    public static SkyWalkingTraceContext INSTANCE = new SkyWalkingTraceContext();

    @Override
    public String traceId() {
        return TraceContext.traceId();
    }

    @Override
    public <T> void put(String name, T value) {
        if (value == null) {
            return;
        }
        if (value.getClass() == Boolean.class) {
            TRACER.activeSpan().setTag(name, (Boolean) value);
        } else if (value.getClass().getSuperclass() == Number.class) {
            TRACER.activeSpan().setTag(name, (Number) value);
        } else if (value.getClass() == String.class) {
            TRACER.activeSpan().setTag(name, (String) value);
        } else {
            TRACER.activeSpan().setTag(name, JSON.toJSONString(value));
        }
    }

    @Override
    public <T> T get(String name, Class<T> clazz) {
        throw new ServiceException(GlobalErrorCode.UNSUPPORTED_METHOD_IMPLEMENT);
    }

}
