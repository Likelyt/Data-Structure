import java.util.ArrayList;

public class PHashtable {
    private ArrayList<Patient>[] table;
    //private ArrayList<Patient[]> table;
    private int prime;
    private int count;

    //set the table size to the first 
    //prime number p >= capacity
    public PHashtable (int capacity) {
    //TO BE COMPLETED
        this.prime = getNextPrime(capacity);
        //this.table = new ArrayList[prime];
        //for (int i = 0; i < prime; i++) { 
        //    table[i] = new ArrayList<Patient>(); 
        // } 
        this.count = 0;
        this.table = new ArrayList[prime];
        //this.table = new ArrayList<Patient>(prime);
        //List<Patient> table = new ArrayList<>(prime);
        //this.table = (ArrayList[]) new Object[prime];
        //ArrayList<Patient>[] table = new ArrayList[prime];
        //ArrayList<Patient[]> table = new ArrayList<Patient[]>(prime);
        
        //ArrayList<ArrayList<Patient> > table =  new ArrayList<ArrayList<Patient> >(prime); 
        // master list (ArrayList of ArrayList) 
        //for (int i = 0; i < prime ; i++){
        //    ArrayList<Patient> a1= new ArrayList<Patient>();
        //    table.add(a1);
        //}
        
    }

    public int search(ArrayList<Patient> tempAL, String name){
        for(int i = 0; i < tempAL.size(); i++){
            if (tempAL.get(i).name() == name){
                return i;
            }
        }

        return -1;
    }
    //return the Patient with the given name 
    //or null if the Patient is not in the table
    public Patient get(String name) {
    //TO BE COMPLETED
        int key = name.hashCode()%prime;
        if (key < 0){
            key = key + prime;
        }

        int id;
        if (table[key] == null){
            return null;
        }else{
            id = search(table[key], name);
            if (id != -1){
                // found it
                return table[key].get(id);
            }else{
                // not found
                return null;
            } 
        }
       
    }

    //put Patient p into the table
    public void put(Patient p) {
        //ArrayList<Patient> tempArr;
        //TO BE COMPLETED
        int key;
        key = p.name().hashCode()%prime; 
        if (key < 0){
            key = key + prime;
        }

        // check whether this patient already exists in the table
        int id = -1;
  
        if(table[key] != null){
            id = search(table[key], p.name());
        }else if(table[key] ==  null){
            //ArrayList<Patient> tempArr;
            id = key;
            table[key] = new ArrayList<Patient>();
        }

        
        if (id == -1) {
            // not it, add it
            table[key].add(p);
            count = count + 1;
        }else{
            // not found, add it
            table[key].add(p);
            count = count + 1;
        }    
        //System.out.println(table);    
    }

    //remove and return the Patient with the given name
    //from the table
    //return null if Patient doesn't exist
    public Patient remove(String name) {
    //TO BE COMPLETED
        // transform name by hashcode to key
        int key = name.hashCode()%prime;
        if (key < 0){
            key = key + prime;
        }
        
        // check the name exist or not by id
        int id = -1;
        if (table[key] == null){
            return null;
        }else{
            id = search(table[key], name);
            if (id != -1){
                // found it, define it as pa
                Patient pa = table[key].get(id);
                table[key].remove(id);
                count = count -1;
                return pa;
            }else{
                // not found
                return null;
            }    
        }

    }	    

    //return the number of Patients in the table
    public int size() {
    //TO BE COMPLETED
        return count;
    }

    //returns the underlying structure for testing
    public ArrayList<Patient>[] getArray() {
	    return table;
    }
    
    //get the next prime number p >= num
    private int getNextPrime(int num) {
    if (num == 2 || num == 3)
        return num;
    int rem = num % 6;
    switch (rem) {
        case 0:
        case 4:
            num++;
            break;
        case 2:
            num += 3;
            break;
        case 3:
            num += 2;
            break;
    }
    while (!isPrime(num)) {
        if (num % 6 == 5) {
            num += 2;
        } else {
            num += 4;
           }
        }
        return num;
    }


    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if(num % 2 == 0){
            return false;
        }
        
	int x = 3;
	for(int i = x; i < num; i+=2){
	    if(num % i == 0){
		    return false;
        }
    }
	return true;
    }
}
      

