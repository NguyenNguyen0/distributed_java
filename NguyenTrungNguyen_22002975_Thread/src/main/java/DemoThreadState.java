public class DemoThreadState {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " Execute task " + "Hello");
        };

        Thread thread1 = new Thread(task, "thread 1");
        System.out.println("State thread 1: " + thread1.getState()); // NEW
        thread1.start();
        System.out.println("State thread 1: " + thread1.getState()); // RUNNABLE

//        while (thread1.isAlive()) {
//            System.out.println("State thread 1: " + thread1.getState());
//        }

        thread1.join();

        System.out.println("State thread 1: " + thread1.getState()); // TERMINATED

//        thread1.run();
//        System.out.println("State thread 1: " + thread1.getState()); // NEW
    }
}
