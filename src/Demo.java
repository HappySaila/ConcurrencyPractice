/**
 * Created by wlsgra012 on 2016/08/15.
 */
class thread extends Thread{
    private int wait;
    public thread(int i){
        wait = i;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("loop->"+i);

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Demo {
    public static void main(String[] args) {
        thread t1 = new thread(100);
        thread t2 = new thread(300);

        t1.start();
        t2.start();

    }
}
