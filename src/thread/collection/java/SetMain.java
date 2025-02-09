package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

    public static void main(String[] args) {
        // 순서 보장 X
        Set<Integer> copySet = new CopyOnWriteArraySet<>();
        copySet.add(1);
        copySet.add(2);
        copySet.add(3);
        System.out.println("copySet = " + copySet);

        // 순서 보장 O
        // `ConcurrentSkipListSet` 은 `TreeSet` 의 대안이다. 데이터의 정렬 순서를 유지한다. `Comparator` 사용 가능
        Set<Object> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(2);
        skipSet.add(3);
        skipSet.add(1);
        System.out.println("skipSet = " + skipSet);
    }
}
