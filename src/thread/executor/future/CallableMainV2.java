package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * `Future` 는 작업의 미래 결과를 받을 수 있는 객체이다.
 * `submit()` 호출시 `future` 는 즉시 반환된다. 덕분에 요청 스레드는 블로킹 되지 않고, 필요한 작업을 할 수 있다.
 */
public class CallableMainV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("submit() 호출");
        Future<Integer> future = es.submit(new MyCallable()); //`Thread.start()` 를 호출한 것과 비슷: 스레드의 작업 코드가 별도의 스레드에서 실행 (여기는 블로킹 아님)
        log("future 즉시 반환, future = " + future); //Future` 의 상태는 "Not completed"(미 완료)이고, 연관된 작업은 전달한 `taskA(MyCallable 인스턴스)` 이다.

        log("future.get() [블로킹] 메서드 호출 시작 -> main 스레드 WAITING");
        /**
         * 요청 스레드가 `future.get()` 을 호출하면 `Future` 가 완료 상태가 될 때 까지 대기한다. 이때 요청 스레드의 상태는 `RUNNABLE` `WAITING`
         */
        Integer result = future.get(); //main Thread sleep, thread-1이 작업 후 future에 value 담으면 `WAITING` -> `RUNNABLE` (여기서 블로킹)
        log("future.get() [블로킹] 메서드 호출 완료 -> main 스레드 RUNNABLE");

        log("result value = " + result);
        log("future 완료, future = " + future);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Callable 완료");
            return value;
        }
    }
}
