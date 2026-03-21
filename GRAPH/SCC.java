import java.util.ArrayList; // import ArrayList to use as adjacency list
import java.util.Stack; // import Stack for topological sort finish-order

public class SCC { // class to find Strongly Connected Components

    static class Edge { // inner class to represent a directed edge
        int src; // source vertex of edge
        int dest; // destination vertex of edge

        public Edge(int s, int d) { // constructor to create an edge
            this.src = s; // assign source
            this.dest = d; // assign destination
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) { // builds the graph
        for (int i = 0; i < graph.length; i++) { // loop through each vertex
            graph[i] = new ArrayList<Edge>(); // initialize empty edge list for vertex i
        }
        graph[0].add(new Edge(0, 2)); // add edge 0 → 2
        graph[0].add(new Edge(0, 3)); // add edge 0 → 3
        graph[1].add(new Edge(1, 0)); // add edge 1 → 0
        graph[2].add(new Edge(2, 1)); // add edge 2 → 1 (0→2→1→0 forms a cycle = 1 SCC)
        graph[3].add(new Edge(3, 4)); // add edge 3 → 4 (no return = separate SCCs)
    }

    // STEP 1 HELPER: DFS that pushes nodes to stack in finish-time order
    public static void topSort(ArrayList<Edge> graph[], int curr, boolean vis[], Stack<Integer> s) {
        vis[curr] = true; // mark current node as visited
        for (int i = 0; i < graph[curr].size(); i++) { // loop through all edges of curr
            Edge e = graph[curr].get(i); // get each edge from curr
            if (!vis[e.dest]) { // if neighbour not visited
                topSort(graph, e.dest, vis, s); // recurse into neighbour first (go deep)
            }
        }
        s.push(curr); // push AFTER all neighbours done → last to finish = top of stack
    }

    // STEP 3 HELPER: plain DFS on transposed graph to print one full SCC
    public static void dfs(ArrayList<Edge> graph[], int curr, boolean vis[]) {
        vis[curr] = true; // mark current node visited
        System.out.print(curr + " "); // print node → it belongs to current SCC
        for (int i = 0; i < graph[curr].size(); i++) { // loop through all edges of curr
            Edge e = graph[curr].get(i); // FIX: was missing "=" (syntax error)
            if (!vis[e.dest]) { // if neighbour not yet visited
                dfs(graph, e.dest, vis); // FIX: was "curr" → must be "e.dest" (was infinite loop)
            }
        }
    }

    public static void kosaraju(ArrayList<Edge> graph[], int V) { // main algorithm

        // ── STEP 1: Fill stack by DFS finish time ──────────────────────────
        Stack<Integer> s = new Stack<>(); // stack stores nodes in finish order
        boolean vis[] = new boolean[V]; // visited array, all false by default
        for (int i = 0; i < V; i++) { // loop every vertex
            if (!vis[i]) { // if not yet visited
                topSort(graph, i, vis, s); // run DFS from this vertex, fill stack
            }
        }

        // ── STEP 2: Build Transpose (reverse all edges) ────────────────────
        ArrayList<Edge> transpose[] = new ArrayList[V]; // new graph with reversed edges
        for (int i = 0; i < graph.length; i++) { // loop every vertex
            vis[i] = false; // reset visited[] for reuse in step 3
            transpose[i] = new ArrayList<Edge>(); // init empty list for each vertex
        }
        for (int i = 0; i < V; i++) { // loop every vertex
            for (int j = 0; j < graph[i].size(); j++) { // loop every edge of vertex i
                Edge e = graph[i].get(j); // get original edge e.src → e.dest
                transpose[e.dest].add(new Edge(e.dest, e.src)); // add reversed edge e.dest → e.src
            }
        }

        // ── STEP 3: DFS on transpose in stack (finish-time) order ──────────
        while (!s.isEmpty()) { // process all nodes from stack
            int curr = s.pop(); // pop node with highest finish time first
            if (!vis[curr]) { // if not yet visited → it's a new SCC root
                System.out.print("SCC -> "); // print SCC label
                dfs(transpose, curr, vis); // DFS on transpose finds all members of this SCC
                System.out.println(); // newline after each SCC is fully printed
            }
        }
    }

    @SuppressWarnings("unchecked") // suppress generic array warning
    public static void main(String[] args) {
        int V = 5; // total number of vertices
        ArrayList<Edge> graph[] = new ArrayList[V]; // adjacency list of size V

        createGraph(graph); // build the graph with edges
        kosaraju(graph, V); // find and print all SCCs
    }
}


 