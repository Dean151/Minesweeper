package core;

import gui.GUI;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(8, 8, 10);
        GUI gui = new GUI(board);
    }

    public static void newGame(int width, int height, int mines) {
        Board board = new Board(width, height, mines);
        GUI gui = new GUI(board);
    }
}
