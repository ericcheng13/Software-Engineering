package sudoku;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

class SudokuTableModel extends DefaultTableModel {
    Integer[][] originalTable;
    Integer[][] answerTable;

    SudokuTableModel(Integer[][] playGrid, Integer[][] answerTable ){
        super(playGrid, playGrid[0]);
        this.originalTable = playGrid;
        this.answerTable = answerTable;
        this.setValueAt(null, -1,-1);
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
        if(value != null && value.getClass()==Integer.class && ((Integer)value <=9 && (Integer)value >=1 )) {
            Vector<Object> rowVector = dataVector.elementAt(row);
            rowVector.setElementAt(value, col);

        }
        fireTableCellUpdated(row, col);
    }

}
