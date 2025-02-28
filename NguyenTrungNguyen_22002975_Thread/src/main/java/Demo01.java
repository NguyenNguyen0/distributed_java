import task.PrintCharTask;

public class Demo01 {
    public static void main(String[] args) {
        PrintCharTask task1 = new PrintCharTask('a', 100);
        Thread thread1 = new Thread(task1); // Default name: Thread-0, state: NEW
        thread1.setName("Thread 1");

        thread1.run(); // state: RUNNABLE
    }
}
