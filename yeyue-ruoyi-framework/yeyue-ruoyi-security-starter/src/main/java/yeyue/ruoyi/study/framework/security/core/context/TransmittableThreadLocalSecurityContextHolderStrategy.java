package yeyue.ruoyi.study.framework.security.core.context;

import org.springframework.security.core.context.*;
import org.springframework.util.Assert;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 基于 TransmittableThreadLocal 实现的 Security Context 持有者策略 目的是，避免 @Async 等异步执行时，原生 ThreadLocal 的丢失问题
 *
 * @author yeyue
 * @date 2022-04-19 14:39:43
 */
public class TransmittableThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

    /**
     * 使用 TransmittableThreadLocal 作为上下文
     */
    private static final ThreadLocal<SecurityContext> CONTEXT_HOLDER = new TransmittableThreadLocal<>();

    @Override
    public void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    @Override
    public SecurityContext getContext() {
        SecurityContext ctx = CONTEXT_HOLDER.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            CONTEXT_HOLDER.set(ctx);
        }
        return ctx;
    }

    @Override
    public void setContext(SecurityContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        CONTEXT_HOLDER.set(context);
    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}
