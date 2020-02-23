import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayableGridCreator {
  private int[][] playable;
  private int[][] mat;
  private int[] order;

  public PlayableGridCreator(){
    CreateGrid cg = new CreateGrid();
    mat = cg.newGrid();
    order = new int[80];
    order = generateOrder();
  }

  //Returns the complete grid, aka the solutions
  public int[][] getMat(){
    return this.mat;
  }

  /*
  Creates an easy playable grid. The input number represent the number of times it tries to remove pairs of values
  Later used in the hard method to help keep symmetry
   */

  public int[][] easy(int number) {
    playable = mat;
    for (int num = 0; num < number; num++){
      int position = order[num];
      int i = (position / 9);
      int j = position % 9;
      if (playable[i][j] != 0) {
        int currval1 = playable[i][j];
        int currval2 = playable[8-i][8-j];
        playable[i][j] = 0;
        playable[8-i][8-j] = 0;
        if (numSolutions(0, 0, playable, 0) != 1) {
          playable[i][j] = currval1;
          playable[8-i][8-j] = currval2;
        }
      }
    }
    return playable;
  }

  //Creates an easy playable grid. The number of removed values is set to 25
  public int[][] easy() {
    playable = mat;
    for (int num = 0; num < 25; num++){
      int position = order[num];
      int i = (position / 9);
      int j = position % 9;
      if (playable[i][j] != 0) {
        int currval1 = playable[i][j];
        int currval2 = playable[8-i][8-j];
        playable[i][j] = 0;
        playable[8-i][8-j] = 0;
        if (numSolutions(0, 0, playable, 0) != 1) {
          playable[i][j] = currval1;
          playable[8-i][8-j] = currval2;
        }
      }
    }
    return playable;
  }

  //Creates a playable grid with medium difficulty. Check 30 individual values and tries to remove them.
  public int[][] medium() {
    playable = mat;
    for (int num = 0; num < 30; num++){
      int position = order[num];
      int i = (position / 9);
      int j = position % 9;
      if (playable[i][j] != 0) {
        int currval = playable[i][j];
        playable[i][j] = 0;
        if (numSolutions(0, 0, playable, 0) != 1) {
          playable[i][j] = currval;
        }
      }
    }
    return playable;
  }
//Creates a hard playable grid. Check all spots and removes any value that keeps the number of solutions at 1.
  public int[][] hard() {
    playable = easy(10);
    for (int position: order) {
      int i = (position / 9);
      int j = position % 9;
      if (playable[i][j] != 0) {
        int currval = playable[i][j];
        playable[i][j] = 0;
        if (numSolutions(0, 0, playable, 0) != 1) {
          playable[i][j] = currval;
        }
      }
    }
    return playable;
  }

  //Creates a int[80] that contains randomly sorted integers from 0-80.
  int[] generateOrder(){
    Integer[] order = new Integer[80];
    for(int i = 0; i < 80; i++){
      order[i] = i;
    }
    List<Integer> intList = Arrays.asList(order);
    Collections.shuffle(intList);
    intList.toArray(order);
    int[] newOrder = new int[80];
    int i = 0;
    for(Integer value: order){
      newOrder[i++] = value;
    }
    return newOrder;
  }

//return 0 if there are no solutions, 1 if there is one solution, 2 if there are multiple solutions
   public int numSolutions(int i, int j, int[][] mat, int count) {
    if (i == 9) {
      i = 0;
      if (++j == 9)
        return 1+count;
    }
    if (mat[i][j] != 0)  // skip filled cells
      return numSolutions(i+1,j,mat, count);
    // search for 2 solutions instead of 1
    // break, if 2 solutions are found
    for (int val = 1; val <= 9 && count < 2; ++val) {
      if (islegal(i,j,val,mat)) {
        mat[i][j] = val;
        // add additional solutions
        count = numSolutions(i+1,j,mat, count);
      }
    }
    mat[i][j] = 0;
    return count;
  }

  private boolean islegal(int i, int j, int val, int[][] mat) {
    for (int d = 0; d < mat.length; d++) {
      if (mat[i][d] == val) {
        return false;
      }
    }
    for (int r = 0; r < mat.length; r++) {
      if (mat[r][j] == val) {
        return false;
      }
    }
    int sqrt = (int) Math.sqrt(mat.length);
    int boxRowStart = i - i % sqrt;
    int boxColStart = j - j % sqrt;
    for (int r = boxRowStart;
        r < boxRowStart + sqrt; r++) {
      for (int d = boxColStart;
          d < boxColStart + sqrt; d++) {
        if (mat[r][d] == val) {
          return false;
        }
      }
    }
    return true;
  }

  public String printMat(int [][] mat){
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(mat[i][j]);
      }
      sb.append(" \n");
    }
    return sb.toString();
  }

  public static void main(String[] args){
     CreateGrid cg = new CreateGrid();
     int[][] grid = cg.newGrid();
     PlayableGridCreator pgc = new PlayableGridCreator();
    System.out.println(pgc.printMat(pgc.getMat()));
     System.out.println(pgc.printMat(pgc.hard()));
     System.out.println(pgc.numSolutions(0,0,grid,0));
     }
}
