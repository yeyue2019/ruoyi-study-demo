package yeyue.ruoyi.study.framework.common.monitor.trace.context.normal;

import org.apache.commons.lang3.StringUtils;
import yeyue.ruoyi.study.framework.common.monitor.trace.context.YeyueTraceContext;

import java.util.UUID;

/**
 * copy skywalking的traceId生成方式
 *
 * @author yeyue
 * @date 2022-04-15 12:06:50
 */
public class NormalTraceContext implements YeyueTraceContext {
    @Override
    public String traceId() {
        return generate();
    }

    @Override
    public <T> void put(String name, T value) {
    }

    @Override
    public <T> T get(String name, Class<T> clazz) {
        return null;
    }

    private static final String PROCESS_ID = UUID.randomUUID().toString().replaceAll("-", "");
    private static final ThreadLocal<IDContext> THREAD_ID_SEQUENCE = ThreadLocal.withInitial(
            () -> new IDContext(System.currentTimeMillis(), (short) 0));

    private NormalTraceContext() {
    }

    /**
     * Generate a new id, combined by three parts.
     * <p>
     * The first one represents application instance id.
     * <p>
     * The second one represents thread id.
     * <p>
     * The third one also has two parts, 1) a timestamp, measured in milliseconds 2) a seq, in current thread, between
     * 0(included) and 9999(included)
     *
     * @return unique id to represent a trace or segment
     */
    public static String generate() {
        return StringUtils.joinWith(
                ".",
                PROCESS_ID,
                String.valueOf(Thread.currentThread().getId()),
                String.valueOf(THREAD_ID_SEQUENCE.get().nextSeq())
        );
    }

    private static class IDContext {
        private long lastTimestamp;
        private short threadSeq;

        // Just for considering time-shift-back only.
        private long lastShiftTimestamp;
        private int lastShiftValue;

        private IDContext(long lastTimestamp, short threadSeq) {
            this.lastTimestamp = lastTimestamp;
            this.threadSeq = threadSeq;
        }

        private long nextSeq() {
            return timestamp() * 10000 + nextThreadSeq();
        }

        private long timestamp() {
            long currentTimeMillis = System.currentTimeMillis();

            if (currentTimeMillis < lastTimestamp) {
                // Just for considering time-shift-back by Ops or OS. @hanahmily 's suggestion.
                if (lastShiftTimestamp != currentTimeMillis) {
                    lastShiftValue++;
                    lastShiftTimestamp = currentTimeMillis;
                }
                return lastShiftValue;
            } else {
                lastTimestamp = currentTimeMillis;
                return lastTimestamp;
            }
        }

        private short nextThreadSeq() {
            if (threadSeq == 10000) {
                threadSeq = 0;
            }
            return threadSeq++;
        }
    }
}
