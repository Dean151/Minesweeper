package gui;

import core.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play implements ActionListener {
    private final GUI gui;
    private final Board board;
    private final int x;
    private final int y;

    public Play(GUI gui, int x, int y) {
        this.gui = gui;
        this.board = gui.getBoard();
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO play move
        if (board.play(x, y)) {
            gui.updateBoard();
        }
    }
}
