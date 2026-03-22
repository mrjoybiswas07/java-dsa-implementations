import java.util.ArrayList; // imports ArrayList to store graph edges

public class Aticulation_Point { // class for finding articulation points in graph

    static class Edge { // Edge class to represent connection between two vertices
        int src; // source vertex
        int dest; // destination vertex

        public Edge(int s, int d) { // constructor to initialize edge
            this.src = s; // assign source vertex
            this.dest = d; // assign destination vertex
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) { // function to create graph
        for (int i = 0; i < graph.length; i++) { // loop through each vertex
            graph[i] = new ArrayList<Edge>(); // initialize each adjacency list
        }

        graph[0].add(new Edge(0, 1)); // add edge from 0 to 1
        graph[0].add(new Edge(0, 2)); // add edge from 0 to 2
        graph[0].add(new Edge(0, 3)); // add edge from 0 to 3

        graph[1].add(new Edge(1, 0)); // add edge from 1 to 0
        graph[1].add(new Edge(1, 2)); // add edge from 1 to 2

        graph[2].add(new Edge(2, 0)); // add edge from 2 to 0
        graph[2].add(new Edge(2, 1)); // add edge from 2 to 1

        graph[3].add(new Edge(3, 0)); // add edge from 3 to 0
        graph[3].add(new Edge(3, 4)); // add edge from 3 to 4

        graph[4].add(new Edge(4, 3)); // add edge from 4 to 3
    }

    // O(V + E)
    public static void dfs(ArrayList<Edge> graph[], int curr, int par, int dt[], int low[], int time, boolean vis[],
            boolean ap[]) { // DFS function to find articulation points

        vis[curr] = true; // mark current node as visited
        dt[curr] = low[curr] = ++time; // set discovery time and low value of current node
        int children = 0; // counts DFS tree children of current node

        for (int i = 0; i < graph[curr].size(); i++) { // traverse all neighbors of current node
            Edge e = graph[curr].get(i); // get current edge from adjacency list
            int neigh = e.dest; // store neighbor node

            if (par == neigh) { // if neighbor is parent, ignore it
                continue; // skip parent edge
            } else if (vis[neigh]) { // if neighbor already visited, back edge found
                low[curr] = Math.min(low[curr], dt[neigh]); // update low value using neighbor's discovery time
            } else { // if neighbor is not visited
                dfs(graph, neigh, curr, dt, low, time, vis, ap); // recursively call DFS for unvisited neighbor
                low[curr] = Math.min(low[curr], low[neigh]); // update current low using child's low

                if (par != -1 && dt[curr] <= low[neigh]) { // articulation point condition for non-root
                    ap[curr] = true; // mark current node as articulation point
                }

                children++; // increment child count for current node
            }
        }

        if (par == -1 && children > 1) { // articulation point condition for root node
            ap[curr] = true; // root is articulation point if it has more than one DFS child
        }
    }

    public static void getAp(ArrayList<Edge> graph[], int V) { // function to find all articulation points
        int dt[] = new int[V]; // discovery time array
        int low[] = new int[V]; // low value array
        int time = 0; // timer variable
        boolean vis[] = new boolean[V]; // visited array
        boolean ap[] = new boolean[V]; // articulation point array

        for (int i = 0; i < graph.length; i++) { // run DFS for each component
            if (!vis[i]) { // if node is unvisited
                dfs(graph, i, -1, dt, low, time, vis, ap); // call DFS starting from node i with parent = -1
            }
        }

        for (int i = 0; i < V; i++) { // loop through all vertices
            if (ap[i]) { // if current node is an articulation point
                System.out.println("Ap: " + i); // print articulation point
            }
        }
    }

    public static void main(String[] args) { // main function
        int V = 5; // number of vertices
        ArrayList<Edge> graph[] = new ArrayList[V]; // create adjacency list array
        createGraph(graph); // build graph
        getAp(graph, V); // find and print articulation points
    }
}