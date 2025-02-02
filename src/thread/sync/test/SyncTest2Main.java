package thread.sync.test;

import static util.MyLogger.log;

/**
 * 스택 영역은 각각의 스레드가 가지는 별도의 메모리 공간이다. 이 메모리 공간은 다른 스레드와 공유하지 않는다.
 * 지역 변수는 스레드의 개별 저장 공간인 스택 영역에 생성된다.
 * 지역 변수는 절대로! 다른 스레드와 공유되지 않는다!
 */
public class SyncTest2Main {
    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }

    static class MyCounter {
        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            log("결과: " + localValue);
        }
    }
}

