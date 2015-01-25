package gui;

import core.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 25/01/15.
 */
public class Play implements ActionListener {
    private final Board board;
    private final int x;
    private final int y;

    public Play(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO play move
    }
}
