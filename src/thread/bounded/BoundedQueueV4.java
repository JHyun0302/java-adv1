package thread.bounded;

import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 * condition.await()
 * - Object.wait()` 와 유사한 기능이다. 지정한 `condition` 에 현재 스레드를 대기(`WAITING` ) 상태로 보관한다.
 * - 이때 `ReentrantLock` 에서 획득한 락을 반납하고 대기 상태로 `condition` 에 보관된다.
 * condition.signal()
 * - Object.notify()` 와 유사한 기능이다. 지정한 `condition` 에서 대기중인 스레드를 하나 깨운다.
 * - 깨어난 스레드는 `condition` 에서 빠져나온다.
 */
public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();

        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    condition.await(); // condition에서 스레드 대기하도록 명령 (wait() 유사)
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            condition.signal(); // codition에서 대기 중인 스레드에게 시그널 명령 (notify() 유사)
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    condition.await(); // condition에서 스레드 대기하도록 명령 (wait() 유사)
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            condition.signal(); // codition에서 대기 중인 스레드에게 시그널 명령 (notify() 유사)
            return data;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
