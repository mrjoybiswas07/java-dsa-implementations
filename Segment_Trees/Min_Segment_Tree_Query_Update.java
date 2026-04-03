public class Min_Segment_Tree_Query_Update {
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
        tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int getMin(int arr[], int qi, int qj) {
        int n = arr.length;
        return getMinUtil(0, 0, n - 1, qi, qj);
    }

    public static int getMinUtil(int i, int si, int sj, int qi, int qj) {
        if (si > qj || sj < qi) { // no - overlap
            return Integer.MAX_VALUE;
        } else if (si >= qi && sj <= qj) { // complete overlap
            return tree[i];
        } else {// partial overlap
            int mid = (si + sj) / 2;
            int left = getMinUtil(2 * i + 1, si, mid, qi, qj);
            int right = getMinUtil(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.min(left, right);
        }

    }

    public static void update(int arr[], int idx, int newVal) {
        arr[idx] = newVal;
        int n = arr.length;
        updateUtil(0, 0, n - 1, idx, newVal);

    }

    public static void updateUtil(int i, int si, int sj, int idx, int newVal) {
        if (idx < si || idx > sj) { // no- overlappring
            return;
        }
        if (si == sj) {
            tree[i] = newVal;
        }

        if (si != sj) {
            tree[i] = Math.min(tree[i], newVal);

            int mid = (si + sj) / 2;
            updateUtil(2 * i + 1, si, mid, idx, newVal);// left
            updateUtil(2 * i + 2, mid + 1, sj, idx, newVal); // right

        }

    }

    public static void main(String[] args) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;

        init(n);
        buildTree(0, 0, n - 1, arr);

        int max = getMin(arr, 2, 5);
        System.out.println(max); // -1
        update(arr, 2, 20);
        max = getMin(arr, 2, 5);
        System.out.println(max);// 1
    }

}
