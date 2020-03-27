package sudoku;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class SudokuTableModel extends DefaultTableModel {
    Integer[][] originalTable;
    Integer[][] answerTable;

    public SudokuTableModel(Integer[][] playGrid, Integer[][] answerTable){
        super(playGrid, playGrid[0]);
        this.originalTable = playGrid;
        this.answerTable = answerTable;
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
    public void setValueAt(Object value, int row, int col){
        if((value==null)||( value != null && value.getClass()==Integer.class && ((Integer)value <=9 && (Integer)value >=1 ))) { //checks if value is within 1-9 range or empty
            Vector<Object> rowVector = dataVector.elementAt(row);
            rowVector.setElementAt(value, col);

        }
        fireTableCellUpdated(row, col);
    }
    
    public void clearTable() {
            for (int i = 0; i < this.originalTable.length; i++) {
                for (int j = 0; j < this.originalTable[0].length; j++){
                    if (this.getValueAt(i,j) != this.answerTable[i][j]){
                        this.setValueAt(null, i, j);
                    }
                }
            }
    }

    public void fillValue() {
        FORLOOPS:
        for (int i = 0; i < this.getColumnCount(); i++) {
            for (int j = 0; j < this.getRowCount(); j++){
                if (this.getValueAt(i,j) != null && this.getValueAt(i,j) != answerTable[i][j] && originalTable[i][j] == null){
                    this.setValueAt(answerTable[i][j], i, j);
                    break FORLOOPS;
                }
                else if (this.getValueAt(i,j) == null) {
                    this.setValueAt(answerTable[i][j], i, j);
                    break FORLOOPS;
                }
            }
        }
    }


}
