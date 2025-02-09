package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.Random;

/**
 * 별도의 스레드에서 만든 무작위 값 하나를 받아오는 과정이 이렇게 복잡하다.
 * 작업 스레드(`Thread-1` )는 값을 어딘가에 보관해두어야 하고,
 * 요청 스레드(`main` )는 작업 스레드의 작업이 끝날 때까지 `join()` 을 호출해서 대기한 다음에,
 * 어딘가에 보관된 값을 찾아서 꺼내야 한다.
 */
public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
