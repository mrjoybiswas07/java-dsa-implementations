
import java.util.*;

public class Kruskals_Algo {

    static class Edge implements Comparable<Edge> {   // Edge class to store graph edges, implements Comparable for sorting

        int src;                                       // source vertex
        int dest;                                      // destination vertex
        int wt;                                        // weight of edge

        public Edge(int s, int d, int w) {             // constructor to initialize edge
            this.src = s;                              // assign source
            this.dest = d;                             // assign destination
            this.wt = w;                               // assign weight
        }

        @Override
        public int compareTo(Edge e2) {                // defines natural ordering for sorting
            return this.wt - e2.wt;                    // sort edges by weight (ascending)
        }
    }

    static void createGraph(ArrayList<Edge> edges) {   // builds the graph by adding edges
        //edges
        edges.add(new Edge(0, 1, 10));                 // edge 0→1, weight 10
        edges.add(new Edge(0, 2, 15));                 // edge 0→2, weight 15
        edges.add(new Edge(0, 3, 30));                 // edge 0→3, weight 30
        edges.add(new Edge(1, 3, 40));                 // edge 1→3, weight 40
        edges.add(new Edge(2, 3, 50));                 // edge 2→3, weight 50

    }
    static int n = 4;                                  // total number of vertices
    static int par[] = new int[n];                     // parent array for Union-Find
    static int rank[] = new int[n];                    // rank array for Union by Rank

    public static void init() {                        // initialize Union-Find structure
        for (int i = 0; i < n; i++) {
            par[i] = i;                                // each node is its own parent (self loop)
        }
    }

    public static int find(int x) {                    // find root/representative of x
        if (par[x] == x) {                             // if x is its own parent, it's the root
            return x;
        }
        return par[x] = find(par[x]);                  // path compression: flatten the tree
    }

    public static void union(int a, int b) {           // merge two sets using Union by Rank
        int parA = find(a);                            // find root of a
        int parB = find(b);                            // find root of b

        if (rank[parA] == rank[parB]) {                // equal ranks: attach B under A
            par[parB] = parA;                          // make A the parent of B
            rank[parA]++;                              // increase A's rank
        } else if (rank[parA] < rank[parB]) {          // B has higher rank
            par[parA] = parB;                          // attach A under B
        } else {                                       // A has higher rank
            par[parB] = parA;                          // attach B under A
        }

    }

    public static void kruskalsMST(ArrayList<Edge> edges, int V) {  // main Kruskal's algorithm
        init();                                        // initialize parent & rank arrays
        Collections.sort(edges);                       // sort all edges by weight → O(ElogE)
        int mstCost = 0;                               // total MST cost
        int count = 0;                                 // number of edges added to MST
        for (int i = 0; count < V - 1; i++) {         // loop until MST has V-1 edges → O(V)
            Edge e = edges.get(i);                     // pick next cheapest edge

            int parA = find(e.src);                    // find root of source vertex
            int parB = find(e.dest);                   // find root of destination vertex
            if (parA != parB) {                        // if they're in different sets (no cycle)
                union(e.src, e.dest);                  // merge the two sets
                mstCost += e.wt;                       // add edge weight to MST cost
                count++;                               // increment edge count

            }

        }
        System.out.println(mstCost);                   // print total MST cost
    }

    public static void main(String[] args) {           // entry point
        int V = 4;                                     // number of vertices
        ArrayList<Edge> edges = new ArrayList<>();     // list to store all edges
        createGraph(edges);                            // populate edges
        kruskalsMST(edges, V);                         // run Kruskal's algorithm
    }

}
