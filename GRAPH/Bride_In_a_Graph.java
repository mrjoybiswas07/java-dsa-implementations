import java.util.*; // for ArrayList and other utility classes

public class Bride_In_a_Graph {

    static class Edge {
        int src; // source vertex
        int dest; // destination vertex

        public Edge(int s, int d) {
            this.src = s; // set source
            this.dest = d; // set destination
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>(); // initialize each list
        }

        graph[0].add(new Edge(0, 1)); // 0 -> 1
        graph[0].add(new Edge(0, 2)); // 0 -> 2
        graph[0].add(new Edge(0, 3)); // 0 -> 3

        graph[1].add(new Edge(1, 0)); // 1 -> 0
        graph[1].add(new Edge(1, 2)); // 1 -> 2

        graph[2].add(new Edge(2, 0)); // 2 -> 0
        graph[2].add(new Edge(2, 1)); // 2 -> 1

        graph[3].add(new Edge(3, 0)); // 3 -> 0
        graph[3].add(new Edge(3, 4)); // 3 -> 4
        // graph[3].add(new Edge(3, 5)); // 3 -> 5

        graph[4].add(new Edge(4, 3)); // 4 -> 3
        // graph[4].add(new Edge(4, 5)); // 4 -> 5

        // graph[5].add(new Edge(5, 3)); // 5 -> 3
        // graph[5].add(new Edge(5, 4)); // 5 -> 4
    }

    public static void dfs(ArrayList<Edge> graph[], int curr, int par, int dt[], int low[], boolean vis[], int time) {
        vis[curr] = true; // mark current as visited
        dt[curr] = low[curr] = ++time; // set discovery time and low time

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i); // get current edge
            int neigh = e.dest; // neighbor node

            if (neigh == par) {
                continue; // skip parent node
            } else if (!vis[neigh]) {
                dfs(graph, neigh, curr, dt, low, vis, time); // DFS on unvisited neighbor

                low[curr] = Math.min(low[curr], low[neigh]); // update low of current

                if (dt[curr] < low[neigh]) { // bridge condition
                    System.out.println("Bridge " + curr + " ------ " + neigh); // print bridge
                }
            } else {
                low[curr] = Math.min(low[curr], dt[neigh]); // update low using visited neighbor
            }
        }
    }

    public static void trajanBridge(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V]; // discovery time array
        int low[] = new int[V]; // lowest reachable time array
        int time = 0; // timer
        boolean vis[] = new boolean[V]; // visited array

        for (int i = 0; i < V; i++) { // check all vertices
            if (!vis[i]) {
                dfs(graph, i, -1, dt, low, vis, time); // start DFS from unvisited node
            }
        }
    }

    public static void main(String[] args) {
        int V = 6; // number of vertices
        ArrayList<Edge> graph[] = new ArrayList[V]; // graph array
        createGraph(graph); // create graph
        trajanBridge(graph, V); // find bridges
    }
}