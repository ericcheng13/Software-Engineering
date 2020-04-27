package sudoku.test;

import org.junit.jupiter.api.Test;
import sudoku.SudokuTableCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class SudokuTableCellRendererTest {

    @Test
    void getTableCellRendererComponent() {
        SudokuTableCellRenderer stcr = new SudokuTableCellRenderer();
        Integer[][] grid = {{1},{2},{3}};

        stcr.getTableCellRendererComponent(new JTable(new DefaultTableModel(grid , new Integer[]{1, 2, 3})), Integer.valueOf(1), true, true, 0,0);

    }
}