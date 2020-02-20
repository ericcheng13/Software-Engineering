package sudoku;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    /**
     * creates frame for game itself
     * includes sudoku grid, buttons for Hint, Verify, Undo, Clear, and New Game
     */
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;


    @Override
    public void paint(Graphics g) {

    }

    @Override
    public int getHeight() {
        return WINDOW_HEIGHT;
    }

    @Override
    public int getWidth() {
        return WINDOW_WIDTH;
    }

}
