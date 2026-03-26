public class Coin_Change_Unbounded_kanpsack_tabulation {

    public static int coinChange(int coins[], int sum) {
        int n = coins.length; // total coins

        int dp[][] = new int[n + 1][sum + 1]; // dp table

        // 🔹 Base Case 1: sum = 0 → always 1 way (take nothing)
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1; // 1 way to make sum 0
        }

        // 🔹 Base Case 2: no coins → cannot make positive sum
        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = 0; // 0 ways
        }

        // 🔥 Fill DP table
        for (int i = 1; i < n + 1; i++) { // coins loop
            for (int j = 1; j < sum + 1; j++) { // sum loop

                if (coins[i - 1] <= j) { // ✅ valid (can use coin)

                    // include + exclude
                    dp[i][j] = dp[i][j - coins[i - 1]] // 👉 include (stay in same row)
                            + dp[i - 1][j]; // 👉 exclude (move up)

                } else { // ❌ invalid (coin too big)

                    dp[i][j] = dp[i - 1][j]; // only exclude (move up)
                }
            }
        }

        print(dp, n, sum); // print matrix
        return dp[n][sum]; // final answer
    }

    // 🔥 Print DP table
    public static void print(int dp[][], int n, int sum) {
        System.out.println("DP Table:");

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int coins1[] = { 1, 2, 3 };
        int sum1 = 4;
        int coins2[] = { 2, 5, 3, 6 };
        int sum2=10;
        System.out.println("Total Ways = " + coinChange(coins1, sum1));
        System.out.println("Total Ways = " + coinChange(coins2, sum2));
    }
}