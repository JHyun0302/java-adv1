package thread.cas;

import static util.MyLogger.log;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue1 = " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // thread1: 0,
            log("getVaule: " + getValue);
            //thread2 value -> 1
            result = atomicInteger.compareAndSet(getValue, getValue + 1); //CAS 연산
            log("result: " + result);
        } while (!result);

        return getValue + 1;
    }
}
