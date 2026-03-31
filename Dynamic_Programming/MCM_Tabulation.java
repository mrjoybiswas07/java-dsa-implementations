public class MCM_Tabulation {
    public static int mcmTab(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];

        // initialization: cost of single matrix = 0
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // bottom-up approach (length of chain)
        for (int len = 2; len <= n - 1; len++) {
            for (int i = 1; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // try all possible partitions
                for (int k = i; k <= j - 1; k++) {
                    int cost1 = dp[i][k]; // left part
                    int cost2 = dp[k + 1][j]; // right part
                    int cost3 = arr[i - 1] * arr[k] * arr[j]; // multiplication cost

                    // take minimum cost
                    dp[i][j] = Math.min(dp[i][j], cost1 + cost2 + cost3);
                }
            }
        }

        print(dp); // print dp table
        return dp[1][n - 1]; // final answer
    }

    public static void print(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 3 };

        System.out.println(mcmTab(arr));
    }
}