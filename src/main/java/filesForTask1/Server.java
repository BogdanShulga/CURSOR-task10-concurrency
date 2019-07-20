package main.java.filesForTask1;

public class Server {
    public void accept(Runnable runnable) {
        new Thread(runnable).start();
    }
}
