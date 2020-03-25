package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class Display{
    GameFrame gameFrame;
    StartFrame startFrame;
    Display(){
        startFrame = new StartFrame(startGameListener(),comboBoxListener());
    }

    ActionListener startGameListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        return al;
    }

    ItemListener comboBoxListener(){
        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        };

        return  il;
    }

}
