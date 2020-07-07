public class NewPatientQueue {
    private Patient[] array;
    private PHashtable table;
    private int count, capacity;


    /*TO BE COMPLETED IN PART 1*/

    //constructor: set variables
    //capacity = initial capacity of array 
    public NewPatientQueue(int capacity) {
        //TO BE COMPLETED
        this.capacity = capacity;
        this.array = new Patient[capacity+1];
        this.table = new PHashtable(capacity);
        this.count = 1;
    }
    
    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
        //TO BE COMPLETED
            long diff;
            Patient temp;
            int pos = -1;
            
            // have a new patient coming in
            //count = count + 1;
            if (count == capacity + 1){
                return pos;
            }
    
            // first add the new patiet to the array
            // set posInQueue
            array[count] = p;
            array[count].setPosInQueue(count);
            
            pos = count;
            if (count != 1){
                // change the order of the array base max heap
                diff = array[pos/2].compareTo(array[pos]);
                while(diff < 0){
                    temp = array[pos];
                    array[pos] = array[pos/2];
                    array[pos/2] = temp;
                    // swap posInQueue
                    swapPos(array[pos/2], array[pos], pos);
                    pos = pos / 2;
                    
                    if(pos !=1){
                        diff = array[pos/2].compareTo(array[pos]);
                    }else if(pos == 1){
                        break;
                    }
                    
                }
            }
            count = count + 1;
            return pos;
        }   
    
    public void swapPos(Patient p_1, Patient p_2, int pos){
        int temp_pos_1, temp_pos_2;
        temp_pos_1 =  p_1.posInQueue();
        temp_pos_2 =  p_2.posInQueue();

        array[pos/2].setPosInQueue(temp_pos_2);
        array[pos].setPosInQueue(temp_pos_1);
    }


    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        //TO BE COMPLETED
        
        /*
        After delete, we need to set the posInQueue is -1 and others change.
        
        */
        if (count == 1){
            return null;
        }

        Patient p_del;
        p_del = array[1];
        p_del.setPosInQueue(-1);

        // replace the last item to the first item
        Patient temp;
        array[1] = array[count-1];
        array[count-1] = null;

        // re-arrange the posInQueue
        for(int i = 1; i < count-1; i++){
            array[i].setPosInQueue(i);
        }


        /* to arrange the array, remove the first item 
        and move the last item to index 1
        then call the sink function(2k, 2k+1),
        choose the smaller of the two, swap with that one.
        just need to check the unleaf nodes
        */
        int k = 1;
        int max_child_index = 1;
        
        
        // find the max children index, future swap with it
        if(k == 1){
            if (array[2*k] == null && array[2*k+1] == null){
                count = count - 1; 
                return p_del;
            }else if (array[2*k] != null && array[2*k+1] == null){
                max_child_index =  2*k;
            }else if (array[2*k] == null && array[2*k+1] != null){
                max_child_index =  2*k+1;
            }else{
                max_child_index =  find_Max_index(array[2*k], array[2*k+1], k);
            }
        }

        while ((array[k].compareTo(array[max_child_index]) < 0) && array[max_child_index] != null){            
            temp = array[max_child_index];
            array[max_child_index] = array[k];
            array[k] = temp;
            k = max_child_index;

            if(2*k < capacity+1){
                if(2*k+1 < capacity + 1){
                    if (array[2*k] == null && array[2*k+1] == null){
                        break;
                    }else if (array[2*k] != null && array[2*k+1] == null){
                        max_child_index =  2*k;
                    }else if (array[2*k] == null && array[2*k+1] != null){
                        max_child_index =  2*k+1;
                    }else{
                        max_child_index =  find_Max_index(array[2*k], array[2*k+1], k);
                    }
                }else if (2*k+1 == capacity + 1){
                    if (array[2*k] == null){
                        break;
                    }else if (array[2*k] != null ){
                        max_child_index =  2*k;
                    }
                }
            }else{
                break;
            }
        }
        count = count - 1; 
        return p_del;
    }

    // return the max index of the children, need to swap
    // if to children have the same time, return the time small
    public int find_Max_index(Patient p_1, Patient p_2, int k){
        int index;
        if (p_1.compareTo(p_2) > 0){
            index = 2*k;
        }else{
            index = 2*k+1;
        }

        return index;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        //TO BE COMPLETED
        if (count == 1){
            return null;
        }
        return array[1];
    }

    //return the number of patients currently in the queue
    public int size() {
        //TO BE COMPLETED
            return count-1;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        //TO BE COMPLETED
        return (count == 1);
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
	    return array;
    }

    /*TO BE COMPLETED IN PART 2*/

    //remove and return the Patient with
    //name s from the queue
    //return null if the Patient isn't in the queue
    public Patient remove(String s) {
        //TO BE COMPLETED
        

        
	    return null;
    }
    
    //update the emergency level of the Patient
    //with name s to urgency
    public void update(String s, int urgency) {
        //TO BE COMPLETED

        // the first task is to search the name in the hashtable.
        Patient person = table.get(s);

        // find the pos in the new queue
        int pos_in_queue = person.posInQueue();

        // set the emergencey
        int prev_urgency = array[pos_in_queue].urgency();
        array[pos_in_queue].setUrgency(urgency);


        // update the array order;
        // three scenarios 

        // 1. the new urgencey is equal to the current urgency
        // do nothing
        


        if(prev_urgency < urgency){
        // 2. if the new urgencey is larger the current urgence,
        // we need swap with its parent
        // To be completed
        // do something
        // parent id: pos_in_queue/2;
        // we need helper function to help it swim up.

        }else if(prev_urgency > urgency){
        // 3 if the new urgencey is smaller the current urgency,
        // we need to swap with its children.
        // To be completed
        // children id: pos_in_queue*2 , pos_in_queue*2+1;
        // we need helper function to help it sink down.

        }




    }
}
    