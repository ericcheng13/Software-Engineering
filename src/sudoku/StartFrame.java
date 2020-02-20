package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {
    /**
     * creates start screen ie new game window
     * drop down to select difficulty and button for new game
     */
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;
    JLabel diffLabel;

    public StartFrame(){
        this.setLayout(new FlowLayout());
        diffLabel = new JLabel("Select difficulty: ");
        this.add(diffLabel);
        JComponent dropdown = new DiffDropDown(this);
        dropdown.setOpaque(true);
        this.add(dropdown);

    }



    public void paint(Graphics g){


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println(action);

    }

    @Override
    public int getHeight() {
        return WINDOW_HEIGHT;
    }

    @Override
    public int getWidth() {
        return WINDOW_WIDTH;
    }

    class DiffDropDown extends JComponent{
        DiffDropDown(ActionListener al){
            String[] dif = {"1","2","3"};
            JComboBox diffList = new JComboBox(dif);
            diffList.addActionListener(al);
        }
    }


}
