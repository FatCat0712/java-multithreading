package concurrency.memory;

public class MyRunnable implements Runnable {
    private int count = 0;
//    private MyObject myObject = null;
//
//    public MyRunnable() {
//    }
//
//    public MyRunnable(MyObject myObject) {
//        this.myObject = myObject;
//    }

    @Override
    public void run() {
//        MyObject myObject = new MyObject();
//        System.out.println(myObject);
        for (int i = 0; i < 1_000_000; i++) {
            synchronized (this) {
                this.count++;
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + this.count);
    }
}
