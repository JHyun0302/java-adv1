package thread.cas.increment;

/**
 * `synchronized` 를 사용한 안전한 임계 영역이 있기 때문에 멀티스레드 상황에도 안전하게 사용할 수 있다.
 * `MyAtomicInteger` 보다 성능이 느리다.
 */
public class SyncInteger implements IncrementInteger{

    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
