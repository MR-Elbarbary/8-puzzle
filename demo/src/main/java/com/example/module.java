package main.java.com.example;

import java.util.*;

class Node {
    int[][] state;
    Node parent;
    int depth;
    int cost; // g(n) for A*
    int heuristic; // h(n) for A*
}

class PuzzleSolver {

    private final int[][] goalState = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    public void DFS(Node start) {
    }

    public void BFS(Node start) {
    }

    public void AStar(Node start) {
    }

    private int heuristic(Node node) {
        int[][] currentState = node.state;
        int distance = 0;
        int size = currentState.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = currentState[i][j];
                if (value != 0) {
                    int goalX = value / size;
                    int goalY = value % size;
                    distance += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        }
        return distance;
    }
}
