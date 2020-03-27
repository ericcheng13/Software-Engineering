package sudoku;

import javax.swing.table.DefaultTableModel;

class SudokuTable extends DefaultTableModel {
    Integer[][] originalTable;
    Integer[][] answerTable;
    SudokuTable(Integer[][] playGrid, Integer[][] answerTable ){
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

}
