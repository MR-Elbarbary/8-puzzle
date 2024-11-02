package com.example;

import main.java.com.example.Node;
import main.java.com.example.puzzleSolver;

public class app {
    public static void main(String[] args) {
        puzzleSolver solver = new PuzzleSolver();

        int[][] initialState = {
            {1, 2, 3},
            {4, 0, 5},
            {6, 7, 8}
        };
        Node start = new Node(initialState);
        solver.AStar(start);
    }
}