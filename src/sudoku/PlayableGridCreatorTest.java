package sudoku;

import sudoku.CreateGrid;
import sudoku.PlayableGridCreator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PlayableGridCreatorTest {

  @org.junit.jupiter.api.Test
  void isLegal() {

  }

  @org.junit.jupiter.api.Test
  void generateOrderTest() {
    Integer[] order = new Integer[80];
    for(int i = 0; i < 80; i++){
      order[i] = i;
    }
    PlayableGridCreator pgc = new PlayableGridCreator();
    int[] randomInt = pgc.generateOrder();
    Integer[] random = Arrays.stream(randomInt).boxed().toArray( Integer[]::new );
    List<Integer> randomList = Arrays.asList(random);
    Collections.sort(randomList);
    randomList.toArray(order);
    int[] newSort = new int[80];
    int i = 0;
    for(Integer value: order){
      newSort[i++] = value;
    }
    assertEquals(newSort[1],order[1]);
  }

  @org.junit.jupiter.api.Test
  void numOfSolutionsTest() {
    int[][] grid = new int[9][9];
    for(int i = 0; i < 9; i++){
      for(int j = 0; j < 9; j++){
        grid[i][j] = 0;
      }
    }
    int[][] multi = { {0,8,0,0,0,9,7,4,3}, {0,5,0,0,0,8,0,1,0}, {0,1,0,0,0,0,0,0,0}, {8,0,0,0,0,5,0,0,0}, {0,0,0,8,0,4,0,0,0},
        {0,0,0,3,0,0,0,0,6},{0,0,0,0,0,0,0,7,0},{0,3,0,5,0,0,0,8,0},{9,7,2,4,0,0,0,5,0} };
    int[][] hard = {{2,0,0,0,0,0,0,8,0}, {0,0,0,0,9,0,0,0,0}, {0,0,0,3,0,0,7,0,0},{0,0,0,8,0,0,0,0,7},
        {0,1,0,0,4,0,3,2,0},{0,0,9,0,6,0,0,0,1},{8,0,0,4,0,0,0,0,0}, {4,5,0,0,0,7,8,0,0},{0,0,3,0,0,0,0,9,2}};
    PlayableGridCreator pgc = new PlayableGridCreator();
    CreateGrid cg = new CreateGrid();
    int[][] complete = cg.newGrid();
    assertEquals(pgc.numSolutions(0,0,grid,0),2);
    assertEquals(pgc.numSolutions(0,0,multi,0),2);
    assertEquals(pgc.numSolutions(0,0,complete,0),1);
    assertEquals(pgc.numSolutions(0,0,hard,0),1);
  }
}