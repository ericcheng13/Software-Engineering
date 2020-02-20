package sudoku;

import javax.swing.*;
import java.awt.event.*;

final class Display{

    private static JFrame frame;

    Display(){
        createFrame();
    }

    private void createFrame(){
        this.frame = new GameFrame();
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        //frame.setSize();
        frame.setVisible(true);

    }

    static void paint(){
        frame.paint(frame.getGraphics());

    }



}
