
import java.util.*; // Import utility classes like ArrayList

public class Bellman_Ford_algo_modify { // Main class

    static class Edge { // Edge class to store graph edges

        int src; // source vertex
        int dest; // destination vertex
        int wt; // weight of the edge

        // constructor to create edge
        public Edge(int s, int d, int w) {
            this.src = s; // assign source vertex
            this.dest = d; // assign destination vertex
            this.wt = w; // assign weight of edge
        }
    }

    static void createGraph(ArrayList<Edge> graph) { // Function to add edges into graph

        graph.add(new Edge(0, 1, 2)); // Edge: 0 → 1 weight = 2
        graph.add(new Edge(0, 2, 4)); // Edge: 0 → 2 weight = 4

        graph.add(new Edge(1, 2, -4)); // Edge: 1 → 2 weight = -4 (negative edge)

        graph.add(new Edge(2, 3, 2)); // Edge: 2 → 3 weight = 2

        graph.add(new Edge(3, 4, 4)); // Edge: 3 → 4 weight = 4

        graph.add(new Edge(4, 1, -1)); // Edge: 4 → 1 weight = -1
    }

    public static void bellmanFord(ArrayList<Edge> graph, int src, int V) { // Bellman-Ford algorithm

        int dist[] = new int[V]; // Distance array to store shortest distance from source

        for (int i = 0; i < dist.length; i++) { // Initialize distance array
            if (i != src) { // If vertex is not source
                dist[i] = Integer.MAX_VALUE; // Set distance as infinity
            }
        }

        // total complexity O(V * E)
        // algo -O(V)
        for (int i = 0; i < V - 1; i++) { // Repeat relaxation V-1 times

            // edges -O(E)
            for (int j = 0; j < graph.size(); j++) { // Traverse all edges

                Edge e = graph.get(j); // Get edge object from ArrayList

                int u = e.src; // Source vertex of edge
                int v = e.dest; // Destination vertex of edge
                int wt = e.wt; // Weight of edge

                // relaxation step
                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) { // Check if shorter path found
                    dist[v] = dist[u] + wt; // Update shortest distance
                }
            }
        }

        // print shortest distances
        for (int i = 0; i < dist.length; i++) { // Loop through distance array
            System.out.print(dist[i] + " "); // Print distance
        }

        System.out.println(); // Move to next line
    }

    public static void main(String[] args) { // Main function

        int V = 5; // Number of vertices

        ArrayList<Edge> graph = new ArrayList<>(); // Create ArrayList to store edges

        createGraph(graph); // Build graph

        bellmanFord(graph, 0, V); // Run Bellman-Ford from source vertex 0
    }
}
