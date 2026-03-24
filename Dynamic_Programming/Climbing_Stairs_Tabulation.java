public class Climbing_Stairs_Tabulation {

    public static int countWaysTab(int n) {
        int dp[] = new int[n + 1]; // create dp array from 0 to n

        dp[0] = 1; // base case: 1 way to stay at 0

        for (int i = 1; i <= n; i++) { // fill dp from 1 to n
            if (i == 1) {
                dp[i] = dp[i - 1] + 0; // only one way to reach stair 1
            } else {
                dp[i] = dp[i - 1] + dp[i - 2]; // ways from 1 step + 2 step back
            }
        }

        return dp[n]; // final answer
    }

    public static void main(String[] args) {
        int n = 5; // target stair
        System.out.println(countWaysTab(n)); // print number of ways
    }
}