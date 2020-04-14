package sudoku.test;

import org.junit.jupiter.api.Test;
import sudoku.CreateGrid;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;


public class CreateGridTest {

  @org.junit.jupiter.api.Test
  void randomizerTest() {
    CreateGrid cg = new CreateGrid();
    int i = cg.randomizer(10);
    boolean b = false;
     if(i > 0 || i <= 10){
       b = true;
    }
     assertEquals(true, b);
  }

  @org.junit.jupiter.api.Test
  void printMatTest() {
    CreateGrid cg = new CreateGrid();
    cg.newGrid();
    cg.printMat();
  }

  @org.junit.jupiter.api.Test
  void newGridTest() {
    CreateGrid cg = new CreateGrid();
    cg.newGrid();
    String s = cg.printMat();
    boolean b = true;
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == '0'){
        b = false;
      }
    }
    assertEquals(true, b);
  }


}
