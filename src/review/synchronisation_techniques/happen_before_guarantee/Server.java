package review.synchronisation_techniques.happen_before_guarantee;

public class Server {
    private volatile boolean isRunning = true;

    public void shutdown() {
        isRunning = false;
    }

    public void run() {
        System.out.println("Server is running");
        while(isRunning) {

        }
        System.out.println("Server has stopped.");
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();

        Thread serverThread = new Thread(server::run);
        serverThread.start();

        Thread.sleep(2000);

        Thread shutdownThread = new Thread(server::shutdown);
        shutdownThread.start();

        serverThread.join();


        System.out.println("Server shutdown complete.");

    }
}
