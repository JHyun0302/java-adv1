package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

//        boolean runFlag = true; // 문제: 메인 메모리에 변경 내용이 즉시 적용되지 않음. 캐시 메모리만 변경 됨!
        volatile boolean runFlag = true; // 성능을 약간 포기하는 대신에, 값을 읽을 때, 값을 쓸 때 모두 메인 메모리에 직접 접근 (캐시 메모리 접근하지 않으므로 성능 저하 O)

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                //runFlag가 false로 변하면 탈출
            }
            log("task 종료"); // 문제: task 종료 안 찍히고 계속 실행됨!
            /**
             * 캐시 메모리의 runFlag 값만 변한다는 것이다! 메인 메모리에 이 값이 즉시 반영되지 않는다.
             * 캐시 메모리를 메인 메모리에 반영하거나, 메인 메모리의 변경 내역을 캐시 메모리에 다시 불러오는 것은 언제 발생할까?
             * 이 부분은 CPU 설계 방식과 실행 환경에 따라 다를 수 있다. 즉시 반영될 수도 있고, 몇 밀리초 후에 될 수도 있고, 몇 초 후에 될 수도 있고, 평생 반영되지 않을 수도 있다.
             * 주로 컨텍스트 스위칭이 될 때, 캐시 메모리도 함께 갱신되는데, 이 부분도 환경에 따라 달라질 수 있다.
             */
        }
    }
}
