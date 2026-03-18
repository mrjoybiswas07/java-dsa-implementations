import java.util.ArrayList;          // used for adjacency list
import java.util.LinkedList;         // used to implement queue
import java.util.Queue;              // queue for BFS

public class Chepest_Flights_Within_K_Stop {

    static class Edge {              // edge represents flight

        int src;                    // source node
        int dest;                   // destination node
        int wt;                     // cost

        public Edge(int s, int d, int w) {
            this.src = s;           // assign source
            this.dest = d;          // assign destination
            this.wt = w;            // assign weight
        }
    }

    public static void createGraph(int flights[][], ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();   // initialize list for each node
        }
        for (int i = 0; i < flights.length; i++) {
            int src = flights[i][0];        // read source
            int dest = flights[i][1];       // read destination
            int wt = flights[i][2];         // read cost
            Edge e = new Edge(src, dest, wt); // create edge
            graph[src].add(e);              // add edge to graph
        }
    }

    static class Info {             // helper class for BFS state

        int vertex;                // current node
        int cost;                  // cost till now
        int stops;                 // stops used

        public Info(int v, int c, int s) {
            this.vertex = v;       // assign node
            this.cost = c;         // assign cost
            this.stops = s;        // assign stops
        }
    }

    public static int cheapestFlight(int n, int flights[][], int src, int dest, int k) {
        ArrayList<Edge> graph[] = new ArrayList[n];  // adjacency list
        createGraph(flights, graph);                // build graph

        int dist[] = new int[n];                    // distance array
        for (int i = 0; i < n; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;        // initialize with infinity
            }
        }

        Queue<Info> q = new LinkedList<>();         // BFS queue
        q.add(new Info(src, 0, 0));                 // start from source

        while (!q.isEmpty()) {
            Info curr = q.remove();                 // get current state

            if (curr.stops > k) {
                break;                         // skip if stops exceeded 
            }

            for (int i = 0; i < graph[curr.vertex].size(); i++) {
                Edge e = graph[curr.vertex].get(i); // get neighbor edge
                int u = e.src;                     // source
                int v = e.dest;                    // destination
                int wt = e.wt;                     // weight

                if (curr.cost + wt < dist[v] && curr.stops <= k) { // relaxation check
                    dist[v] = curr.cost + wt;      // update shortest cost
                    q.add(new Info(v, dist[v], curr.stops + 1)); // push next state
                }
            }
        }

        if (dist[dest] == Integer.MAX_VALUE) {
            return -1;                             // not reachable
        } else {
            return dist[dest];                     // return min cost
        }
    } 

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int n = 4;                                 // number of nodes
        int flights[][] = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 0, 100},
            {1, 3, 600},
            {2, 3, 200}
        };                                         // flight data
        int src = 0, dest = 3, k = 1;              // input values

        int ans = cheapestFlight(n, flights, src, dest, k); // call function
        System.out.println(ans);                   // print result
    }
}