package sudoku;

import javax.swing.*;
import java.awt.*;

public abstract class FrameBase extends JFrame {
    public abstract int getWindowHeight();
    public abstract int getWindowWidth();
    public abstract void paint(Graphics g);
}
