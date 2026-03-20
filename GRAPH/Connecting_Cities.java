
import java.util.PriorityQueue;

public class Connecting_Cities {

    static class Edge implements Comparable<Edge> {          // Edge class for min-heap sorting

        int dest;                                            // destination city index
        int cost;                                            // edge weight/cost

        public Edge(int d, int c) {
            this.dest = d;                                   // set destination
            this.cost = c;                                   // set cost
        }

        @Override
        public int compareTo(Edge e2) {
            return this.cost - e2.cost;                      // min-heap: lower cost = higher priority
        }
    }
    public static int connectCities(int cities[][]) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();      // min-heap to pick cheapest edge
        boolean vis[] = new boolean[cities.length];          // track visited cities
        pq.add(new Edge(0, 0));                              // start from city 0 with cost 0
        int finalCost = 0;                                   // accumulate total MST cost
        while (!pq.isEmpty()) {                              // process until no edges left
            Edge curr = pq.remove();                         // pick cheapest available edge
            if (!vis[curr.dest]) {                           // skip if city already visited
                vis[curr.dest] = true;                       // mark city as visited
                finalCost += curr.cost;                      // add edge cost to MST total
                for (int i = 0; i < cities[curr.dest].length; i++) {  // loop all neighbors
                    if (cities[curr.dest][i] != 0) {         // skip if no connection (0 = no edge)
                        pq.add(new Edge(i, cities[curr.dest][i]));     // add neighbor to heap
                    }
                }
            }
        }
        return finalCost;                                    // return total MST cost (missing in original) 
    }

    public static void main(String[] args) {
         int cities[][] = { // adjacency matrix of city connections
            {0, 1, 2, 3, 4}, // city 0  connections
            {1, 0, 5, 0, 7}, // city 1 connections
            {2, 5, 0, 6, 0}, // city 2 connections
            {3, 0, 6, 0, 0}, // city 3 connections
            {4, 7, 0, 0, 0}};                               // city 4 connections

        System.out.println(connectCities(cities));           // print minimum connection cost
    }
}
