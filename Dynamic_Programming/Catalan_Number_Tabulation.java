public class Catalan_Number_Tabulation {
    // tc - O(n^2)
    public static int catalanTab(int n) {
        int dp[] = new int[n + 1]; // dp[i] stores ith Catalan number

        dp[0] = 1; // base case: C0 = 1
        if (n >= 1)
            dp[1] = 1; // base case: C1 = 1

        for (int i = 2; i <= n; i++) { // compute from C2 to Cn
            for (int j = 0; j < i; j++) { // split into left (j) & right (i-j-1)
                dp[i] += dp[j] * dp[i - j - 1]; // Ci = Σ (Cj * Ci-j-1)
            }
        }
        return dp[n]; // return nth Catalan number
    }

    public static void main(String[] args) {
        int n = 4; // input
        System.out.println(catalanTab(n)); // output: 14
    }
}