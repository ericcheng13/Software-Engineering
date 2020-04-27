package sudoku;

import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEditSupport;
import java.util.Random;
import java.util.Vector;
import java.io.IOException;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SudokuTableModel extends DefaultTableModel {
    private Integer[][] originalTable;
    private Integer[][] answerTable;
    private PlayableGridCreator pgc;
    private static UndoableEditSupport undoSupport = new UndoableEditSupport();

    private Integer[][] undoMatrix = new Integer[9][9];
    private Integer[][] oldMatrix = new Integer[9][9];
    private LinkedList<Integer> rowList = new LinkedList();
    private LinkedList<Integer> columnList = new LinkedList();

    public void fillMatrix(Integer[][] mat){
        for (int i = 0; i <9; i++ ){
            for (int j = 0; i < 9; i++ ) {
                mat[i][j] = 0;
            }
        }
    }

    public SudokuTableModel(Integer[][] playGrid, PlayableGridCreator pgc){
        super(playGrid, playGrid[0]);
        this.originalTable = playGrid;
        this.pgc = pgc;
        this.answerTable = GameFrame.makeIntegerGrid(pgc.getMat());
        this.fillMatrix(undoMatrix);
        this.fillMatrix(oldMatrix);

    }

    public static UndoableEditSupport getUndoSupport(){
        return undoSupport;
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return originalTable[row][column] == null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if ((value == null) || (value != null && value.getClass() == Integer.class && ((Integer) value <= 9 && (Integer) value >= 1))) { //checks if value is within 1-9 range or empty
            Vector<Object> rowVector = dataVector.elementAt(row);
            rowVector.setElementAt(value, col);
                oldMatrix[row][col] = undoMatrix[row][col];
                undoMatrix[row][col] = (Integer)value;
            }

        fireTableCellUpdated(row, col);
        rowList.addFirst(row);
        columnList.addFirst(col);

    }
    
    public void clearTable() {
            for (int i = 0; i < this.originalTable.length; i++) {
                for (int j = 0; j < this.originalTable[0].length; j++){
                    if (this.getValueAt(i,j) != this.originalTable[i][j]){
                        this.setValueAt(null, i, j);
                    }
                }
            }
    }

    public void fillValue() {
        boolean corrected = false;
        CHANGE_VAL_LOOP:
        for (int i = 0; i < this.originalTable.length; i++) {
            for (int j = 0; j < this.originalTable[0].length; j++){
                Integer val = (Integer)this.getValueAt(i,j);
                if (val != null && !pgc.isCorrect(i,j,val)){
                    this.setValueAt(this.answerTable[i][j], i, j);
                    this.originalTable[i][j] = this.answerTable[i][j];
                    corrected = true;
                    break CHANGE_VAL_LOOP;
                }
            }
        }
        Random gen = new Random();
        while(!corrected){
            int rand = gen.nextInt(81);
            int row = Math.floorMod(rand,9);
            int col = Math.floorDiv(rand,9);
            if(isCellEditable(row,col) && this.getValueAt(row,col)==null){
                this.setValueAt(this.answerTable[row][col],row,col);
                this.originalTable[row][col] = this.answerTable[row][col];
                corrected = true;
            }
        }
    }

    public void fillAll() {
        for (int i = 0; i < this.originalTable.length; i++) {
            for (int j = 0; j < this.originalTable[0].length; j++) {
                this.setValueAt(this.answerTable[i][j],i,j);
            }
        }
    }
    public void undoValue() {
        setValueAt(oldMatrix[rowList.peek()][columnList.peek()], rowList.remove(), columnList.remove());
        rowList.remove();
        columnList.remove();
    }

    public boolean isValueValid(int row, int col) {
        return (this.getValueAt(row, col) == null) || pgc
            .isLegal(row, col, ((Integer) this.getValueAt(row, col)).intValue(), dataVector);
    }


}
