public class DemoPriorityThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Hello World");
        });
        thread1.setName("Thread 1");
//        thread1.setPriority(Thread.MIN_PRIORITY);
        thread1.start();

//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println("Thread 2: " + i);
//            }
//        });
//        thread2.setName("Thread 2");
//        thread2.setPriority(Thread.MAX_PRIORITY);
//        thread2.start();

        System.out.println("Priority of main " + Thread.currentThread().getPriority()); // 5
        System.out.println("Priority of " + thread1.getName() + ": " + thread1.getPriority()); // 5

        Thread.currentThread().setPriority(7);
        System.out.println("Priority of main " + Thread.currentThread().getPriority()); // 7

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Hello World");
        });
        thread2.setName("Thread 2");

        System.out.println("Priority of " + thread2.getName() + ": " + thread2.getPriority()); // 7
    }
}
