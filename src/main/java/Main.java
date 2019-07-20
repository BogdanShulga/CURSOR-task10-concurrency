package main.java;

import main.java.filesForTask1.Body;
import main.java.filesForTask1.HttpRequest;
import main.java.filesForTask1.RunImpl;
import main.java.filesForTask1.Server;
import main.java.filesForTask2.Firefighter;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final Object lock = new Object();
    private static volatile AtomicInteger counterOfWaitingThreads = new AtomicInteger(0);

    public static void increaseCounterOfWaitingThreads() {
        counterOfWaitingThreads.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println("This is Thread-" + Thread.currentThread().getName());

        System.out.println("Task 1. Parallel not blocking thread execution.\n");
        System.out.println("Putting requests with some body and request type to the server:\n");

        Server server = new Server();
        for (HttpRequest request : HttpRequest.values()) {
            server.accept(new RunImpl(new Body(), request));
        }


        // waiting until task 1 will be finished
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTask 2. Fire department.\n");

        Firefighter bob = new Firefighter("Bob", lock);
        Firefighter peter = new Firefighter("Peter", lock);


        bob.start();
        peter.start();

        boolean stop = false;
        while (!stop) {
            if (Main.counterOfWaitingThreads.get() < 2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                stop = true;
            }
        }

        System.out.println("\nWaiting to fire, as all are ready. In Thread " + Thread.currentThread().getName() + ".");

        System.out.println("\nAlarm! Fire! In Thread " + Thread.currentThread().getName() + ".\n");

        synchronized (lock) {
            bob.setStop(true);
            peter.setStop(true);
            lock.notifyAll();
        }
    }
}
