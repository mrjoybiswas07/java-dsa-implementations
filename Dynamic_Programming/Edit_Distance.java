public class Edit_Distance {
    // O(n*m)
    public static int editDistance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n + 1][m + 1];

        // initialize base cases
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0) {
                    dp[i][j] = j; // insert all characters
                }
                if (j == 0) {
                    dp[i][j] = i; // delete all characters
                }
            }
        }
        // bottom-up DP filling
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                // if characters are same → no operation
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // operations: insert, delete, replace
                    int add = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    // take minimum of all operations
                    dp[i][j] = Math.min(add, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m]; // final answer
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(editDistance(word1, word2)); // Output: 5
    }
}