package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * `synchronized` 를 대신 적용해 주는 프록시를 만드는 방법
 * synchronized 프록시 방식의 단점
 * 단순 무식하게 모든 메서드에 `synchronized` 를 걸어버리는 것
 */
public class SynchronizedListMain {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("data1");
        list.add("data2");
        list.add("data3");
        System.out.println(list.getClass());
        System.out.println("list = " + list);
    }
}
