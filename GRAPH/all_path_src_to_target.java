import java.util.*;                 // Import Java utility package (ArrayList etc.)

public class all_path_src_to_target {   // Main class

    // Edge class represents a directed edge (src -> dest)
    static class Edge {

        int src;   // source vertex
        int dest;  // destination vertex

        // constructor to create edge
        public Edge(int s, int d) {
            this.src = s;   // assign source value
            this.dest = d;  // assign destination value
        }
    }

    // Function to create graph using adjacency list
    static void createGraph(ArrayList<Edge>[] graph) {

        // initialize each index of graph with empty ArrayList
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // adding directed edges to graph
        graph[0].add(new Edge(0, 3)); // edge from 0 → 3
        graph[2].add(new Edge(2, 3)); // edge from 2 → 3
        graph[3].add(new Edge(3, 1)); // edge from 3 → 1
        graph[4].add(new Edge(4, 0)); // edge from 4 → 0
        graph[4].add(new Edge(4, 1)); // edge from 4 → 1
        graph[5].add(new Edge(5, 0)); // edge from 5 → 0
        graph[5].add(new Edge(5, 2)); // edge from 5 → 2
    }

    // DFS function to print all paths from source to destination
    public static void printAllPath(ArrayList<Edge> graph[], int src, int dest, String path) {

        // base case: if source becomes destination
        if (src == dest) {
            System.out.println(path + dest); // print complete path
            return;                          // stop recursion
        }

        // loop through all neighbors of current node
        for (int i = 0; i < graph[src].size(); i++) {

            Edge e = graph[src].get(i); // get edge from adjacency list

            // recursive DFS call for next node
            printAllPath(graph, e.dest, dest, path + src + "->");
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        int V = 7; // number of vertices in graph

        // create adjacency list of size V
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph); // build graph

        int src = 5, dest = 1; // source and destination

        // call function to print all paths
        printAllPath(graph, src, dest, "");
    }
}