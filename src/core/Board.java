package core;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by thomas on 25/01/15.
 */
public class Board {
    private final int HEIGHT;
    private final int WIDTH;
    private final int NB_MINES;

    private ArrayList<Square> board;

    public Board(int width, int height, int mines) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.NB_MINES = mines;

        // Just in case...
        assert(NB_MINES < HEIGHT*WIDTH);

        this.board = new ArrayList<Square>();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                this.board.add(new Square(this, x, y));
            }
        }

        initMines();
    }

    /**
     * Initialize board with mines.
     */
    private void initMines() {
        // TODO init mines on first move to prevent losing

        ArrayList<Square> possibilities = new ArrayList<Square>();
        for (Square square : board) {
            possibilities.add(square); // Every square is a potential mine
        }

        for (int i = 0; i < NB_MINES; i++) {
            int index = getRandomNumber(0, possibilities.size() - 1); // We get a random in the possibilities
            Square mine = board.get(index); // We get the corresponding square
            mine.setMine(); // We set it as a mine
            possibilities.remove(index); // Then this square is out of the possibilities for next mine assignment
        }
    }

    /**
     * Allow to check if coordinates are inside the board
     * @param x x coordinate
     * @param y y coordinate
     * @return true if coordinates are inside the board, false otherwise
     */
    public boolean isInBoard(int x, int y) {
        return (x >= 0 && y >= 0 && x < WIDTH && y < WIDTH);
    }

    public int getIndex(int x, int y) {
        return  y * WIDTH + x;
    }

    /**
     * Allow to access a square at the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return the square at the following coordinates, null if coordinates are out of the board
     */
    public Square getSquare(int x, int y) {
        if (isInBoard(x, y)) {
            int index = getIndex(x, y);
            return board.get(index);
        } else {
            return null;
        }
    }

    /**
     * Allow to get all neighbors of a given square
     * @param square the square that is the center of the neighbors we want
     * @return List of neighbor squares
     */
    public ArrayList<Square> getNeighbors(Square square) {
        ArrayList<Square> neighbors = new ArrayList<Square>();

        int x = square.getX();
        int y = square.getY();

        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        assert(dx.length == dy.length);

        for (int i = 0; i < dx.length; i++) {
            Square adj = getSquare(x+dx[i], y+dy[i]);
            if (adj != null) neighbors.add(adj);
        }

        return neighbors;
    }

    /**
     * Allow to play at the [x,y] coordinates
     * @param x x coord
     * @param y y coord
     * @return true if the move is applied, false otherwise
     */
    public boolean play(int x, int y) {
        Square square = getSquare(x, y);
        if (square != null) {
            if (!square.isMarked()) { // Marked square are protected
                square.setRevealed();
                return true;
            }
        }

        return false;
    }

    public void mark(int x, int y) {
        Square square = getSquare(x, y);
        if (square != null) {
            square.setMarked();
        }
    }

    /**
     * Return a number between in the range [min, max] (included)
     * @param min minimum of the range
     * @param max maximum of the range
     * @return a random number in the range [min, max]
     */
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}