import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wlsgra012 on 2016/08/15.
 */
public class Demo4 implements Runnable{
    private int id;
    public Demo4(int i){
        this.id = i+1;
    }
    public void run(){
        System.out.println("Starting: "+id);
        //work
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending: "+id);
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {//5 is the number of tasks you want to complete in total
            ex.submit(new Demo4(i));
        }

        ex.shutdown();
        System.out.println("All tasks are submitted!");
        try {
            ex.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed!");
    }
}
