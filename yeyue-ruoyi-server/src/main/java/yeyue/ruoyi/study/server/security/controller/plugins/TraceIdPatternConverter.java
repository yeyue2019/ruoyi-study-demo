package yeyue.ruoyi.study.server.security.controller.plugins;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.*;

import static yeyue.ruoyi.study.server.security.controller.plugins.TraceIdPatternConverter.*;

/**
 * traceId追踪插件
 *
 * @author fred
 * @date 2021-09-30 15:12
 */
@Plugin(name = "TraceIdPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({PATTERN, TARGET})
public class TraceIdPatternConverter extends LogEventPatternConverter {
    public static final String PATTERN = "tr";
    public static final String TARGET = "trace";

    private static final TraceIdPatternConverter INSTANCE = new TraceIdPatternConverter();

    public static TraceIdPatternConverter newInstance(final String[] options) {
        return INSTANCE;
    }

    private TraceIdPatternConverter() {
        super(TARGET, TARGET);
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder stringBuilder) {
        stringBuilder.append("TEST");
    }
}
