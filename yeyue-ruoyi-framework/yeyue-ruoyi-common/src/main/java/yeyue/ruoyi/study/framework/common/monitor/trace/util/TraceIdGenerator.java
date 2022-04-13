// package yeyue.ruoyi.study.framework.common.monitor.trace.util;
//
// import org.apache.commons.lang3.StringUtils;
//
// import java.util.UUID;
//
// /**
//  * 全局traceId生成器
//  *
//  * @author yeyue
//  * @date 2022-04-13 21:42:25
//  */
// public abstract class TraceIdGenerator {
//
//     private static final String PROCESS_ID = UUID.randomUUID().toString().replaceAll("-", "");
//     private static final ThreadLocal<IDContext> THREAD_ID_SEQUENCE = ThreadLocal.withInitial(
//             () -> new IDContext(System.currentTimeMillis(), (short) 0));
//
//
//
//     /**
//      * 生成一个由三部分组成的Id
//      *
//      * 1.应用程序Id
//      * 2.线程Id
//      * 3.流水号
//      */
//     public static String generate() {
//         return StringUtils.joinWith(
//                 ".",
//                 PROCESS_ID,
//                 String.valueOf(Thread.currentThread().getId()),
//                 String.valueOf(THREAD_ID_SEQUENCE.get().nextSeq())
//         );
//     }
//
//     private static class IDContext {
//         private long lastTimestamp;
//         private short threadSeq;
//
//         // Just for considering time-shift-back only.
//         private long lastShiftTimestamp;
//         private int lastShiftValue;
//
//         private IDContext(long lastTimestamp, short threadSeq) {
//             this.lastTimestamp = lastTimestamp;
//             this.threadSeq = threadSeq;
//         }
//
//         private long nextSeq() {
//             return timestamp() * 10000 + nextThreadSeq();
//         }
//
//         private long timestamp() {
//             long currentTimeMillis = System.currentTimeMillis();
//
//             if (currentTimeMillis < lastTimestamp) {
//                 // Just for considering time-shift-back by Ops or OS. @hanahmily 's suggestion.
//                 if (lastShiftTimestamp != currentTimeMillis) {
//                     lastShiftValue++;
//                     lastShiftTimestamp = currentTimeMillis;
//                 }
//                 return lastShiftValue;
//             } else {
//                 lastTimestamp = currentTimeMillis;
//                 return lastTimestamp;
//             }
//         }
//
//         private short nextThreadSeq() {
//             if (threadSeq == 10000) {
//                 threadSeq = 0;
//             }
//             return threadSeq++;
//         }
//     }
// }
