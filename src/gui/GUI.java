package gui;

import core.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thomas on 25/01/15.
 */
public class GUI extends JFrame {
    private static final int SQUARE_SIZE = 30;
    private static final int MENU_SIZE = 42;

    private int height;
    private int width;

    private Board board;

    // Menu
    private JMenuBar jMenuBar;

    // Game Menu
    private JMenu jMenuGame;
    private JMenuItem jGameNew;
    private JMenuItem jGameQuit;

    // About Menu
    private JMenu jMenuAbout;
    private JMenuItem jAboutInformations;

    // Board
    private JPanel jBoard;
    private JButton[][] jSquares;

    public GUI(Board board) {
        this.board = board;

        this.height = SQUARE_SIZE * board.getHeight() + MENU_SIZE;
        this.width = SQUARE_SIZE * board.getWidth();

        initWindow();

        pack();
    }

    /**
     * Initialize window
     */
    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper Java");
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        setVisible(true);
    }
}
