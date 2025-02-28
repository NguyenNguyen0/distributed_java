import task.SumRangeTask;

import java.util.List;
import java.util.concurrent.*;

public class DemoThreadPool {
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> task1 = new SumRangeTask(1, 10000);
        Callable<Integer> task2 = new SumRangeTask(10000, 20000);
        Callable<Integer> task3 = new SumRangeTask(20000, 30000);
        Callable<Integer> task4 = new SumRangeTask(20000, 30000);
        Callable<Integer> task5 = new SumRangeTask(20000, 30000);

        List<Callable<Integer>> tasks = List.of(task1, task2, task3, task4, task5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<Integer>> futureTasks = executorService.invokeAll(tasks);

        Callable<Integer> task6 = new SumRangeTask(21000, 30000);

        executorService.submit(task6);

        executorService.shutdown();

        int total = futureTasks.stream()
                .mapToInt(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .sum();
        System.out.println("All tasks are completed: " + total);
    }
}
