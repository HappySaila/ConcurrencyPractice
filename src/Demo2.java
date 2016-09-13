/**
 * Created by wlsgra012 on 2016/08/15.
 */
public class Demo2 {
    private int count=0;
    public static final int number=100000000;

    public synchronized void inc(){
        count++;
    }
    public int doWork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Demo2.number; i++) {
                    inc();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Demo2.number; i++) {
                    inc();
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

        return count;
    }



    public static void main(String[] args) {
        Demo2 demo  = new Demo2();
        System.out.println("running...");
        System.out.println(demo.doWork());
    }
}
