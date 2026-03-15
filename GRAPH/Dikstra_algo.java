import java.util.*;

public class Dikstra_algo {

    public static class Edge {
        int src, dest, wt;                          // edge has: from, to, weight

        public Edge(int s, int d, int w) {
            this.src = s;                           // store source node
            this.dest = d;                          // store destination node
            this.wt = w;                            // store edge weight
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();           // each node gets an empty edge list

        // graph[from].add(new Edge(from, to, weight))
        graph[0].add(new Edge(0, 1, 2));            // 0 -> 1, cost 2
        graph[0].add(new Edge(0, 2, 4));            // 0 -> 2, cost 4
        graph[1].add(new Edge(1, 3, 7));            // 1 -> 3, cost 7
        graph[1].add(new Edge(1, 2, 1));            // 1 -> 2, cost 1
        graph[2].add(new Edge(2, 4, 3));            // 2 -> 4, cost 3
        graph[3].add(new Edge(3, 5, 1));            // 3 -> 5, cost 1
        graph[4].add(new Edge(4, 3, 2));            // 4 -> 3, cost 2
        graph[4].add(new Edge(4, 5, 5));            // 4 -> 5, cost 5
    }

    static class Pair implements Comparable<Pair> { // Pair = (node, distance from src)
        int n;                                      // node number
        int path;                                   // distance to reach this node from src

        public Pair(int n, int path) {
            this.n = n;                             // save node
            this.path = path;                       // save its distance
        }

        @Override
        public int compareTo(Pair p2) {             // PQ uses this to sort: smallest path first
            return this.path - p2.path;             // min-heap based on path cost
        }
    }

    public static void dijkstra(ArrayList<Edge> graph[], int src) {
        int dist[] = new int[graph.length];         // dist[i] = shortest distance from src to i

        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;        // assume all nodes unreachable at start
            }
        }                                           // dist[src] = 0 by default (int array)

        boolean vis[] = new boolean[graph.length];  // vis[i] = true means node i is finalized
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // min-heap: gives closest node first
        pq.add(new Pair(src, 0));                   // start from src with distance 0

        while (!pq.isEmpty()) {                     // keep going until all reachable nodes done
            Pair curr = pq.remove();                // pick node with smallest distance so far
            if (!vis[curr.n]) {                     // skip if already finalized
                vis[curr.n] = true;                 // mark current node as finalized

                for (int i = 0; i < graph[curr.n].size(); i++) { // check all neighbors
                    Edge e = graph[curr.n].get(i);  // get each edge from current node
                    int u = e.src;                  // edge starts at u
                    int v = e.dest;                 // edge goes to v
                    int wt = e.wt;                  // cost of this edge

                    if (dist[u] + wt < dist[v]) {   // found a shorter path to v?
                        dist[v] = dist[u] + wt;     // ✅ update with correct formula (was bug: dist[v]+wt)
                        pq.add(new Pair(v, dist[v])); // push updated distance to pq
                    }
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {    // print shortest dist from src to every node
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")                  // suppress raw array type warning
    public static void main(String[] args) {
        int V = 6;                                  // total 6 nodes (0 to 5)
        ArrayList<Edge>[] graph = new ArrayList[V]; // adjacency list for the graph
        createGraph(graph);                         // fill graph with edges
        int src = 0;                                // start node
        dijkstra(graph, src);                       // find shortest paths from node 0
    }
}