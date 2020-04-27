package sudoku.test;

import org.junit.jupiter.api.Test;
import sudoku.PlayableGridCreator;
import sudoku.SudokuTableCellRenderer;
import sudoku.SudokuTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class SudokuTableCellRendererTest {

    @Test
    void getTableCellRendererComponent() {
        SudokuTableCellRenderer stcr = new SudokuTableCellRenderer();
        PlayableGridCreator pgc = new PlayableGridCreator();
        int[][] grid = pgc.easy();

        //stcr.getTableCellRendererComponent(new JTable(new SudokuTableModel(grid , pgc)), Integer.valueOf(1), true, true, 0,0);

    }
}