package main.java.com.example;


public class Node {
    int[][] state;
    Node parent;
    int depth;
    int cost; // g(n) for A*
    int heuristic; // h(n) for A*
    int totalCost; // cost or depth + heuristic
    public Node(int [][]state){
        this.state = state;
    }
    public Node(){}
}