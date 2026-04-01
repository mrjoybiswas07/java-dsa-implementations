public class Update_ST {
    static int tree[];

    public static void initialize(int n) {
        tree = new int[4 * n]; // allocate enough space for segment tree
    }

    public static int buildST(int arr[], int i, int start, int end) {
        if (start == end) {
            tree[i] = arr[start]; // leaf node stores array value
            return arr[start];
        }
        int mid = (start + end) / 2;

        buildST(arr, 2 * i + 1, start, mid); // build left subtree
        buildST(arr, 2 * i + 2, mid + 1, end); // build right subtree

        tree[i] = tree[2 * i + 1] + tree[2 * i + 2]; // store sum of children
        return tree[i];
    }

    public static int getSumUtil(int i, int si, int sj, int qi, int qj) {

        if (qj < si || qi > sj) { // no overlap
            return 0;
        }

        else if (si >= qi && sj <= qj) { // complete overlap
            return tree[i];
        }

        else { // partial overlap
            int mid = (si + sj) / 2;

            int left = getSumUtil(2 * i + 1, si, mid, qi, qj); // left subtree
            int right = getSumUtil(2 * i + 2, mid + 1, sj, qi, qj); // right subtree

            return left + right; // combine results
        }
    }

    public static int getSum(int arr[], int qi, int qj) {
        int n = arr.length;
        return getSumUtil(0, 0, n - 1, qi, qj); // start from root
    }

    public static void updateUtil(int i, int si, int sj, int idx, int diff) {
        if (idx < si || idx > sj) { // if index not in range
            return;
        }

        tree[i] += diff; // update current node

        if (si != sj) { // if not leaf node
            int mid = (si + sj) / 2;

            updateUtil(2 * i + 1, si, mid, idx, diff); // update left subtree
            updateUtil(2 * i + 2, mid + 1, sj, idx, diff); // update right subtree
        }
    }

    public static void update(int arr[], int idx, int newVal) {
        int n = arr.length;

        int diff = newVal - arr[idx]; // calculate difference
        arr[idx] = newVal; // update original array

        updateUtil(0, 0, n - 1, idx, diff);
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int n = arr.length;

        initialize(n);
        buildST(arr, 0, 0, n - 1);

        System.out.println(getSum(arr, 2, 5)); // sum before update

        update(arr, 2, 2); // change index 2 from 3 → 2

        System.out.println(getSum(arr, 2, 5)); // sum after update
    }
}