package sudoku;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import java.awt.*;
import java.awt.event.*;

public final class GameFrame {
    /**
     * creates frame for game itself
     * includes sudoku grid, buttons for Hint, Verify, Undo, Clear, and New Game
     */
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 420;
    private static JFrame f;
    private static JLabel l,lb,br;
    private static JButton hint, undo, clear, newGame;
    private static PlayableGridCreator pgc;
    private static Integer[][] answerGrid;
    private static Integer[][] playGrid;
    private static ActionListener newGameAL;
    private static JTable table;


    public GameFrame(Difficulty diff, ActionListener newGameAL){
        pgc = new PlayableGridCreator();
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
        answerGrid = makeIntegerGrid(pgc.mat);
        this.newGameAL = newGameAL;
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



        undo = new JButton("Undo");
        /*
        ActionListener undoAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SudokuTableModel table = new SudokuTableModel(playGrid, answerGrid);
                table.addUndoableEditListener(new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        undoManager.addEdit(e.getEdit());
                        try {
                            undoManager.undo();
                        } catch (CannotUndoException e) {
                            ;
                        }
                    }
                });
            }
        };*/


        newGame = new JButton("New Game");
        newGame.addActionListener(newGameAL);
        //create table
        table = new JTable();
        table.setModel(new SudokuTableModel(playGrid,pgc));
        table.setDefaultRenderer(Integer.class, new SudokuTableCellRenderer());
        table.setCellSelectionEnabled(true);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(30);
        table.doLayout();
        table.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 25));

        clear = new JButton("Clear");
        ActionListener clearAL = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ((SudokuTableModel)table.getModel()).clearTable();
            }
        };
        clear.addActionListener(clearAL);

        hint = new JButton("Hint");
        ActionListener hintAL = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ((SudokuTableModel)table.getModel()).fillValue();
            }
        };
        hint.addActionListener(hintAL);


        //title label
        l = new JLabel("Sudoku Unlimited");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font = new Font(l.getFont().getFontName(), Font.BOLD,30);
        l.setFont(font);
        //empty labels for spacing
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
        f.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        f.setVisible(false);

    }

    public static Integer[][] makeIntegerGrid(int[][] input){
        int rows = input.length;
        int cols = input[0].length;
        Integer[][] returnGrid = new Integer[rows][cols];
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<cols;j++){
                returnGrid[i][j] = (input[i][j] == 0 )? null : input[i][j];
            }
        }
        return returnGrid;
    }

    public void setVisible(boolean visible){
        f.setVisible(visible);
    }

    public Integer[][] TESTMakeIntegerGrid(int[][] input){
        return makeIntegerGrid(input);
    }
}
