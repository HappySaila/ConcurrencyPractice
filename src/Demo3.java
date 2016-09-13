import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by wlsgra012 on 2016/08/15.
 */
public class Demo3 {
    private Random rand = new Random();
    private List<Integer> l1= new ArrayList<Integer>();
    private List<Integer> l2= new ArrayList<Integer>();
    //create two locks so that stage 1 and stage 2 can be accessed concurrently, but stage 1 itself
    //cannot be accessed by two threads
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void stage1(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock1){
            l1.add(rand.nextInt(100));
        }
    }
    private void stage2(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock2){
            l2.add(rand.nextInt(100));
        }
    }
    public void process(){
        for (int i = 0; i < 1000; i++) {
            stage1();
            stage2();
        }
    }
    public void main() {
        System.out.println("Starting...");
        long s = System.currentTimeMillis();
        //region creating treads
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        //endregion

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long e = System.currentTimeMillis();
        System.out.println("time: " +(e-s));
        System.out.println("list 1 size: "+l1.size());
        System.out.println("list 2 size: "+l1.size());
    }

    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        demo.main();
    }
}
