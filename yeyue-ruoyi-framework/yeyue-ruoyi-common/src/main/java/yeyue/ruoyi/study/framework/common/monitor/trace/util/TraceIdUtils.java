// package yeyue.ruoyi.study.framework.common.monitor.trace.util;
//
// import org.apache.skywalking.apm.toolkit.trace.TraceContext;
//
// /**
//  * @author yeyue
//  * @date 2022-04-13 22:39:36
//  */
// public abstract class TraceIdUtils {
//
//     public static final String DEFAULT_SKYWALKING_TRACE = "TID: N/A";
//     public static final String STOP_SKYWALKING_TRACE = "TID: Ignored_Trace";
//
//     public static String traceId() {
//         String traceId = TraceContext.traceId();
//         switch (traceId) {
//             case DEFAULT_SKYWALKING_TRACE:
//                 // 未知链路调用
//             case STOP_SKYWALKING_TRACE:
//                 // skywalking服务不存在
//                 traceId = TraceIdGenerator.generate();
//                 break;
//             default:
//         }
//         return traceId;
//     }
// }
