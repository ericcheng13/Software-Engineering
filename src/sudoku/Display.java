package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class Display{
    GameFrame gameFrame;
    StartFrame startFrame;
    Display(){
        startFrame = new StartFrame(startGameListener());
    }

    ActionListener startGameListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                gameFrame = new GameFrame(startFrame.getDifficulty());
                gameFrame.setVisible(true);
            }
        };

        return al;
    }

}
