package sudoku.test;

import org.junit.jupiter.api.Test;
import sudoku.Difficulty;
import sudoku.StartFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartFrameTest {
    StartFrame sf = new StartFrame(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    });
    @Test
    void getDifficulty() {

        assertEquals(sf.getDifficulty(), Difficulty.EASY);

    }

    @Test
    void setVisible() {
        sf.setVisible(false);
    }
}