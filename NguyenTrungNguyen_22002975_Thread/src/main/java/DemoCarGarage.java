import com.sun.jdi.event.ThreadDeathEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class DemoCarGarage {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Garage garage = new Garage(5);
        Runnable enterTask = () -> {
            try {
                garage.enter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable leaveTask = () -> {
            try {
                garage.leave();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            pool.submit(leaveTask);
        }

        for (int i = 0; i < 5; i++) {
            pool.submit(enterTask);
        }

        pool.shutdown();

        while (!pool.isTerminated()) {}

        System.out.println("Available space: " + garage.getAvailableSpace());
    }
}

class Garage {
    private int capacity;
    private int availableSpace;

    public Garage(int capacity) {
        this.capacity = capacity;
        this.availableSpace = capacity;
    }

    public synchronized void enter() throws InterruptedException {
        while (availableSpace <= 0) {
            System.out.println("Garage is full, " + Thread.currentThread().getName() + " wait");
            wait();
        }

        availableSpace--;
        System.out.println(Thread.currentThread().getName() + " entered, available space: " + availableSpace);
        notifyAll();
    }

    public synchronized void leave() throws InterruptedException {
        while (availableSpace >= capacity) {
            System.out.println("Garage is empty, " + Thread.currentThread().getName() + " wait");
            wait();
        }

        availableSpace++;
        System.out.println(Thread.currentThread().getName() + " left, available space: " + availableSpace);
        notifyAll();
    }

    public int getAvailableSpace() {
        return availableSpace;
    }
}