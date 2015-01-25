package gui;

import core.Board;
import core.Square;
import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thomas on 25/01/15.
 */
public class GUI extends JFrame {
    private static final int SQUARE_SIZE = 30;
    private static final int MENU_SIZE = 42;

    private int height;
    private int width;

    private int boardHeight;
    private int boardWidth;

    private Board board;

    // Menu
    private JMenuBar jMenuBar;
    // Game Menu
    private JMenu jMenuGame;
    private JMenuItem jGameNew;
    private JMenuItem jGameQuit;

    // Board
    private JPanel jBoard;
    private JButton[][] jSquares;

    public GUI(Board board) {
        this.board = board;
        this.boardHeight = board.getHeight();
        this.boardWidth = board.getWidth();

        this.height = SQUARE_SIZE * this.boardHeight + MENU_SIZE;
        this.width = SQUARE_SIZE * this.boardWidth;

        initMenus();

        initWindow();

        initBoard();

        pack();
    }

    /**
     * Board getter
     * @return board
     */
    public Board getBoard() {
        return board;
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

    /**
     * initialize menus
     */
    private void initMenus() {
        jMenuBar = new JMenuBar();

        // GAME MENU
        jMenuGame = new JMenu("Game");

        // new game
        jGameNew = new JMenuItem("New game");
        jGameNew.setAccelerator(KeyStroke.getKeyStroke("Control N"));
        jGameNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO open new game
            }
        });

        // quit
        jGameQuit = new JMenuItem("Quit");
        jGameQuit.setAccelerator(KeyStroke.getKeyStroke("Control Q"));
        jGameQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenuGame.add(jGameNew);
        jMenuGame.add(jGameQuit);
        jMenuBar.add(jMenuGame);

        setJMenuBar(jMenuBar);
    }

    private void initBoard() {
        // Initializing board
        jBoard = new JPanel(new GridLayout(boardHeight, boardWidth));
        jSquares = new JButton[boardWidth][boardHeight];

        // Initializing buttons
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                jSquares[x][y] = new JButton();
                jSquares[x][y].setEnabled(true);
                jSquares[x][y].setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));

                jSquares[x][y].addActionListener(new Play(this, x, y));

                jBoard.add(jSquares[x][y], y, x);
            }
        }

        getContentPane().add(jBoard, BorderLayout.CENTER);
    }

    public void updateBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Square square = board.getSquare(x, y);

                if (square.isMine() && (square.isRevealed() || board.hasLost())) {
                    jSquares[x][y].setText("*");
                }
                else if (square.isMarked()) {
                    jSquares[x][y].setText("P");
                }
                else if (square.isRevealed()) {
                    if (square.getNbMinesAround() > 0) jSquares[x][y].setText(String.valueOf(square.getNbMinesAround()));
                }
            }
        }
    }
}
