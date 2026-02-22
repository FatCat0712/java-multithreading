package review.synchronisation_techniques.thread_safety;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {
    private static final String LOG_FILE = "overlap_log.txt";

    public synchronized void writeLog(String message, int delay)  {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            for(char ch : message.toCharArray()) {
                writer.print(ch);
                writer.flush();
                Thread.sleep(delay); // introduce delay to simulate overlay
            }
            writer.println(); // Move to next line after message
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogWriter logWriter = new LogWriter();

        Runnable task1 = () -> {
            logWriter.writeLog("Message from thread 1", 10);
        };

        Runnable task2 = () -> {
            logWriter.writeLog("Message from thread 2", 10);
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
