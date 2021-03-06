package yeyue.ruoyi.study.framework.monitor.trace.core;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import yeyue.ruoyi.study.framework.common.monitor.trace.util.TracerUtils;

/**
 * traceId追踪插件
 *
 * @author fred
 * @date 2021-09-30 15:12
 */
@Plugin(name = "TracerConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({"tr"})
public class TracerConverter extends LogEventPatternConverter {

    protected TracerConverter(String name, String style) {
        super(name, style);
    }

    public static TracerConverter newInstance(String[] options) {
        return new TracerConverter("tr", "tr");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(TracerUtils.getTraceId());
    }
}
