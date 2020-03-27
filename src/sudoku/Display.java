package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Display{
    GameFrame gameFrame;
    StartFrame startFrame;
    public Display(){
        startFrame = new StartFrame(startGameListener());
    }

    public ActionListener startGameListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                gameFrame = new GameFrame(startFrame.getDifficulty(), newGameActionListener());
                gameFrame.setVisible(true);
            }
        };

        return al;
    }

    public ActionListener newGameActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        };

        return al;
    }

}
