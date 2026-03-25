public class Target_Sum_Subset {
    // O(n * sum) -> DP complexity
    public static boolean targetSumSubset(int arr[], int sum) {
        int n = arr.length;

        boolean dp[][] = new boolean[n + 1][sum + 1];
        // dp[i][j] = can we make sum j using first i items?
        
        // base case: sum = 0 is always possible
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true; // 0 sum possible by taking nothing
        }
        // fill DP table
        for (int i = 1; i < n + 1; i++) { // items loop
            for (int j = 1; j < sum + 1; j++) { // sum loop (FIXED)
                int v = arr[i - 1]; // current value

                // include case
                if (v <= j && dp[i - 1][j - v] == true) {
                    dp[i][j] = true; // include current element
                }
                // exclude case
                else if (dp[i - 1][j] == true) {
                    dp[i][j] = true; // skip current element
                }
                // otherwise false (default)
            }
        }
        print(dp);
        return dp[n][sum]; // final answer
    }

    public static void print(boolean dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) { // FIXED
                System.out.print(dp[i][j] + " "); // print table
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 4, 2, 7, 1, 3 };
        int sum = 10;

        System.out.println(targetSumSubset(arr, sum));
    }
}