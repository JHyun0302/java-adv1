package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 * 어떤 부분이 문제일까?
 * 바로 다음 두 부분이 원자적이지 않다는 문제가 있다.
 * 1. 락 사용 여부 확인
 * 2. 락의 값 변경
 */
public class SpinLockBad {

    // 락을 가져가면 true, 락을 반납하면 false
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while (true) {
            if (!lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true; // 2. 락의 값 변경
                break; // 락 획득 시 탈출
            } else {
                // 락을 획득할 때 까지 스핀 대기(바쁜 대기) 한다.
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
