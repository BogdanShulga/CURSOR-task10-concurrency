package main.java.filesForTask2;

import main.java.Main;

public class Firefighter extends Thread {
    private final Object lock;
    private String name;
    private boolean stop = false;

    public Firefighter(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("Fireman " + this.name + " is waiting in " + Thread.currentThread().getName());
            Main.increaseCounterOfWaitingThreads();
            while (!this.stop) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fireman " + name + " is going to the fire call in " + Thread.currentThread().getName());
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
