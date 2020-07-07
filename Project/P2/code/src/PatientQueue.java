public class PatientQueue {
    private Patient[] array;
    private int count, capacity;

    //constructor: set variables
    //capacity = initial capacity of array 
    public PatientQueue(int capacity) {
    //TO BE COMPLETED
    // the inital start should be 1 not 0
        this.capacity = capacity;
        //this.array = (Patient[]) new Object[capacity+1];
        this.array = new Patient[capacity+1];
        this.count = 1;
    }
    
    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
    //TO BE COMPLETED
        long diff, prio_diff, time_diff;
        Patient temp;
        int pos = -1;
        
        // have a new patient coming in
        //count = count + 1;
        if (count == capacity + 1){
            return pos;
        }


        // first add the new patiet to the array
        array[count] = p;
        pos = count;
        if (count != 1){
            // change the order of the array base max heap
            diff = array[pos/2].compareTo(array[pos]);
            while(diff < 0){
                temp = array[pos];
                array[pos] = array[pos/2];
                array[pos/2] = temp;
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

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
    //TO BE COMPLETED
        if (count == 1){
            return null;
        }

        Patient p_del;
        p_del = array[1];
        
        // replace the last item to the first item
        Patient temp;
        array[1] = array[count-1];
        array[count-1] = null;

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
}
    