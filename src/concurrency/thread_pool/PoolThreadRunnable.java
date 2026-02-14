package concurrency.thread_pool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {
    private Thread thread = null;
    private final BlockingQueue<Runnable> taskQueue;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
//                log or otherwise report exception
//                but keep thread pool alive
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
//        break pool thread out of deque() call
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
