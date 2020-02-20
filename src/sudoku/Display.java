package sudoku;

import javax.swing.*;
import java.awt.event.*;

final class Display{

    private static JFrame frame;

    Display(){
        StartFrame startFrame = new StartFrame();
        createFrame(startFrame);
    }

    private void createFrame(JFrame frame){
        this.frame = frame;
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        frame.setSize(frame.getWidth(),frame.getHeight());
        frame.setVisible(true);

    }

    public static void paint(){
        frame.paint(frame.getGraphics());

    }



}
