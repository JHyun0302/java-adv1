package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        sleep(1000);

        task.flag = false;
         log("flag = " + task.flag + ", count = " + task.count + " in main");
    }

    static class MyTask implements Runnable {
//        boolean flag = true; // main, work 스레드 다른 숫자
//        long count;

        volatile boolean flag = true; //main, work 스레드 모두 같은 숫자 (volatile 일 때 숫자가 더 적음 - 성능 저하)
        volatile long count;

        @Override
        public void run() {
            while (flag) {
                count++;
                //1억번에 한번씩 출력
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
