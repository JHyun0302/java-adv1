package thread.cas.increment;

/**
 * 안전한 임계 영역도 없고, `volatile`도 사용하지 않기 때문에 멀티스레드 상황에는 사용할 수 없다.
 * 단일 스레드가 사용하는 경우에 효율적이다.
 */
public class BasicInteger implements IncrementInteger{

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
