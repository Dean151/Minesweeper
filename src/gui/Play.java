package gui;

import core.Board;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Play implements MouseListener {
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
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (board.play(x, y)) {
                gui.updateBoard();
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            board.mark(x, y);
            gui.updateBoard();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
