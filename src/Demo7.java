import java.util.LinkedList;
import java.util.Random;

/**
 * Created by wlsgra012 on 2016/08/16.
 */
class Task {
    private Object lock = new Object();
    private int Limit = 10;
    private LinkedList<Integer> list = new LinkedList<>();
    private Random rand = new Random();
    public void Producer() throws InterruptedException {
        //will add to the list
        int val = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == Limit) {
                    //will wait only if the list has 10 elements
                    //placed inside a list to recheck the value of size
                    //if the list has not reached the limit then the thread will hold the lock
                    lock.wait();
                }

                //if this statement has reach then list is not full and we need to add
                //an element. Lock aquired
                list.add(++val);
                //notify that consumer may try get a value
                lock.notify();
            }
            thread.sleep(rand.nextInt(400));
        }
    }

    public void Consumer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    //wait for a value to be added to the list else we will wait
                    lock.wait();
                }
                //reached here then there is an element
                //in the array that we can get
                System.out.println("value: " + list.removeFirst() + " ;size" + list.size());
                //notify producer to add to list
                lock.notify();
            }
            thread.sleep(rand.nextInt(500));
        }
    }
}
public class Demo7 {
    private static final Task task = new Task();
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.Producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.Consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
