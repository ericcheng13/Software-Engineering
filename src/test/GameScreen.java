package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameScreen extends JFrame {
    /**
     * creates frame for game itself
     * includes sudoku grid, buttons for Hint, Verify, Undo, Clear, and New Game
     */
    static JFrame f;
    static JLabel l,lb,br;
    static JButton hint, verify, undo, clear, newGame;


    public static void main(String[] args){
        f = new JFrame("Sudoku");
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });


        //create buttons
        hint = new JButton("Hint");
        verify = new JButton("Verify");
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        newGame = new JButton("New Game");

        //create table
        String[] colNames = {"1","2","3","4","5","6","7","8","9"};
        Object[][] sudoGrid = {{1,2,3,4,5,6,7,8,9},{2,3,4,5,6,7,8,9,1},{3,4,5,6,7,8,9,1,2},
                {4,5,6,7,8,9,1,2,3},{5,6,7,8,9,1,2,3,4},{6,7,8,9,1,2,3,4,5},
                {7,8,9,1,2,3,4,5,6},{8,9,1,2,3,4,5,6,7},{9,1,2,3,4,5,6,7,8}};

        JTable table = new JTable(sudoGrid, colNames);
        table.setCellSelectionEnabled(true);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(20);
        table.doLayout();
        //label
        l = new JLabel("Sudoku Unlimited");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font = new Font(l.getFont().getFontName(), Font.BOLD,20);
        l.setFont(font);
        lb = new JLabel(" ");
        br = new JLabel(" ");
        //create panels
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        //add items
        listPanel.add(l);
        listPanel.add(br);
        listPanel.add(table);
        listPanel.add(lb);

        buttonPanel.add(hint);
        buttonPanel.add(verify);
        buttonPanel.add(undo);
        buttonPanel.add(clear);
        buttonPanel.add(newGame);
        listPanel.add(buttonPanel);

        //create window
        f.add(listPanel);
        //f.setSize(300,250);
        f.pack();
        f.setVisible(true);

    }

}