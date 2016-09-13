import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wlsgra012 on 2016/08/16.
 */
class Processor{
    private Scanner sc = new Scanner(System.in);
    public void Producer() throws InterruptedException{
        synchronized (this){//using this as an object means that only notify may be called from inside this object
            //this is because notify and wait have to share the same intrentic lock
            System.out.println("Thread has Started");
            wait();
            System.out.println("Woken up");
        }

    }

    public void Consumer() throws InterruptedException{
        synchronized (this){
            Thread.sleep(2000);
            System.out.println("Waiting to return...");
            sc.nextLine();
            System.out.println("Returned");
            notify();
            Thread.sleep(3000);
        }
    }
}

public class Demo6 {
    public BlockingQueue<Integer> queue= new ArrayBlockingQueue<>(10);
    public static Random rand = new Random();

    public static void main(String[] args) throws InterruptedException{
        final Processor p = new Processor();
        //create thread to constantly add onto the queue
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.Producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //create queue to take data from the queue
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.Consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
