package thread.cas;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * `AtomicInteger` 가 제공하는 `incrementAndGet()` 코드도 앞서 우리가 직접 작성한 `incrementAndGet()` 코드와 똑같이 CAS를 활용하도록 작성되어 있다.
 * CAS를 사용하면 락을 사용하지 않지만, 대신에 다른 스레드가 값을 먼저 증가해서 문제가 발생하는 경우 루프를 돌며 재시도를 하는 방식을 사용
 */
public class CasMainV3 {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + " resultValue: " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            sleep(100); // 스레드 동시 실행을 위한 대기
            log("getVaule: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1); //CAS 연산
            log("result: " + result);
        } while (!result);

        return getValue + 1;
        // 'atomicInteger.get();' 처럼 return 하면 다른 스레드가 값을 변경시킬 수도 있기 때문에 'getVaule + 1;'로 return
//        return atomicInteger.get();
    }
}
