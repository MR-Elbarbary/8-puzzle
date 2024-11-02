package main.java.com.example;


import java.util.*;
import main.java.com.example.Node;

public class puzzleSolver {

    private final int[][] goalState = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    private final int[][] directions = {
        {-1, 0}, // Up
        {1, 0},  // Down
        {0, -1}, // Left
        {0, 1}   // Right
    };
    public Node DFS(Node start, Set<String> visited) {
        if (Arrays.deepEquals(goalState, start.state)) {
            System.out.println("Path cost: "+currentNode.depth);
            System.out.println("path:");
            System.out.println(toStringPath(start));
            return start;
        }
        visited.add(Arrays.deepToString(start.state));
        for (Node nextNode : getLegalMoves(start)) {
            if (!visited.contains(Arrays.deepToString(nextNode.state))) {
                DFS(nextNode, visited);
            }
        }
        return null;
    }

    public Node BFS(Node start) {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(Arrays.deepToString(start.state));
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (Arrays.deepEquals(goalState, currentNode.state)) {
                System.out.println("Nodes expanded: "+visited.size());
                System.out.println("Path cost: "+currentNode.depth);
                System.out.println("path:");
                System.out.println(toStringPath(start));
                return currentNode;
            }
            for (Node nextNode : getLegalMoves(currentNode)) {
                if (!visited.contains(Arrays.deepToString(nextNode.state))) {
                    queue.add(nextNode);
                    visited.add(Arrays.deepToString(nextNode.state));
                }
            }
        }
        return null;
    }

    public Node AStar(Node start) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(n -> n.totalCost));
        Set<String> visited = new HashSet<>();
        start.heuristic = heuristic(start);
        start.totalCost = start.depth + start.heuristic;
        minHeap.add(start);
        visited.add(Arrays.deepToString(start.state));
        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            if (Arrays.deepEquals(goalState, currentNode.state)) {
                System.out.println("Nodes expanded: "+visited.size());
                System.out.println("Path cost: "+currentNode.depth);
                System.out.println("path:");
                System.out.println(toStringPath(start));
                return currentNode;
            }
            for (Node nextNode : getLegalMoves(currentNode)) {
                if (!visited.contains(Arrays.deepToString(nextNode.state))) {
                    nextNode.heuristic = heuristic(nextNode);
                    nextNode.totalCost = nextNode.depth + nextNode.heuristic;
                    minHeap.add(nextNode);
                    visited.add(Arrays.deepToString(nextNode.state));                    
                }
            }
        }
        return null;
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

    private int[] findEmptyTile(int[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    private List<Node> getLegalMoves(Node node) {
        List<Node> moves = new ArrayList<>();
        int[][] currentState = node.state;
        int[] emptyTilePos = findEmptyTile(currentState);
        int row = emptyTilePos[0];
        int col = emptyTilePos[1];
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int[][] newState = deepCopy(currentState);
                newState[row][col] = newState[newRow][newCol];
                newState[newRow][newCol] = 0;

                // Create a new Node for the new state
                //might refactor
                Node newNode = new Node();
                newNode.state = newState;
                newNode.parent = node;
                newNode.depth = node.depth + 1;

                moves.add(newNode);
            }
        }
        return moves;
    }
    private int[][] deepCopy(int[][] currentState) {
        int [][] newState = new int[currentState.length][];
        for(int i = 0; i < currentState.length; i++)
            newState[i] = currentState[i].clone();
        return newState;
    }
    private String toStringPath(Node node){
        String path ="";
        path = Arrays.deepToString(node.state);
        while (node.parent) {
            path = Arrays.deepToString(node.state)+"/n"+
            "|" +"/n" +
            "V"+"/n"
            + path;
            node = node.parent;
        }
        return path;
    }
}
