package review.synchronisation_techniques.lock_in_java;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataStore {
    private int data = 0;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public int readData() {
        rwLock.readLock().lock();
        try {
            return data;
        }finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeData(int newData) {
        rwLock.writeLock().lock();
        try {
            data = newData;
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataStore dataStore = new DataStore();
        Thread reader1 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                System.out.println("Reader 1 reads: " + dataStore.readData());
            }
        });

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Reader 2 reads: "+ dataStore.readData());
            }
        }) ;

        Thread writer = new Thread(() -> {
           for(int i = 0; i < 5; i++) {
               dataStore.writeData(i);
               System.out.println("Writer updates data to: " + i);
           }
        });

        reader1.start();
        reader2.start();
        writer.start();

        reader1.join();
        reader2.join();
        writer.join();
    }
}
