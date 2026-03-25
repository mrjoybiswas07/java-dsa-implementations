public class Unbounded_Knapsack_Tabulation { // Class for Unbounded Knapsack using Tabulation

    // O(n * W) → n items, W capacity
    public static int unboundedKnapsack(int val[], int wt[], int W) { // Function to return max profit

        int n = val.length; // Total number of items
        int dp[][] = new int[n + 1][W + 1]; // DP table → rows = items, cols = capacity

        // 🔹 Base Case 1: Capacity = 0 → profit = 0
        for (int i = 0; i <= n; i++) { // for all items
            dp[i][0] = 0; // no capacity → no profit
        }

        // 🔹 Base Case 2: Items = 0 → profit = 0
        for (int j = 0; j <= W; j++) { // for all capacities
            dp[0][j] = 0; // no items → no profit
        }

        // 🔹 Fill DP Table
        for (int i = 1; i <= n; i++) { // iterate over items (1 to n)
            for (int j = 1; j <= W; j++) { // iterate over capacity (1 to W)

                if (wt[i - 1] <= j) { // if current item's weight fits in capacity

                    // 🔥 INCLUDE case (Unbounded → can reuse same item)
                    int include = val[i - 1] + dp[i][j - wt[i - 1]];
                    // take item → value + remaining capacity (same row i)

                    // ❌ EXCLUDE case (skip item)
                    int exclude = dp[i - 1][j];
                    // move to previous item

                    dp[i][j] = Math.max(include, exclude); // take best of both

                } else {
                    // ❌ Cannot include item (too heavy)
                    dp[i][j] = dp[i - 1][j]; // just exclude
                }
            }
        }
        print(dp);
        return dp[n][W]; // final answer → all items considered, full capacity
    }

    public static void print(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) { // FIX HERE
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) { // Driver code

        int val[] = { 15, 14, 10, 45, 30 }; // values of items
        int wt[] = { 2, 5, 1, 3, 4 }; // weights of items
        int W = 7; // knapsack capacity

        System.out.println(unboundedKnapsack(val, wt, W)); // print max profit
    }
}