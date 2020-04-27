package sudoku;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class GameFrame {
    /**
     * creates frame for game itself
     * includes sudoku grid, buttons for Hint, Verify, Undo, Clear, and New Game
     */
    private final int WINDOW_WIDTH = 300;
    private final int WINDOW_HEIGHT = 450;
    private static JFrame f;
    private static JLabel l,lh,lb,br;
    private static JButton hint, undoBtn, clear, newGame, solution;
    private static PlayableGridCreator pgc;
    private static Integer[][] answerGrid;
    private static Integer[][] playGrid;
    private static ActionListener newGameAL;
    private static JTable table;
    private static JPanel finalPanel;
    private int emptyCellCount = 0;
    private UndoManager undoManager;




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

        //create all objects
        createTable();
        createClearButton();
        createHintButton();
        createNewGameButton();
        createSolutionButton();
        createLabels();
        createUndoButton();

        createPanel();

        //create window
        f.add(finalPanel);
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

    private void createPanel(){
        this.finalPanel = new JPanel();
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        //add items
        finalPanel.add(l);
        finalPanel.add(lh);
        finalPanel.add(br);
        finalPanel.add(table);
        finalPanel.add(lb);

        buttonPanel.add(hint);
        buttonPanel.add(undoBtn);
        buttonPanel.add(clear);
        buttonPanel.add(newGame);
        finalPanel.add(buttonPanel);
        finalPanel.add(solution);
    }

    private void createTable(){
        table = new JTable();
        table.setModel(new SudokuTableModel(playGrid,pgc));
        table.setDefaultRenderer(Integer.class, new SudokuTableCellRenderer());
        table.setCellSelectionEnabled(true);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(30);
        table.doLayout();
        table.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 25));
    }

    private void createHintButton(){
        hint = new JButton("Hint");
        countEmptyCellCount();
        ActionListener hintAL = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(emptyCellCount > 0) {
                    ((SudokuTableModel) table.getModel()).fillValue();
                    emptyCellCount= emptyCellCount - 2;
                    lh.setText("You have " + emptyCellCount/2 + " hints.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "You ran out of Hints" );
                }
            }
        };

        hint.addActionListener(hintAL);

    }

    private void createUndoButton(){
        undoBtn = new JButton("Undo");
        Action undoAction = new undoAction();
        undoBtn.addActionListener(undoAction);
        undoManager = new UndoManager();
        SudokuTableModel.getUndoSupport().addUndoableEditListener(new UndoAdaptor());

    }

    private void createClearButton(){
        clear = new JButton("Clear");
        ActionListener clearAL = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ((SudokuTableModel)table.getModel()).clearTable();
            }
        };
        clear.addActionListener(clearAL);

    }

    private void createSolutionButton(){
        solution = new JButton("Solution");
        solution.setAlignmentX(Component.CENTER_ALIGNMENT);
        ButtonModel solModel = solution.getModel();
        solModel.addChangeListener(new ChangeListener() {
            private boolean needChange = false;
            @Override
            public void stateChanged(ChangeEvent e) {
                if(solModel.isPressed()){
                    ((SudokuTableModel) table.getModel()).fillAll();
                    needChange = true;
                }
                else if (!solModel.isPressed() && needChange){
                    ((SudokuTableModel)table.getModel()).clearTable();
                    needChange = false;
                }

            }
        });
    }

    private void createNewGameButton(){
        newGame = new JButton("New Game");
        newGame.addActionListener(newGameAL);
    }

    private void createLabels(){
        lh = new JLabel("You have " + emptyCellCount/2 + " hints.");
        lh.setAlignmentX(Component.LEFT_ALIGNMENT);
        Font hintFont = new Font(lh.getFont().getFontName(), Font.ITALIC,10);
        lh.setFont(hintFont);
        //title label
        l = new JLabel("Sudoku Unlimited");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font titleFont = new Font(l.getFont().getFontName(), Font.BOLD,30);
        l.setFont(titleFont);
        //empty labels for spacing
        lb = new JLabel(" ");
        br = new JLabel(" ");
    }

    private void countEmptyCellCount(){
        for(int i = 0; i < playGrid.length; i++){
            for(int j = 0; j < playGrid[0].length; j++){
                if(playGrid[i][j] == null) {
                    emptyCellCount++;
                }
            }
        }
    }

    public void setVisible(boolean visible){
        f.setVisible(visible);
    }

    public Integer[][] TESTMakeIntegerGrid(int[][] input){
        return makeIntegerGrid(input);
    }

    private class UndoAdaptor implements UndoableEditListener {
        public void undoableEditHappened (UndoableEditEvent evt) {
            UndoableEdit edit = evt.getEdit();
            undoManager.addEdit(edit);
            refreshUndo();
        }
    }

    private class undoAction extends AbstractAction {
        public void actionPerformed(ActionEvent evt ) {
            undoManager.undo();
            refreshUndo();
        }
    }

    private void refreshUndo() {
        undoBtn.setText(undoManager.getUndoPresentationName());
        undoBtn.setEnabled(undoManager.canUndo());
    }
}
