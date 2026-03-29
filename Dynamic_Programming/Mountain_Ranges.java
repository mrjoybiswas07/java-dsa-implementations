public class Mountain_Ranges {
    public static int mountainRanges(int n) {
        int dp[] = new int[n + 1];

        // Base cases
        dp[0] = 1; // empty range
        dp[1] = 1; // one pair

        // Start from 2 because dp[0] and dp[1] are already known
        for (int i = 2; i < n + 1; i++) {

            // j = number of pairs inside
            for (int j = 0; j < i; j++) {
                int inside = dp[j]; // left part
                int outside = dp[i - j - 1]; // right part

                // Catalan formula: Cn = Σ (Ci * Cn-i-1)
                dp[i] += inside * outside;
            }
        }

        // final answer
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4; // Expected output: 14
        System.out.println(mountainRanges(n));
    }
}