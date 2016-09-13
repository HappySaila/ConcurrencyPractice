import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wlsgra012 on 2016/08/16.
 */
class process implements Runnable{
    private CountDownLatch latch;
    public process(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Waiting at the door...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
    }
}
public class Demo5 {
    public static void main(String[] args) {
        int threads = 10; //number of people at the party
        CountDownLatch latch  = new CountDownLatch(3);
        ExecutorService ex = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < 10; i++) {
            ex.submit(new process(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all enter the party");

    }

}
