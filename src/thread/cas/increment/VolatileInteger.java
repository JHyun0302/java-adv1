package thread.cas.increment;

/**
 * 단일 스레드가 사용하기에는 `BasicInteger` 보다 느리다. 그리고 멀티스레드 상황에도 안전하지 않다.
 */
public class VolatileInteger implements IncrementInteger{

    volatile private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
