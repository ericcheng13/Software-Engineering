package sudoku;

import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import java.awt.Toolkit;

public class UndoAction extends AbstractAction {

    private UndoManager manage;

    public UndoAction(UndoManager manage){
        this.manage = manage;
    }

    public void actionPerformed (ActionEvent e){
        try {
            manage.undo();
        }
        catch (CannotUndoException exc){
            Toolkit.getDefaultToolkit().beep();
        }
    }
}