package sudoku;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SudokuTableCellRenderer extends DefaultTableCellRenderer {
    private final int THICK = 1;
    private final int THIN = 0;
    private final Color lightBlue=new Color(165,255, 245);
    private final Color lightGrey=new Color(193, 193, 193);
    private final Color lightRed = new Color(255, 193,193);
    //private final Color greyRed =  new Color(255, 103, 118);
    public SudokuTableCellRenderer(){
        super.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
        SudokuTableModel tableModel = (SudokuTableModel) table.getModel();
        //emphasizes 3 by 3 grid
        this.setBorder(BorderFactory.createMatteBorder((row) % 3 == 0 ? THICK : THIN, (col) % 3 == 0 ? THICK : THIN, (row+1) % 3 == 0 ? THICK : THIN, (col+1) % 3 == 0 ? THICK : THIN, Color.black));
        this.setHorizontalAlignment(CENTER);

        if (!table.isCellEditable(row,col)) {
            this.setBackground(lightGrey);
        }
        else if(table.isCellSelected(row, col)){
            this.setBackground(lightBlue);
        }
        else if (!tableModel.isValueValid(row, col)){
            this.setBackground(lightRed);
        }
        else {
            this.setBackground(Color.WHITE);
        }

        return this;
    }

}
