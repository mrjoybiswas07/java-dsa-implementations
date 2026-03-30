public class MCM_Recursion {

    public static int mcm(int arr[], int i, int j) {

        if (i == j) {
            return 0; // only one matrix, no multiplication
        }

        int ans = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) { // split point

            int cost1 = mcm(arr, i, k); // left part
            int cost2 = mcm(arr, k + 1, j); // right part

            int cost3 = arr[i - 1] * arr[k] * arr[j]; // multiply cost

            int finalCost = cost1 + cost2 + cost3;

            ans = Math.min(ans, finalCost); // take minimum
        }

        return ans;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 3 };
        int n = arr.length;

        System.out.println(mcm(arr, 1, n - 1)); // call from 1 to n-1
    }
}