
public class Disjoint_Set_Union {

    static int n = 7;                    // Total number of elements (0 to 6)
    static int par[] = new int[n];       // par[i] = parent of element i
    static int rank[] = new int[n];      // rank[i] = tree height estimate (for balancing)

    // Initializes each element as its own parent → n isolated sets
    public static void init() {
        for (int i = 0; i < n; i++) {
            par[i] = i;                  // Every element is its own root initially
        }
        // rank[] is already 0 by default (all trees have height 0)
    }

    // Returns the root (representative) of the set containing x
    public static int find(int x) {
        if (x == par[x]) {
            return x;                    // x is the root → base case, return it
        }
        // Path compression: flatten the tree by pointing x directly to the root
        // par[x] = find(par[x]) reassigns the parent of x to the ultimate root
        return par[x] = find(par[x]);   // Recursive call + compress path on the way back
    }

    // Merges the sets containing a and b into one set
    public static void union(int a, int b) {
        int parA = find(a);              // Find root of a's set
        int parB = find(b);              // Find root of b's set

        // If already in the same set, nothing to do (not checked here, but roots would be equal)
        if (rank[parA] == rank[parB]) {       // Both trees have same height
            par[parB] = parA;                 // Attach B's tree under A's root (arbitrary choice)
            rank[parA]++;                     // A's tree grows taller by 1
        } else if (rank[parA] < rank[parB]) { // B's tree is taller
            par[parA] = parB;                 // Attach A under B to keep overall height smaller
        } else {                              // A's tree is taller
            par[parB] = parA;                 // Attach B under A to keep overall height smaller
        }
        // Union by rank ensures the shorter tree is always attached under the taller one
        // This prevents the tree from degenerating into a linked list (O(n) find)
    }

    public static void main(String[] args) {
        init();                          // Sets: {0},{1},{2},{3},{4},{5},{6}

        System.out.println(find(3));     // → 3 (3 is its own root)

        union(1, 3);                     // Merges {1} and {3} → {1,3}, root = 1
        System.out.println(find(3));     // → 1 (3's root is now 1)

        union(2, 4);                     // Merges {2} and {4} → {2,4}, root = 2
        union(3, 6);                     // find(3)=1, find(6)=6 → merges {1,3} and {6}, root = 1
        union(1, 4);                     // find(1)=1, find(4)=2 → merges {1,3,6} and {2,4}, root = 1
        // rank[1]==rank[2]==1, so par[2]=1 and rank[1] becomes 2

        System.out.println(find(3));     // → 1 (path: 3→1, already compressed)
        System.out.println(find(4));     // → 1 (path: 4→2→1, compressed to 4→1)

        union(1, 5);                     // find(1)=1, find(5)=5 → merges {1,2,3,4,6} and {5}
        // rank[1]=2 > rank[5]=0, so par[5]=1
    }
}
