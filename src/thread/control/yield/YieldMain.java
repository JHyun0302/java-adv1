package thread.control.yield;

import static util.ThreadUtils.sleep;

import thread.start.HelloRunnable;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty - sout만 실행 -> 순서대로 거의 실행
//                sleep(1); // 2. sleep - 특정 스레드를 잠시 쉬게 한다. -> 순서가 계속 바뀜 (RUNNABLE -> TIMED_WAITING -> RUNNABLE 상태 변경)
                Thread.yield(); // 3. yield - 다른 스레드에 실행을 양보한다. -> sleep 보단 순서대로 실행됨! (RUNNING -> Ready 상태로 변경 - 자바에서는 두 상태를 구분 못함!)
            }
        }
    }
}
