**Name:** Mira Shanouda &nbsp; &nbsp; **SID:** 900193016 &nbsp; &nbsp; **Section:** 5:30 PM
<br>
<br>
<br>
**Assumptions:**
  1. User input the customers type by num: 1 if reg and 2 if VIP which will be considered as prioirity.
  2. Both the customer's arrival and the customer number in the queue and how many people ahead of him/her are considered the same as the index of it in the M customers.
  3. Time duration of each teller handeling a customer is 2-7 seconds.
  4. The producer (ManageQueue) doesn't sleep and imediately input a customer in the queue if there is a space.
  5. Customers priority is handled in the prioirty queue where VIP cusotmers (prioirty 2) are put first in the queue and if the same prioirty, the first to arrive is considered.
  6. ManageQueue is the producer and teller is the consumer

**Design:**

1. SharedQueue class for the shared buffer (Priority Queue)
    1. Argumets:
        1. Priority Queue of type Customer
        2. ArrayList of type Customer to store all M customers
        3. maxsize = N
    2. Methods: 
        1. synchronized addToQueue() to add from the ArrayList of all customers to the Priority Queue **used by ManageQueue class**
        2. synchronized takeFromQueue() to remove customers form the Priority Queue **used by Teeler class**
        3. synchronized continueWork() to check if there are still some customers to add to the prioirty queue.
2. ManageQueue (Producer): calls addToQueue.
3. Teller (Consumer): 
    1. calls takeFromQueue.
    2. Sleeps for2-7 seconds to simultate a customer handeling. 
4. Customer: class to handel the cutomer number and prioirity.
5. ComparatorQueue: 
    1. Implemetnts a Comparator interface.
    2. Used to sort cutomers in the prioirty queue based on their prioirty (VIP/Regular)
  
