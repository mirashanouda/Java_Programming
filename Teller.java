import java.security.SecureRandom;

public class Teller extends Thread {
    //aka: consumer
    SecureRandom rand = new SecureRandom();
    private SharedQueue q;
    public int tNum;
    public Teller(int i, SharedQueue q){
        this.q = q;
        this.tNum = i;
    }

    @Override
    public void run() {
//        System.out.printf("-Start- processing customer %d with priority %d\n", c.getI(), c.getP());
        while (q.continueWork()) {
            try {
                int t = rand.nextInt(5);
                Thread.sleep(t + 1000);
            } catch (InterruptedException e) {
                System.err.println("Error in Sleep");
                throw new RuntimeException(e);
            }
            if (q.continueWork()) {
                Customer c = q.takeFromQueue();
                System.out.printf("-Finish- processing customer %d with priority %d -- by Teller %d\n", c.getI(), c.getP(), tNum);
            }
        }
    }
}
