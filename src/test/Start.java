package test;
// Java Program to create a simple JComboBox
// and add elements to it
import sudoku.Difficulty;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class Start extends JFrame implements ItemListener {

    // frame
    static JFrame f;

    // label
    static JLabel l, l1;

    // combobox
    static JComboBox c1;

    //button
    static JButton b;

    // main class
    public static void main(String[] args)
    {
        // create a new frame
        f = new JFrame("frame");
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        // create a object
        Start s = new Start();

        // set layout of frame
        f.setLayout(new FlowLayout());

        // create checkbox
        c1 = new JComboBox(Difficulty.values());

        // add ItemListener
        c1.addItemListener(s);

        // create labels
        l = new JLabel("Select difficulty: ");
        l1 = new JLabel("");

        b = new JButton("Start Game!");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // create a new panel
        JPanel p = new JPanel();
        GridLayout layout = new GridLayout(4,0);
        p.setLayout(layout);

        p.add(l);

        // add combobox to panel
        p.add(c1);
        p.add(l1);


        p.add(b);
        // add panel to frame

        layout.setVgap(5);
        f.add(p);
        f.setSize(200,200);
        f.pack();
        f.setVisible(true);
    }
    public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed

    }


}