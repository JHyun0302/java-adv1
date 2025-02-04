package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue - 예외
 * `BlockingQueue` 의 `add(data)` , `remove()` 를 사용해서, 대기시 예외가 발생
 */
public class BoundedQueueV6_4 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // java.lang.illegalStateException: Queue full
    }

    @Override
    public String take() {
        return queue.remove(); // java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
