package core;

import java.util.ArrayList;

/**
 * Created by thomas on 25/01/15.
 */
public class Square {

    /**
     * Contain the parent board
     */
    private Board board;

    /**
     * x and y are the coordinates of the square
     */
    private int x, y;

    /**
     * Boolean that is true if this square is a mine, false otherwise
     */
    private boolean isMine;

    /**
     * Boolean that is true when the square is revealed by player
     */
    private boolean isRevealed;

    /**
     * Boolean that say if the square is marked by player as a mine
     */
    private boolean isMarked;

    /**
     * Number of mines around this square, should be between 0 and 8
     */
    private int nbMinesAround;

    public Square(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.isMine = false;
        this.isRevealed = false;
        this.isMarked = false;
        this.nbMinesAround = 0;
    }

    /**
     * X coordinate getter
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Y coordinate getter
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Call this function for this case to became a mine
     */
    public void makeItMine() {
        if (!isMine) {
            isMine = true;
            ArrayList<Square> adjacents = board.getNeighbors(this);
            for (Square adj : adjacents) {
                adj.addMineAround();
            }
        }
    }

    /**
     * Allow to increment the number of mines around. Called during mine assignment.
     */
    public void addMineAround() {
        nbMinesAround++;
    }
}
