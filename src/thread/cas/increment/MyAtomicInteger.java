package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 성능도 `synchronized` , `Lock(ReentrantLock)` 을 사용하는 경우보다 1.5 ~ 2배 정도 빠르다.
 */
public class MyAtomicInteger implements IncrementInteger{

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
