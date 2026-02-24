package review.advanced_synchronisation_constructs.wait_notify;

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String take() {
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        empty = false;
        this.message = message;
        notifyAll();
    }

}

public class WaitNotifyExample {
    public static void main(String[] args) {
        Message message = new Message();

        Thread producer = new Thread(() -> {
           String[] messages = {"Hello", "How are you?", "I'm fine", "Done"};
           for (String m: messages) {
               message.put(m);
               System.out.println("Produced: " + m);
           }
        });

        Thread consumer = new Thread(() -> {
            String msg;
            do {
                msg = message.take();
                System.out.println("Consumed: " + msg);
            }while (!msg.equals("Done"));
        });

        producer.start();
        consumer.start();
    }
}
