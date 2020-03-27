package sudoku.test;

import org.junit.jupiter.api.*;
import sudoku.Display;

import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DisplayTest {
    Display display;

    @BeforeEach
    void setVariable(){
        display = new Display();
    }
    @Test
    void startGameListener() {
        ActionListener al = display.startGameListener();
        al.actionPerformed(new ActionEvent(1, 1,"command"));
    }

    @Test
    void newGameActionListener() {
        ActionListener al = display.newGameActionListener();
        assertThrows(NullPointerException.class, () -> {
            al.actionPerformed(new ActionEvent(1, 1,"command"));
        });
        ActionListener al2 = display.startGameListener();
        al2.actionPerformed(new ActionEvent(1, 1,"command"));
        al.actionPerformed(new ActionEvent(1, 1,"command"));
    }
}