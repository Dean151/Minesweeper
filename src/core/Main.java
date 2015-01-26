package core;

import gui.GUI;

public class Main {
    public static void main(String[] args) {
        newGame(8, 8, 10);
    }

    public static void newGame(int width, int height, int mines) {
        Board board = new Board(width, height, mines);
        GUI gui = new GUI(board);
    }
}
