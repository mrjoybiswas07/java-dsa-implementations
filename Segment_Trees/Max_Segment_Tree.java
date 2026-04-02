public class Max_Segment_Tree {
    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n]; // Allocate enough space (safe size)
    }

    public static void buildTree(int i, int si, int sj, int arr[]) {
        if (si == sj) {
            tree[i] = arr[si];
            // Leaf node stores actual array value
            return;
        }

        int mid = (si + sj) / 2;
        // Better for large values: si + (sj - si) / 2

        // Build left subtree
        buildTree(2 * i + 1, si, mid, arr);

        // Build right subtree
        buildTree(2 * i + 2, mid + 1, sj, arr);

        // Store max of children
        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static void main(String[] args) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;

        init(n);
        buildTree(0, 0, n - 1, arr);

        // Print segment tree
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
    }
}