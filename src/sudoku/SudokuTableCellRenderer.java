package sudoku;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class SudokuTableCellRenderer extends DefaultTableCellRenderer {
    SudokuTableCellRenderer(){
        super.setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);

        if (!table.isCellEditable(row,col)) {
            this.setBackground(Color.GRAY);
        }
        else {
            this.setBackground(Color.WHITE);
        }

        return this;
    }

}
