package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100); // 시간을 줄임
        log("작업 중단 지시 runFlag=false");
        thread.interrupt(); // 작업 중단 지시
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) { // 인터럽트 상태 변경 X
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // true <- 문제!
            /**
             * `work` 스레드는 이후에 자원을 정리하는 코드를 실행하는데, 이때도 인터럽트의 상태는 계속 `true`로 유지된다.
             * 이때 만약 인터럽트가 발생하는 `sleep()` 과 같은 코드를 수행한다면, 해당 코드에서 인터럽트 예외가 발생하게 된다.
             * 이것은 우리가 기대한 결과가 아니다! 우리가 기대하는 것은 `while()` 문을 탈출하기 위해 딱 한 번만 인터럽트를 사용하는 것이지,
             * 다른 곳에서도 계속해서 인터럽트가 발생하는 것이 아니다.
             * 결과적으로 자원 정리를 하는 도중에 인터럽트가 발생해서, 자원 정리에 실패한다.
             * 자바에서 인터럽트 예외가 한 번 발생하면, 스레드의 인터럽트 상태를 다시 정상(`false`)으로 돌리는 것은 이런 이유 때문이다.
             */
            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("자원 정리 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted()); // false
            }
            log("작업 종료");
        }
    }
}
