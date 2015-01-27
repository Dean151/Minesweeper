package gui;

import core.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 27/01/15.
 */
public class NewGame implements ActionListener {

    private final GUI oldGui;

    private final int width;
    private final int height;
    private final int mines;

    public NewGame(GUI oldGui, int width, int height, int mines) {
        this.oldGui = oldGui;

        this.width = width;
        this.height = height;
        this.mines = mines;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board board = new Board(width, height, mines);
        GUI gui = new GUI(board);
        if (oldGui instanceof JFrame) oldGui.dispose();
    }
}
