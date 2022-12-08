import java.security.SecureRandom;

public class ManageQueue implements Runnable {
    //aka: Producer
    private SharedQueue q;
    public ManageQueue(SharedQueue q){
        this.q = q;
//        Thread t = new Thread(this, "Queue Management Thread");
//        t.start(); // to start the thread once it's called
    }

    @Override
    public void run() {
        System.out.println("Started adding to queue");
        q.addToQueue();
//        System.out.println("Done!");
    }
}
