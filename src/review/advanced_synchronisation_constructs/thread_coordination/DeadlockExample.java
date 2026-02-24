package review.advanced_synchronisation_constructs.thread_coordination;

class Resource {
    public  void methodA(Resource other) {
        Resource firstLock = this.hashCode() < other.hashCode() ? this : other;
        Resource secondLock = this.hashCode() < other.hashCode() ? other : this;

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " acquired lock on "
                    + ((firstLock == this) ? "Resource A" : "Resource B"));

            if(firstLock != this) {
                firstLock.methodB();
            }

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on  " +
                        (secondLock == this ? "Resource A" : "Resource B"));
                if(secondLock != this) {
                    secondLock.methodB();
                }
            }
        }
    }

    public  synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " methodB executing");

    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread thread1 = new Thread(() -> resource1.methodA(resource2));
        Thread thread2 = new Thread(() -> resource2.methodA(resource1));

        thread1.start();
        thread2.start();

    }
}
