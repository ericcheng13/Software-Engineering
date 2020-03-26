package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameFrame {
    /**
     * creates frame for game itself
     * includes sudoku grid, buttons for Hint, Verify, Undo, Clear, and New Game
     */
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;
    static JFrame f;
    static JLabel l,lb,br;
    static JButton hint, undo, clear, newGame;
    static PlayableGridCreator pgc = new PlayableGridCreator();
    private static Integer[][] answerGrid;
    private static Integer[][] playGrid;

    GameFrame(Difficulty diff){
        switch(diff){
            case EASY:
                playGrid = makeIntegerGrid(pgc.easy());
                break;
            case MEDIUM:
                playGrid = makeIntegerGrid(pgc.medium());
                break;
            case HARD:
                playGrid = makeIntegerGrid(pgc.hard());
                break;
        }
        answerGrid = makeIntegerGrid(pgc.getMat());
        createFrame();
    }

    private void createFrame(){
        f = new JFrame("Sudoku Unlimited");
        f.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });


        //create buttons
        hint = new JButton("Hint");
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        newGame = new JButton("New Game");

        //create table

        JTable table = new JTable();
        table.setModel(new SudokuTable(playGrid,answerGrid));

        table.setCellSelectionEnabled(true);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(20);
        table.doLayout();
        table.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 20));
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
        buttonPanel.add(undo);
        buttonPanel.add(clear);
        buttonPanel.add(newGame);
        listPanel.add(buttonPanel);
        //create window
        f.add(listPanel);
        f.setSize(275,325);
        f.setVisible(false);

    }

    private static Integer[][] makeIntegerGrid(int[][] input){
        Integer[][] returnGrid = new Integer[9][9];
        for(int i = 0; i<9;i++){
            for(int j = 0; j<9;j++){
                returnGrid[i][j] = (input[i][j] == 0 )? null : input[i][j];
            }
        }
        return returnGrid;
    }
    public int getHeight() {
        return WINDOW_HEIGHT;
    }

    public int getWidth() {
        return WINDOW_WIDTH;
    }

    public void setVisible(boolean visible){
        f.setVisible(visible);
    }
}
