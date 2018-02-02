package org.dbbeans.util;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

public class PrioritizedFutureTask<T> extends FutureTask<T> implements Comparable<PrioritizedFutureTask<T>> {

    private static final AtomicLong orderNumberGenerator = new AtomicLong();

    private final long priority;
    private final long order = orderNumberGenerator.incrementAndGet();

    public PrioritizedFutureTask(final Runnable runnable, final T result, final long priority) {
        super(runnable, result);
        this.priority = priority;
    }

    public PrioritizedFutureTask(final Callable<T> callable, final long priority) {
        super(callable);
        this.priority = priority;
    }

    @Override
    public int compareTo(final PrioritizedFutureTask<T> task) {
        int result = compareLongs(priority, task.priority);

        if (result == 0)
            result = compareLongs(order, task.order);

        return result;
    }

    private int compareLongs(final long l1, final long l2) {
        if (l1 < l2)
            return -1;

        if (l1 > l2)
            return 1;

        return 0;
    }
}
