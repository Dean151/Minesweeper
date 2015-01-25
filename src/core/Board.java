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

    private void initMines() {
        ArrayList<Square> possibilities = new ArrayList<Square>();
        for (Square square : board) {
            possibilities.add(square); // Every square is a potential mine
        }

        for (int i = 0; i < NB_MINES; i++) {
            int index = getRandomNumber(0, possibilities.size() - 1); // We get a random in the possibilities
            Square mine = board.get(index); // We get the corresponding square
            mine.makeItMine(); // We set it as a mine
            possibilities.remove(index); // Then this square is out of the possibilities for next mine assignment
        }
    }

    public boolean isInBoard(int x, int y) {
        return (x >= 0 && y >= 0 && x < WIDTH && y < WIDTH);
    }

    public Square getSquare(int x, int y) {
        if (isInBoard(x, y)) {
            int index = y * WIDTH + x;
            return board.get(index);
        } else {
            return null;
        }
    }

    public ArrayList<Square> getAdjacents(Square square) {
        ArrayList<Square> adjacents = new ArrayList<Square>();

        int x = square.getX();
        int y = square.getY();

        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        assert(dx.length == dy.length);

        for (int i = 0; i < dx.length; i++) {
            Square adj = getSquare(x+dx[i], y+dy[i]);
            if (adj != null) adjacents.add(adj);
        }

        return adjacents;
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
