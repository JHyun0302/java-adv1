package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 연산을 사용하면, 1. 기대하는 값을 확인하고 2. 값을 변경하는 두 연산을 하나로 묶어서 원자적으로 제공한다
 */
public class CasMainV1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        boolean result1 = atomicInteger.compareAndSet(0, 1); // atomicInteger의 값이 0이면 1로 세팅
        // 1. if 기대하는 값이 0이면, 2. 값을 1로 변경해
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1); // atomicInteger의 값이 0이면 1로 세팅
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}
