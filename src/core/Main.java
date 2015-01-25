package core;

import gui.GUI;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(8, 8, 10);
        GUI gui = new GUI(board);
    }
}
