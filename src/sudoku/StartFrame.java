package sudoku;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends  FrameBase{
    /**
     * creates start screen ie new game window
     * drop down to select difficulty and button for new game
     */
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;





    public void paint(Graphics g){

    }

    @Override
    public int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    @Override
    public int getWindowWidth() {
        return WINDOW_WIDTH;
    }
}
