package main.java.filesForTask1;

public class RunImpl implements Runnable {
    private Body body;
    private HttpRequest request;

    public RunImpl(Body body, HttpRequest request) {
        this.body = body;
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("This is " + request + " request in " + Thread.currentThread().getName() + " with " + body);
    }
}
