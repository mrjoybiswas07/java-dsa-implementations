public class Longest_Common_Subsequence_Memoization {

    public static int lcs(String str1, String str2, int n, int m, int dp[][]) {

        // base case: if any string is empty
        if (n == 0 || m == 0) {
            return 0;
        }

        // if already calculated, return stored value
        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        // if last characters match
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            // include character and move both
            return dp[n][m] = 1 + lcs(str1, str2, n - 1, m - 1, dp);
        } else {
            // skip from either string
            int ans1 = lcs(str1, str2, n - 1, m, dp);
            int ans2 = lcs(str1, str2, n, m - 1, dp);

            // store and return max
            return dp[n][m] = Math.max(ans1, ans2);
        }
    }

    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg"; // LCS = "abdg", length = 4

        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n + 1][m + 1];

        // initialize dp with -1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(lcs(str1, str2, n, m, dp));
    }
}