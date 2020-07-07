public class Stack<Item> {
    private int n;
    private Node first;
    private int count;
    
    //constructor: creates an empty Stack
    public Stack() {
	//To be implemented
        this.first = null;
        count = 0;
    }

    //adds item to the top of the Stack
    public void push(Item item) {
	//To be implemented
        //Allocate the new node to this item
        Node node = new Node();
        
        // assign item to the new node
        node.item = item;
        node.next = first;
        
        first = node; 
        count = count + 1;
    }

    //removes and returns the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item pop() throws EmptyStackException {
	    //To be implemented
        if (count==0){
            throw new EmptyStackException();
        }
        Item value = first.item; 
        first = (first).next;
        count = count - 1;
        return value;
    }
    
    //return true if the Stack is empty, false else
    public boolean isEmpty() {
	//To be implemented
        if (first == null){
            return true;
        }else{
            return false;
        }
    }

    //return the size (number of items) of the Stack
    public int size() {
	//To be implemented
        return count;
    }

    //return but do not remove the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item peek() throws EmptyStackException {
	//To be implemented
        if(count == 0){
            throw new EmptyStackException();
        }
        return first.item;
    }

    private class Node {
	Item item;
	Node next;
    }
}