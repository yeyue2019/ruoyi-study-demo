package yeyue.ruoyi.study.framework.common.monitor.trace.util;

import org.springframework.util.Assert;
import yeyue.ruoyi.study.framework.common.monitor.trace.context.YeyueTraceContext;
import yeyue.ruoyi.study.framework.common.monitor.trace.context.skywalking.SkyWalkingTraceContext;

/**
 * 获取Tracer信息的工具
 *
 * @author yeyue
 * @date 2022-04-15 12:00:31
 */
public abstract class TracerUtils {
    private static final YeyueTraceContext CONTEXT = new SkyWalkingTraceContext();

    /**
     * 封装traceId的获取方式
     *
     * @return traceId
     */
    public static String traceId() {
        return CONTEXT.traceId();
    }

    /**
     * 保存信息到上下文
     *
     * @param name  名称
     * @param value 值
     */
    public static <T> void put(String name, T value) {
        Assert.notNull(value, "对象不可为空");
        CONTEXT.put(name, value);
    }

    /**
     * 获取上下文的信息
     *
     * @param name  名称
     * @param clazz 类型
     * @return 结果
     */
    public static <T> T get(String name, Class<T> clazz) {
        return CONTEXT.get(name, clazz);
    }
}
