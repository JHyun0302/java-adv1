package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 runFlag=false");
        thread.interrupt(); // 작업 중단 지시
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000); // interrupt() 로 인해  TIMED_WAITING -> RUNNABLE 상태 변경
                }
            } catch (InterruptedException e) { // TIMED_WAITING -> RUNNABLE 상태로 변경되면서 예외 발생
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // 인터럽트 상태에서 1번 풀리면 false!
                log("interrupt message = " + e.getMessage());
                log("state = " + Thread.currentThread().getState());
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
