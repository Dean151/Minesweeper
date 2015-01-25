package core;

import gui.GUI;

/**
 * Created by thomas on 25/01/15.
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board(8, 8, 10);
        GUI gui = new GUI(board);
    }
}
