package sudoku;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class SudokuTableCellRenderer extends DefaultTableCellRenderer {
    SudokuTableCellRenderer(){
        super.setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
        int THICK = 2;
        int THIN = 0;
        this.setBorder(BorderFactory.createMatteBorder(0, 0, (row+1) % 3 == 0 ? THICK : THIN, (col+1) % 3 == 0 ? THICK : THIN, Color.black));
        if (!table.isCellEditable(row,col)) {
            this.setBackground(new Color(178,190,181));
        }
        else {
            this.setBackground(Color.WHITE);
        }

        return this;
    }

}
