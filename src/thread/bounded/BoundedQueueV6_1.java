package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue` 인터페이스의 대표적인 구현체
 * `ArrayBlockingQueue` : 배열 기반으로 구현되어 있고, 버퍼의 크기가 고정되어 있다.
 * `LinkedBlockingQueue` : 링크 기반으로 구현되어 있고, 버퍼의 크기를 고정할 수도, 또는 무한하게 사용할 수도 있다.
 */
public class BoundedQueueV6_1 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_1(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        try {
            queue.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
