package main.java.com.example;

public class app {
    public static void main(String[] args) {
        puzzleSolver solver = new puzzleSolver();

        int[][] initialState = {
            {1, 2, 3},
            {4, 0, 5},
            {6, 7, 8}
        };
        Node start = new Node(initialState);
        solver.AStar(start);
    }
}