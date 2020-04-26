package sudoku;

public class CreateGrid {

  private int[][] grid = new int[9][9];

  public int[][] newGrid(){
    fillValues();
    return this.grid;
  }

  public int[][] getGrid(){
    return this.grid;
  }

  public static int randomizer(int num) {
    return (int) Math.floor((Math.random()*num+1));
  }
  // Fills in all the values of the 9x9 grid
  public void fillValues() {
    fillDiagonal();
    fillRemaining(0, 3);
  }

  // Goes through and fills in the diagonal blocks first
  private void fillDiagonal() {
    for (int i = 0; i<9; i=i+3)
      fillBlock(i, i);
  }

  // Fills in a block, the input is the top left position the 3x3 block starts at.
  private void fillBlock(int row,int col) {
    int num;
    for (int i=0; i<3; i++) {
      for (int j=0; j<3; j++) {
        do {
          num = randomizer(9);
        }
        while (!blockCheck(row, col, num));
        grid[row+i][col+j] = num;
      }
    }
  }

  // Check if safe to put in cell
  private boolean check(int i,int j,int num) {
    return (rowCheck(i, num) &&
        colCheck(j, num) &&
        blockCheck(i-i%3, j-j%3, num));
  }

  // check in the row for existence
  private boolean rowCheck(int i,int num) {
    for (int j = 0; j<9; j++)
      if (grid[i][j] == num)
        return false;
    return true;
  }

  // check in the row for existence
  private boolean colCheck(int j,int num) {
    for (int i = 0; i<9; i++)
      if (grid[i][j] == num)
        return false;
    return true;
  }

  // Returns false if given 3 x 3 block contains num.
  private boolean blockCheck(int rowStart, int colStart, int num) {
    for (int i = 0; i<3; i++)
      for (int j = 0; j<3; j++)
        if (grid[rowStart+i][colStart+j]==num)
          return false;

    return true;
  }

  // Fills in the remaining empty spots.
  private boolean fillRemaining(int i, int j) {
    //  System.out.println(i+" "+j);
    if (j>=9 && i<9-1) {
      i = i + 1;
      j = 0;
    }
    if (i>=9 && j>=9)
      return true;

    if (i < 3) {
      if (j < 3)
        j = 3;
    }
    else if (i < 6) {
      if (j==(int)(i/3)*3)
        j =  j + 3;
    }
    else {
      if (j == 6) {
        i = i + 1;
        j = 0;
        if (i>=9)
          return true;
      }
    }

    for (int num = 1; num<=9; num++) {
      if (check(i, j, num)) {
        grid[i][j] = num;
        if (fillRemaining(i, j+1))
          return true;
        grid[i][j] = 0;
      }
    }
    return false;
  }

  public String printMat(){
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(grid[i][j]);
      }
      sb.append(" \n");
    }
    return sb.toString();
  }

  public static void main(String[] args){
    CreateGrid t = new CreateGrid();
    t.newGrid();
  }
}
