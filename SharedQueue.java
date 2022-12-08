import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

public class SharedQueue {
    private final PriorityQueue<Customer> q = new PriorityQueue<Customer>(new ComparatorQueue());
    private final ArrayList<Customer> allCustomers = new ArrayList<>();
    int maxsize;

    public SharedQueue(int N, ArrayList<Integer> pris) {
        this.maxsize = N;
        for (int i = 0; i < pris.size(); i++) {
            this.allCustomers.add(new Customer(i, pris.get(i)));
        }
        System.out.println(allCustomers);
    }

    public synchronized void addToQueue() {
        while (continueWork()) {
            SecureRandom rand = new SecureRandom();
            try {
                int t = rand.nextInt(5);
                Thread.sleep(t + 1000);
            } catch (InterruptedException e) {
                System.err.println("Error in Sleep in ManageQueue");
                throw new RuntimeException(e);
            }
            while (q.size() == maxsize) {
                try {
                    wait();
                    System.out.println("Q is full in add to Queue");
                } catch (InterruptedException e) {
                    System.err.println("Error in addToQueue");
                    throw new RuntimeException(e);
                }
            }
            if (!allCustomers.isEmpty()) {
                Customer c = allCustomers.get(0);
                allCustomers.remove(0);
                this.q.add(c);
                c.turn = getIndex(c);
                System.out.printf("Added %d at %d\n", c.getI(), c.turn);
                notify(); //or notify
            } else {
//                notify();
                break;
            }
        }
        System.out.println("Done working");
    }

    public synchronized Customer takeFromQueue() {
            while (q.isEmpty()) {
                try {
                    System.out.println("Q is empty in take from Queue");
                    wait();
                } catch (InterruptedException e) {
                    System.err.println("Error in takeFromQueue");
                    throw new RuntimeException(e);
                }
            }
            Customer c = q.poll();
            System.out.printf("removed %d\n", c.getI());
            notify();
            return c;
    }

    public synchronized boolean continueWork(){
//        System.out.printf("q sz: %d - all: %d\n", q.size(), allCustomers.size());
        return q.size() != 0 || allCustomers.size() != 0;
    }

    private synchronized int getIndex(Customer c){
//        Object[] arr = q.toArray();
//        for (int i = 0; i < q.size(); i++)
//            if (arr[i] == c) return i;
//        System.out.println("Error");
//        return 0;
        int i = 0;
        for(Customer customer : q)
        {
            if(c == customer)
            {
                return  i;
            }

            i++;
        }

        return 0;
    }
}
