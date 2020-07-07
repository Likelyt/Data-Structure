
public class Sequence {
    private Grid grid;
    private Stack<Loc> path;// stores the sequence path
    private Stack<Loc> cor_path;
    private int[][] flag;
    private Loc pos;

    // constructor: create a new Sequence for the
    // specified grid
    public Sequence(Grid grid) {
        this.grid = grid;
        this.path = new Stack<Loc>();
        this.flag = new int[grid.size()][grid.size()];
        this.cor_path = new Stack<Loc>();
    }

    // resets the grid and the path
    public void reset(Grid grid) {
        this.grid = grid;
        this.path = new Stack<Loc>();
    }

    // (i, j) is the starting location
    // val is the value that should end the sequence
    public void getSeq(int i, int j, int val) throws EmptyStackException {
        // if(path.size() == 0){
        // throw new EmptyStackException();
        // }

        // To be implemented
        int found = 1;
        int n, v;
        Loc value;

        flag[i][j] = 1;
        v = grid.getLoc(i, j).val;
        n = grid.size();

        // stopping
        Loc temp = new Loc(i, j, v);
        if (v == val) {
            found = 0;
            path.push(temp);
            value = path.peek();
            path.pop();
            cor_path.push(value);
        }

        // main loop for getSeq
        while (found == 1) {
            Loc temp_new = new Loc(i, j, v);
            path.push(temp_new);

            boolean case_u = (i != 0 && grid.getLoc(i - 1, j).val == v + 1);
            boolean case_r = (j != n - 1 && grid.getLoc(i, j + 1).val == v + 1);
            boolean case_d = (i != n - 1 && grid.getLoc(i + 1, j).val == v + 1);
            boolean case_l = (j != 0 && grid.getLoc(i, j - 1).val == v + 1);

            if (i != 0 && flag[i - 1][j] == 0 && grid.getLoc(i - 1, j).val == v + 1) {
                getSeq(i - 1, j, val);
            } else if (j != n - 1 && flag[i][j + 1] == 0 && grid.getLoc(i, j + 1).val == v + 1) {
                getSeq(i, j + 1, val);
            } else if (i != n - 1 && flag[i + 1][j] == 0 && grid.getLoc(i + 1, j).val == v + 1) {
                getSeq(i + 1, j, val);
            } else if (j != 0 && flag[i][j - 1] == 0 && grid.getLoc(i, j - 1).val == v + 1) {
                getSeq(i, j - 1, val);

            } else if (!case_u && !case_r && !case_d && !case_l) {
                found = 0;
                path.pop();
            } else{
                value = path.peek();
                path.pop();
                cor_path.push(value);
                break;
            }
        }

    }

    // return a String representation of the sequence
    // starting at the starting location and listing
    // all locations in the sequence in order
    public String toString() {
        // To be implemented
        String arr = new String("");
        
        int num = cor_path.size();
        for (int i = 0; i < num; i++) {
            try {
            pos = cor_path.pop();
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
            arr = arr + pos.toString();
        }
        return arr;
    }
}