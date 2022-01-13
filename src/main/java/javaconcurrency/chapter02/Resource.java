package javaconcurrency.chapter02;

import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private String name;
    private int count;
    ReentrantLock lock = new ReentrantLock();

    public Resource(String name) {
        this.name = name;
    }

    public void statisticResource() {
        if (lock.tryLock()) {
            try {
                System.out.println("statistic resource");
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("搶佔鎖失敗");
        }
    }

    public void saveResource(Resource resource) {
        if (lock.tryLock()) {
            try {
                System.out.println("statistic resource");
                resource.statisticResource();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("搶佔鎖失敗");
        }
    }
}
