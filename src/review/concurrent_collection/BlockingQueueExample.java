package review.concurrent_collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Runnable producer = () -> {
          for(int i = 0; i < 10; i++) {
              System.out.println("Produced: " + i);
              try {
                  queue.put(i);
              } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
              }
          }
        };

        Runnable consumer = () -> {
            for(int i = 0; i < 10; i++) {
                try {
                    int value = queue.take();
                    System.out.println("Consumed: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
