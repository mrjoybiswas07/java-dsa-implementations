public class zero_one_Kanapsack_Memoization {

    public static int knapsack(int val[], int wt[], int W, int n, int dp[][]) { // method to solve knapsack
        if (W == 0 || n == 0) { // base case: no capacity OR no items
            return 0; // profit = 0
        }

        if (dp[n][W] != -1) { // already solved subproblem
            return dp[n][W]; // return stored result
        }

        if (wt[n - 1] <= W) { // if current item can fit

            // include current item
            int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1, dp);

            // exclude current item
            int ans2 = knapsack(val, wt, W, n - 1, dp);

            dp[n][W] = Math.max(ans1, ans2); // store max of both choices
            return dp[n][W]; // return result

        } else { // item too heavy → cannot include

            dp[n][W] = knapsack(val, wt, W, n - 1, dp); // skip item
            return dp[n][W]; // return result
        }
    }

    public static void main(String[] args) {
        int val[] = { 15, 14, 10, 45, 30 }; // values
        int wt[] = { 2, 5, 1, 3, 4 }; // weights
        int W = 7; // capacity

        int dp[][] = new int[val.length + 1][W + 1]; // dp table (n+1 x W+1)

        // initialize dp with -1 (means not calculated)
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) { 
                dp[i][j] = -1;
            }
        }

        System.out.println(knapsack(val, wt, W, val.length, dp)); // final answer
    }
}