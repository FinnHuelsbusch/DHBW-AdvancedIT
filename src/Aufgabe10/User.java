package Aufgabe10;

import java.util.ArrayList;

public class User implements Runnable{

    private FiFoQueue fiFoQueue;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        FiFoQueue fiFoQueue = new FiFoQueue();
        fiFoQueue.put("1");
        fiFoQueue.put("2");
        threads.add(new Thread(new User(fiFoQueue)));
        threads.add(new Thread(new User(fiFoQueue)));
        threads.add(new Thread(new User(fiFoQueue)));
        threads.add(new Thread(new User(fiFoQueue)));
        for ( Thread t : threads ) {
            t.start();
        }
        for ( Thread t : threads ) {
            t.join();
        }
        String currentElement = fiFoQueue.get();
        ArrayList<String> list = new ArrayList<>();
        while (currentElement != null) {
            list.add(currentElement);
            currentElement = fiFoQueue.get();
        }
        System.out.println("------------------------------");
        for ( String s : list ) {
            System.out.println(s);
        }
    }

    public User(FiFoQueue fiFoQueue) {
        this.fiFoQueue = fiFoQueue;
    }

    @Override
    public void run() {
        fiFoQueue.put(Thread.currentThread().getName());
        fiFoQueue.put(Thread.currentThread().getName());
        fiFoQueue.put(Thread.currentThread().getName());
        fiFoQueue.put(Thread.currentThread().getName());
        System.out.println(fiFoQueue.get());
    }
}
