package sudoku;

import javax.swing.table.DefaultTableModel;
import java.util.Random;
import java.util.Vector;

public class SudokuTableModel extends DefaultTableModel {
    private Integer[][] originalTable;
    private Integer[][] answerTable;
    private PlayableGridCreator pgc;

    public SudokuTableModel(Integer[][] playGrid, PlayableGridCreator pgc){
        super(playGrid, playGrid[0]);
        this.originalTable = playGrid;
        this.answerTable = GameFrame.makeIntegerGrid(pgc.getMat());
        this.pgc = pgc;

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
                corrected = true;
            }
        }
    }

    public boolean isValueValid(int row, int col){
        return (this.getValueAt(row,col)==null)||pgc.isLegal(row, col, ((Integer)this.getValueAt(row,col)).intValue(),dataVector);
    }


}
