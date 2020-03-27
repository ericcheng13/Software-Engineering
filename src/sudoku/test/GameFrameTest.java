package sudoku.test;


import org.junit.jupiter.api.Test;
import sudoku.Difficulty;
import sudoku.Display;
import sudoku.GameFrame;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GameFrameTest {

    @Test
    void setVisible() {
        Display dis = new Display();
        GameFrame gf = new GameFrame(Difficulty.EASY, dis.newGameActionListener());
        GameFrame gf2 = new GameFrame(Difficulty.MEDIUM, dis.newGameActionListener());
        GameFrame gf3 = new GameFrame(Difficulty.HARD, dis.newGameActionListener());

        gf.setVisible(false);
    }

    @Test
    void makeIntegerGrid() {
        Display dis = new Display();
        GameFrame gf = new GameFrame(Difficulty.EASY, dis.newGameActionListener());
        int[][] grid = {{1,2,3,1,2,3,1,2,3},{3,4,6,1,2,3,1,2,3},{5,6,7,1,2,3,1,2,3}
                ,{5,6,7,1,2,3,1,2,3},{5,6,7,1,2,3,1,2,3},{5,6,7,1,2,3,1,2,3}
                ,{5,6,7,1,2,3,1,2,3},{5,6,7,1,2,3,1,2,3},{5,6,7,1,2,3,1,2,3}};
        assertEquals(gf.TESTMakeIntegerGrid(grid)[0][0], Integer.valueOf(1));
    }
}