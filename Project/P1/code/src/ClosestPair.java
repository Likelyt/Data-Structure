public class ClosestPair {
    private Grid grid;
    private Queue<Loc> queue;

    //constructor: create a new ClosestPair object for grid
    public ClosestPair(Grid grid) {
	this.grid = grid;
	this.queue = new Queue<Loc>();
    }

    //search for the closest location with a value 
    //that is equal to the value at start
    //return null if no match is found
    public Loc search(int x, int y) {
	//To be implemented
    }
}
	