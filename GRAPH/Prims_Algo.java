import java.util.ArrayList;        // for adjacency list
import java.util.PriorityQueue;   // for min heap

public class Prims_Algo {

    // Edge class to store graph edges
    static class Edge {
        int src;   // source node
        int dest;  // destination node
        int wt;    // weight

        public Edge(int s, int d, int w) {
            this.src = s;   // assign source
            this.dest = d;  // assign destination
            this.wt = w;    // assign weight
        }
    }

    // Function to create graph
    static void createGraph(ArrayList<Edge> graph[]) {

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();   // initialize each list
        }

        // add edges (undirected graph)

        graph[0].add(new Edge(0, 1, 10)); // 0 -> 1
        graph[0].add(new Edge(0, 2, 15)); // 0 -> 2
        graph[0].add(new Edge(0, 3, 30)); // 0 -> 3

        graph[1].add(new Edge(1, 0, 10)); // 1 -> 0
        graph[1].add(new Edge(1, 3, 40)); // 1 -> 3

        graph[2].add(new Edge(2, 0, 15)); // 2 -> 0
        graph[2].add(new Edge(2, 3, 50)); // 2 -> 3

        graph[3].add(new Edge(3, 1, 40)); // 3 -> 1
        graph[3].add(new Edge(3, 2, 50)); // 3 -> 2
        graph[3].add(new Edge(3, 0, 30)); // 3 -> 0
    }

    // Pair class for priority queue
    static class Pair implements Comparable<Pair> {
        int v;     // current node
        int cost;  // edge cost

        public Pair(int v, int c) {
            this.v = v;     // store node
            this.cost = c;  // store cost
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost; // min heap (small cost first)
        }
    }

    // Prim's Algorithm function
    public static void prims(ArrayList<Edge> graph[]) {

        boolean vis[] = new boolean[graph.length]; // visited array
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // min heap

        pq.add(new Pair(0, 0)); // start from node 0 with cost 0

        int finalCost = 0; // store MST total cost

        while (!pq.isEmpty()) {

            Pair curr = pq.remove(); // get min cost node

            if (!vis[curr.v]) { // if not visited

                vis[curr.v] = true; // mark visited
                finalCost += curr.cost; // add cost to MST

                // explore neighbors
                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i); // get edge

                    if (!vis[e.dest]) { // only if not visited
                        pq.add(new Pair(e.dest, e.wt)); // push into pq
                    }
                }
            }
        }

        System.out.println("Final (Minimum) Cost of MST = " + finalCost); // print result
    }

    public static void main(String[] args) {

        int V = 4; // number of vertices

        ArrayList<Edge> graph[] = new ArrayList[V]; // create graph array

        createGraph(graph); // build graph

        prims(graph); // run prims algorithm
    }
}