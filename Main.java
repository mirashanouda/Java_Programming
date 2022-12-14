/**
 * Java doesn't support multiple inheritance,
   so we can't inherit form the Thread class and another class at the same time

 Another way of creating classes
 * Runnable r = new MyThread(); // where my thread is a class extends Runnable
 * Thread task = new Thread(r);
 * task.start();

 * Runnable: multiple threads share the same objects.
 * Thread: Each thread creates a unique object and gets associated with it.
 *
 *
 * Design:
 *  1. data class for the buffer
 *  2. user input the customers type by num: 1 if reg and 2 if VIP
 *      2.1. exception handling
 *  3. ManageQueue is the producer and teller is the consumer
 *  4. shared buffer
 *      4.1. size == number of tellers
 *      4.2. input in the queue new date
 *
 * thoughts:
 * the shared buffer is priority queue
 * I am inserting who are coming into this queue and VIP should be given higher priority
 * */

import java.util.ArrayList;
import java.util.Scanner;


/**
 * take N and M
 * enter each M if VIP or regular
 * teller class implemets Runnable
 * loop to create multiple one of those
 *
 * */
public class Main {
    public static void main(String[] args) {
        int n, m;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Number of Tellers (N)");
        n = in.nextInt();
        System.out.println("Enter Number of Customers (M)");
        m = in.nextInt();
        System.out.print("Enter the type of each customer\n(1) for regular and (2) for VIP\n");
        ArrayList<Integer> pris = new ArrayList<>(); 
        for (int  i = 0; i < m; i++){
            while (true){
                try {
                    int p = in.nextInt();
                    if (p != 1 && p != 2) {
                        throw new Exception("Invalid customer type");
                    }
                    else {
                        pris.add(p);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Invalid customer type. Please input a value of (1) or (2)");
                }
            }
        }
        SharedQueue q = new SharedQueue(n, pris);
        Thread[] tellers = new Thread[n+1];
        for (int i = 1; i <= n; i++){
            tellers[i] =  new Teller(i, q);
            tellers[i].start();
        }
        Thread queueSystem = new Thread(new ManageQueue(q));
        queueSystem.start();
    }
}