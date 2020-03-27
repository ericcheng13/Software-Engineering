package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                System.out.println("start new game!!");
                gameFrame = new GameFrame(startFrame.getDifficulty(), newGameActionListener());
                gameFrame.setVisible(true);
            }
        };

        return al;
    }

    ActionListener newGameActionListener(){
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
