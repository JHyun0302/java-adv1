package thread.bounded;

import static util.MyLogger.log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue - 즉시 반환
 * `BlockingQueue` 의 `offer(data)` , `poll()` 를 사용해서 스레드를 대기하지 않고 즉시 반환
 */
public class BoundedQueueV6_2 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data);
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
