package sudoku;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.undo.*;

public class UndoableTableModel extends DefaultTableModel implements TableModelListener {
    private SudokuTableModel pointer;
    private UndoableEditSupport support = new UndoableEditSupport();

    public UndoableTableModel(SudokuTableModel pointer) {
        setPointer(pointer);
    }
    public void setPointer(SudokuTableModel pointer){
        if (this.pointer != null) {
            this.pointer.removeTableModelListener(this);
            this.pointer = pointer;
            this.pointer.addTableModelListener(this);
            fireTableStructureChanged();
        }
    }

    public Class<?> getColumnClass(int column) {
        return pointer.getColumnClass(column);
    }


    public boolean isCellEditable(int row, int column){
        return pointer.isCellEditable(row, column);
    }

    public void setValueAt(Object value, int row, int column){
        Object old = pointer.getValueAt(row, column);
        pointer.setValueAt(value, row, column);
        Object newV = pointer.getValueAt(row, column);
        fireChangeEdit(row, column, old, newV);
    }

    public void addUndoableEditListener(UndoableEditListener l){
        support.addUndoableEditListener(l);
    }

    public void removeUndoableEditListener(UndoableEditListener l){
        support.removeUndoableEditListener(l);
    }

    public void tableChanged(TableModelEvent e){
        TableModelEvent event = new TableModelEvent(this, e.getFirstRow(), e.getLastRow(), e.getColumn(), e.getType());
        fireTableChanged(event);
    }

    protected void fireChangeEdit(int row, int column, Object old, Object newV){
        UndoableEdit input = new TableChangeEdit(row, column, old, newV);
        support.beginUpdate();
        support.postEdit(input);
        support.endUpdate();
    }

    public boolean isValueValid(int row, int col){
        return pointer.isValueValid(row, col);
    }

    public void fillValue(){
        pointer.fillValue();
    }

    public void clearTable(){
        pointer.clearTable();
    }
    private class TableChangeEdit extends AbstractUndoableEdit {
        private int col;
        private int row;
        private Object old;
        private Object newV;

        public TableChangeEdit(int row, int col, Object old, Object newV) {
            this.row = row;
            this.col = col;
            this.old = old;
            this.newV = newV;
        }

        public void undo() {
            super.undo();
            pointer.setValueAt(old, row, col);
        }

        public void redo(){
            super.redo();
            pointer.setValueAt(newV, row, col);
        }
    }
}

