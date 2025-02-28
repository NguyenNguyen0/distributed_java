import task.SumRangeTask;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;
import java.util.concurrent.FutureTask;

public class DemoCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int total = IntStream.range(1, 30000).sum();
        System.out.println("Total: " + total); // 449985000

        FutureTask<Integer> task1 = new FutureTask<>(new SumRangeTask(1, 10000));
        FutureTask<Integer> task2 = new FutureTask<>(new SumRangeTask(10000, 20000));
        FutureTask<Integer> task3 = new FutureTask<>(new SumRangeTask(20000, 30000));

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Total: " + (task1.get() + task2.get() + task3.get())); // 4498500
    }
}
