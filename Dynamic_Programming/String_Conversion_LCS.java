public class String_Conversion_LCS {

    // function to find LCS length
    public static int lcs(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n + 1][m + 1];

        // bottom-up DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // if characters match
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // take max of left or top
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m]; // LCS length
    }

    public static void main(String[] args) {
        String str1 = "pear";
        String str2 = "sea";

        int lcs = lcs(str1, str2);

        // formula
        int deletions = str1.length() - lcs;
        int insertions = str2.length() - lcs;

        System.out.println("Deletions: " + deletions);
        System.out.println("Insertions: " + insertions);
    }
}