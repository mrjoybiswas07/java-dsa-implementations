import java.util.*;  // Import utilities like ArrayList, Queue

class kahns_algo {

    // Edge class represents a directed edge (src -> dest)
    static class Edge {
        int src;   // source vertex
        int dest;  // destination vertex

        // constructor to create edge
        public Edge(int s, int d) {
            this.src = s;   // assign source
            this.dest = d;  // assign destination
        }
    }

    // Function to create graph using adjacency list
    static void createGraph(ArrayList<Edge>[] graph) {

        // initialize each index with empty list
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // add directed edges
        graph[2].add(new Edge(2, 3)); // 2 -> 3
        graph[3].add(new Edge(3, 1)); // 3 -> 1
        graph[4].add(new Edge(4, 0)); // 4 -> 0
        graph[4].add(new Edge(4, 1)); // 4 -> 1
        graph[5].add(new Edge(5, 0)); // 5 -> 0
        graph[5].add(new Edge(5, 2)); // 5 -> 2
    }

    // Calculate indegree of each vertex
    public static void calcIndeg(ArrayList<Edge>[] graph, int indeg[]) {

        // go through each vertex
        for (int i = 0; i < graph.length; i++) {

            // check all edges from that vertex
            for (int j = 0; j < graph[i].size(); j++) {

                Edge e = graph[i].get(j);  // get edge
                indeg[e.dest]++;           // increase indegree of destination
            }
        }
    }

    // Kahn's Algorithm (BFS based Topological Sort)
    public static void topSort(ArrayList<Edge>[] graph) {

        int indeg[] = new int[graph.length];  // store indegree

        calcIndeg(graph, indeg);              // Step 1: calculate indegree

        Queue<Integer> q = new LinkedList<>(); // queue for BFS

        // Step 2: add vertices with indegree = 0
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        // Step 3: BFS traversal
        while (!q.isEmpty()) {

            int curr = q.remove();         // remove node from queue
            System.out.print(curr + " ");  // print topological order

            // Step 4: reduce indegree of neighbors
            for (int i = 0; i < graph[curr].size(); i++) {

                Edge e = graph[curr].get(i);

                indeg[e.dest]--;          // decrease indegree

                // Step 5: if indegree becomes 0 add to queue
                if (indeg[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }

        System.out.println();
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        int V = 6; // number of vertices

        ArrayList<Edge>[] graph = new ArrayList[V]; // adjacency list

        createGraph(graph); // create graph

        topSort(graph);     // run Kahn's algorithm
    }
}