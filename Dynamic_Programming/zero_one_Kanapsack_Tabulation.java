public class zero_one_Kanapsack_Tabulation { // 0/1 Knapsack using Bottom-Up DP

    public static int knapsackTab(int val[], int wt[], int W) { // function to compute max profit
        int n = val.length; // total items

        int dp[][] = new int[n + 1][W + 1]; // dp[i][j] = max profit using i items & capacity j

        // base case initialization
        for (int i = 0; i <= n; i++) { // for all items
            dp[i][0] = 0; // capacity 0 → profit 0
        }

        for (int j = 0; j <= W; j++) { // for all capacities
            dp[0][j] = 0; // 0 items → profit 0
        }

        // main DP logic
        for (int i = 1; i <= n; i++) { // loop over items
            for (int j = 1; j <= W; j++) { // loop over capacity

                int v = val[i - 1]; // current item value
                int w = wt[i - 1]; // correct weight of ith item

                if (w <= j) {
                    // choice: include OR exclude
                    int include = v + dp[i - 1][j - w]; // take item
                    int exclude = dp[i - 1][j]; // skip item

                    dp[i][j] = Math.max(include, exclude); // take best option

                } else {
                    // cannot include (weight > capacity)
                    dp[i][j] = dp[i - 1][j]; // only exclude
                }
            }
        }
        print(dp);
        return dp[n][W]; // final answer
    }

    public static void print(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]  + " "); 
            }
            System.out.println(); // move to next row
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int val[] = { 15, 14, 10, 45, 30 }; // values
        int wt[] = { 2, 5, 1, 3, 4 }; // weights
        int W = 7; // capacity

        System.out.println(knapsackTab(val, wt, W)); // output max profit

    }
}