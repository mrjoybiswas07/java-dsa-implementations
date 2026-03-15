import java.util.*; // Import all utility classes like ArrayList, etc.

public class Bellman_Ford_algo { // Main class name

    static class Edge { // Edge class to represent a graph edge

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

    static void createGraph(ArrayList<Edge> graph[]) { // Function to create graph

        for (int i = 0; i < graph.length; i++) { // Loop through all vertices
            graph[i] = new ArrayList<>(); // Initialize each vertex with empty edge list
        }

        graph[0].add(new Edge(0, 1, 2)); // Edge from 0 → 1 with weight 2
        graph[0].add(new Edge(0, 2, 4)); // Edge from 0 → 2 with weight 4

        graph[1].add(new Edge(1, 2, -4)); // Edge from 1 → 2 with weight -4 (negative edge)

        graph[2].add(new Edge(2, 3, 2)); // Edge from 2 → 3 with weight 2

        graph[3].add(new Edge(3, 4, 4)); // Edge from 3 → 4 with weight 4

        graph[4].add(new Edge(4, 1, -1)); // Edge from 4 → 1 with weight -1
    }

    public static void bellmanFord(ArrayList<Edge> graph[], int src) { // Bellman-Ford algorithm

        int dist[] = new int[graph.length]; // Distance array to store shortest distance

        for (int i = 0; i < dist.length; i++) { // Initialize distance array
            if (i != src) { // If vertex is not source
                dist[i] = Integer.MAX_VALUE; // Set distance as infinity
            }
        }

        int V = graph.length; // Total number of vertices
        // total O(V*E)
        // algo -O(V)
        for (int i = 0; i < V - 1; i++) { // Repeat relaxation V-1 times

            // edges -O(E)
            for (int j = 0; j < graph.length; j++) { // Traverse all vertices

                for (int k = 0; k < graph[j].size(); k++) { // Traverse all edges of vertex j

                    Edge e = graph[j].get(k); // Get current edge

                    // U,v,Wt
                    int u = e.src; // Source vertex of edge
                    int v = e.dest; // Destination vertex
                    int wt = e.wt; // Weight of edge

                    // relaxation step
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt; // Update shortest distance
                    }
                }
            }
        }

        // print
        for (int i = 0; i < dist.length; i++) { // Print all shortest distances
            System.out.print(dist[i] + " ");
        }

        System.out.println(); // Move to next line
    }

    @SuppressWarnings("unchecked") // Suppress generic array warning
    public static void main(String[] args) {

        int V = 5; // Number of vertices

        ArrayList<Edge> graph[] = new ArrayList[V]; // Create graph array

        createGraph(graph); // Call function to build the graph
        bellmanFord(graph,0);
    }
}