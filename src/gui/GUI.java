package gui;

import core.Board;
import core.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public static void main(String[] args) {
        // Launching new game
        NewGame ng = new NewGame(null, 8, 8, 10);
        ng.actionPerformed(null);
    }

    public static final int SQUARE_SIZE = 30;
    private static final int MENU_SIZE = 42;

    private Sprite sprites = new Sprite();

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
    // Difficulty Menu
    private JMenu jMenuDifficulty;
    private JMenuItem jDifficultyEasy;
    private JMenuItem jDifficultyMedium;
    private JMenuItem jDifficultyHard;

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
        jGameNew.setAccelerator(KeyStroke.getKeyStroke("control N"));
        jGameNew.addActionListener(new NewGame(this, boardWidth, boardHeight, board.getNbMines()));

        // quit
        jGameQuit = new JMenuItem("Quit");
        jGameQuit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        jGameQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // DIFFICULTY MENU
        jMenuDifficulty = new JMenu("Difficulty");

        // Easy
        jDifficultyEasy = new JMenuItem("Easy");
        jDifficultyEasy.addActionListener(new NewGame(this, 8, 8, 10));

        // Medium
        jDifficultyMedium = new JMenuItem("Medium");
        jDifficultyMedium.addActionListener(new NewGame(this, 16, 16, 40));

        // Hard
        jDifficultyHard = new JMenuItem("Hard");
        jDifficultyHard.addActionListener(new NewGame(this, 30, 16, 99));

        jMenuGame.add(jGameNew);
        jMenuGame.add(jGameQuit);
        jMenuBar.add(jMenuGame);

        jMenuDifficulty.add(jDifficultyEasy);
        jMenuDifficulty.add(jDifficultyMedium);
        jMenuDifficulty.add(jDifficultyHard);
        jMenuBar.add(jMenuDifficulty);

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
                jSquares[x][y].setIcon(Sprite.unrevealed);

                jSquares[x][y].setBorder(BorderFactory.createEmptyBorder());
                jSquares[x][y].setContentAreaFilled(false);

                jSquares[x][y].addMouseListener(new Play(this, x, y));

                jBoard.add(jSquares[x][y], y, x);
            }
        }

        getContentPane().add(jBoard, BorderLayout.CENTER);
    }

    public void updateBoard() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Square square = board.getSquare(x, y);

                if (square.isMarked()) {
                    if (!board.isGameOver()) jSquares[x][y].setIcon(sprites.getFlag());
                    else jSquares[x][y].setIcon(sprites.getFlag(square.isMine()));
                }
                else if (square.isMine() && (square.isRevealed() || board.isGameOver())) {
                    if (board.isGameOver() && (square.isRevealed() || square.isMarked())) {
                        jSquares[x][y].setIcon(sprites.getMine(square.isRevealed()));
                    }
                    else jSquares[x][y].setIcon(sprites.getMine());
                }
                else if (square.isRevealed()) {
                    jSquares[x][y].setIcon(sprites.getNumber(square.getNbMinesAround()));
                }
                else {
                    jSquares[x][y].setIcon(Sprite.unrevealed);
                }
            }
        }
    }
}
