package sudoku.test;

import org.junit.jupiter.api.*;
import sudoku.PlayableGridCreator;
import sudoku.SudokuTableModel;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTableModelTest {
    SudokuTableModel stm;
    int[][] originalGrid;
    int[][] answerGrid;

    private Integer[][] makeIntegerGrid(int[][] input){
        Integer[][] returnGrid = new Integer[9][9];
        for(int i = 0; i<9;i++){
            for(int j = 0; j<9;j++){
                returnGrid[i][j] = (input[i][j] == 0 )? null : input[i][j];
            }
        }
        return returnGrid;
    }



    @BeforeEach
    void setVariable(){
        PlayableGridCreator pgc = new PlayableGridCreator();
        originalGrid = pgc.hard();
        answerGrid = pgc.getMat();
        stm = new SudokuTableModel(makeIntegerGrid(originalGrid), makeIntegerGrid(answerGrid));
    }
    private int[] findEditableCell(){
        for(int i = 0; i <0; i++){
            for(int j = 0; j<9;j++)
                if(originalGrid[i][j]==0)
                    return new int[] {i,j};
        }
        return null;
    }
    @Test
    void isCellEditable() {
        if(stm.getValueAt(0,0)==null){
            assertTrue(stm.isCellEditable(0,0));
        }
        else assertFalse(stm.isCellEditable(0,0));
    }

    @Test
    void getColumnClass() {
        assertEquals(Integer.class, stm.getColumnClass(0));
    }

    @Test
    void setValueAt() {
        stm.setValueAt(null,0,0);
        stm.setValueAt("int",0,0);
        stm.setValueAt(Integer.valueOf("5"),0,0);
        stm.setValueAt(Integer.valueOf(-5),0,0);
        stm.setValueAt(Integer.valueOf(55),0,0);

    }
}