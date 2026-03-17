public class Logger {

    public synchronized void log(String message) {
        System.out.println(message);
    }

}