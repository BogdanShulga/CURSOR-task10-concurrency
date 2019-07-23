package main.java;

import main.java.filesForTask1.Body;
import main.java.filesForTask1.HttpRequest;
import main.java.filesForTask1.RunImpl;
import main.java.filesForTask1.Server;
import main.java.filesForTask2.Firefighter;

import java.util.concurrent.CyclicBarrier;

public class Main {
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

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () ->
                System.out.println("\nAlarm! Fire! In Thread " + Thread.currentThread().getName() + ".\n"));

        Firefighter bob = new Firefighter("Bob", cyclicBarrier);
        Firefighter peter = new Firefighter("Peter", cyclicBarrier);

        bob.start();
        peter.start();
    }
}
