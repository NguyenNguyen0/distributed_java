import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoLock {
    public static Counter counter = new Counter();

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (counter) {
                    counter.increment();
                }
//                counter.increment();
            }
            System.out.println("Count: " + counter.getCount());
        };

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.submit(task);
        }

        pool.shutdown();

        while (!pool.isTerminated()) {}

        System.out.println("Total count: " + counter.getCount());
    }
}

class Counter {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        count++;
    }
//    public synchronized void increment() {
//        count++;
//    }
//    public void increment() {
//        lock.lock();
//        count++;
//        lock.unlock();
//    }

    public int getCount() {
        return count;
    }
}
