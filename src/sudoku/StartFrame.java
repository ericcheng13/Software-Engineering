package sudoku;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class StartFrame {

    static JFrame f;
    static JLabel l, l1;
    static JComboBox c1;
    static JButton b;
    static ActionListener al;
    private static Difficulty diff = Difficulty.EASY;

    static ItemListener il = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            diff = (Difficulty) cb.getSelectedItem();
        }
    };

    StartFrame(ActionListener startGameListener){
        this.al = startGameListener;
        createFrame();
    }
    private static void createFrame()
    {
        // create a new frame
        f = new JFrame("Sudoku Unlimited");
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        // set layout of frame
        f.setLayout(new FlowLayout());

        // create combobox
        c1 = new JComboBox(Difficulty.values());

        // add ItemListener
        c1.addItemListener(il);

        // create labels
        l = new JLabel("Select difficulty: ");
        l1 = new JLabel("");

        b = new JButton("Start Game!");
        b.addActionListener(al);

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
        f.setVisible(true);
    }

    Difficulty getDifficulty(){
        return diff;
    }

    void setVisible(boolean visible){
        f.setVisible(visible);
    }

}