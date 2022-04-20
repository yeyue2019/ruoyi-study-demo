package yeyue.ruoyi.study.framework.common.monitor.trace.util;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import yeyue.ruoyi.study.framework.common.monitor.trace.ids.GlobalIdGenerator;

/**
 * TraceId 获取的工具类
 *
 * @author yeyue
 * @date 2022-04-20 14:40:22
 */
public abstract class TracerUtils {
    private static final String NONE = "TID : NA";

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
        return GlobalIdGenerator.generate();
    }
}
