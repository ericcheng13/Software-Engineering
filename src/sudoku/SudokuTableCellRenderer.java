package sudoku;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SudokuTableCellRenderer extends DefaultTableCellRenderer {
    private final int THICK = 1;
    private final int THIN = 0;
    public SudokuTableCellRenderer(){
        super.setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);

        this.setBorder(BorderFactory.createMatteBorder((row) % 3 == 0 ? THICK : THIN, (col) % 3 == 0 ? THICK : THIN, (row+1) % 3 == 0 ? THICK : THIN, (col+1) % 3 == 0 ? THICK : THIN, Color.black));
        if (!table.isCellEditable(row,col)) {
            this.setBackground(new Color(178,190,181));
        }
        else if(table.isCellSelected(row, col)){
            this.setBackground(new Color(201,255,229));
        }
        else {
            this.setBackground(Color.WHITE);
        }

        return this;
    }

}
