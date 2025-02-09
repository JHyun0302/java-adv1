package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CAS 연산 덕분에 원자적이지 않은 두 연산을 다음과 같이 하나의 원자적인 연산으로 바꿀 수 있었다.
 * 1. 락을 사용하지 않는다면 락의 값을 변경
 *
 * CAS 단점
 * `BLOCKED` , `WAITING` 상태의 스레드는 CPU를 거의 사용하지 않지만, `RUNNABLE` 상태로 while문을 반복 실행하는 방식은 CPU 자원을 계속해서 사용하는 것
 */
public class SpinLock {

    // 락을 가져가면 true, 락을 반납하면 false
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득 시도");
        while (!lock.compareAndSet(false, true)) {
            // 락을 획득할 때 까지 스핀 대기(바쁜 대기) 한다.
            log("락 획득 실패 - 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
