public class Queue<Item> {
    private Item[] queue;
    private int front, rear, count;
    
    @SuppressWarnings("unchecked")
    //constructor: create an empty Queue with initial
    //capacity of 1
    public Queue() {
    //To be implemented
        queue = (Item[]) new Object[1];
        count = 0;
        front = rear = 0;   
    }

    @SuppressWarnings("unchecked")
    //constructor: create an empty Queue with initial
    //capacity of n
    public Queue(int n) {
    //To be implemented
        //count = 0;
        //front = rear = 0;
        this.queue = (Item[]) new Object[n];
    }

    public void double_resize(int capacity, Item[] queue_temp){
        int i = 0;

        while(true){
            queue_temp[i] = queue[front];
            front++;
            if(front == capacity){
                front = 0;
            }
            if(i == capacity-1){
                break;
            }
            i++;
        }
        queue=queue_temp;
    }

    public void quarter_resize(int capacity, int low_capacity, Item[] queue_temp){
        // deep copy
        int i = 0;

        while(true){
            queue_temp[i] = queue[front];
            front++;
            if(front == capacity){
                front = 0;
            }
            if(i == low_capacity-2){
                break;
            }
            i++;
        }
        queue=queue_temp;
    } 

    //add an item to the back of the Queue
    //double the array capacity if the Queue is full
    public void enqueue(Item item) {
	//To be implemented
        int capacity;
        Item[] queue_temp;

        // queue length = capacity
        capacity = queue.length;
            
        if(capacity == count){
            // create new
            queue_temp = (Item[]) new Object[2 * capacity];

            // double resize it
            double_resize(capacity, queue_temp);

            // the current rear
            queue[capacity] = item;
            front = 0;
            rear = capacity;
            count = count+1;
        }else{
            // if the rear is at the end of the array
            if(rear+1 == capacity){
                rear = 0;
                queue[rear] = item;
                //rear++;
                count = count+1;
            }else{
            // if the rear is at the middle of the array
                queue[rear+1] = item;
                rear++;
                count = count+1;
            }   
        }
    }

    //remove and return the front item from the Queue
    //throw EmptyQueueException if the Queue is empty
    //reduce the array capacity by half if the size 
    //of the Queue falls below 1/4 full
    public Item dequeue() throws EmptyQueueException {
    //To be implemented

        int capacity, low_capacity;
        Item[] queue_temp;
        Item value;
        if(count == 0){
            front=0;
            rear=0;
            throw new EmptyQueueException();
        }

        capacity = queue.length;

        //delete 
        value = queue[front];
        queue[front] = null;
        
        // queue length = capacity
        low_capacity = queue.length/4;
        
        if(low_capacity == size()){
            front = front + 1;
            // store it
            queue_temp = (Item[]) new Object[low_capacity];
            quarter_resize(capacity, low_capacity, queue_temp);

            // the current rear
            front = 0;
            rear = low_capacity-2;
            count = count - 1;
        }else{
            count = count - 1;
            front = front + 1;
            if (front == capacity){
                front = 0;
            }
        }
        return value;
    }

    //return true if the Queue is empty
    //return false if the Queue is not empty
    public boolean isEmpty() {
	//To be implemented
        return (count == 0);
    }

    //return the size of the Queue (i.e. the 
    //number of elements currently in the Queue)
    public int size() {
	//To be implemented
        return count;
    }

    //return but do not remove the front item in the Queue
    //throw an exception if the Queue is empty
    public Item peek() throws EmptyQueueException {
    //To be implemented
        if(count == 0){
            throw new EmptyQueueException();
        }
        return queue[front];
    } 
    
    //return the underlying array
    public Item[] getArray() {
	    return queue;
    }
}