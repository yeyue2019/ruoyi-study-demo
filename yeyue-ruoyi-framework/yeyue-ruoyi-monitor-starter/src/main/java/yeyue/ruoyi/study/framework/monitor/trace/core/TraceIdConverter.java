// package yeyue.ruoyi.study.framework.monitor.trace.core;
//
//
// import org.apache.logging.log4j.core.LogEvent;
// import org.apache.logging.log4j.core.config.plugins.Plugin;
// import org.apache.logging.log4j.core.pattern.*;
//
// /**
//  * traceId追踪插件
//  *
//  * @author fred
//  * @date 2021-09-30 15:12
//  */
// @Plugin(name = "TraceIdConverter", category = "Converter")
// @ConverterKeys({"traceId"})
// public class TraceIdConverter extends LogEventPatternConverter {
//
//     /**
//      * Constructs an instance of LoggingEventPatternConverter.
//      *
//      * @param name  name of converter.
//      * @param style CSS style for output.
//      */
//     protected TraceIdConverter(String name, String style) {
//         super(name, style);
//     }
//
//     public static TraceIdConverter newInstance(String[] options) {
//         return new TraceIdConverter("traceId", "traceId");
//     }
//
//     @Override
//     public void format(LogEvent event, StringBuilder toAppendTo) {
//         toAppendTo.append("AAA");
//     }
// }
//
