public class Wildcard_Matching_Tabulation {

    public static boolean isMatch(String s, String p) {

        int n = s.length(); // length of string
        int m = p.length(); // length of pattern

        boolean dp[][] = new boolean[n + 1][m + 1]; // DP table

        // initialize
        dp[0][0] = true; // empty string matches empty pattern

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = false; // non-empty string can't match empty pattern
        }

        for (int j = 1; j < m + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1]; // '*' can match empty string
            }
        }

        // bottom-up DP
        for (int i = 1; i < n + 1; i++) { // loop over string
            for (int j = 1; j < m + 1; j++) { // loop over pattern

                // case 1: characters match OR '?'
                if (s.charAt(i - 1) == p.charAt(j - 1)
                        || p.charAt(j - 1) == '?') {

                    dp[i][j] = dp[i - 1][j - 1]; // move diagonally

                } 
                // case 2: '*'
                else if (p.charAt(j - 1) == '*') {

                    dp[i][j] = dp[i - 1][j] // '*' matches multiple chars
                            || dp[i][j - 1]; // '*' matches empty

                }
                // case 3: no match
                else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m]; // final answer
    }

    public static void main(String[] args) {

        String s = "baaabab";
        String p = "*****ba*****ab"; // expected: true

        System.out.println(isMatch(s, p));
    }
}