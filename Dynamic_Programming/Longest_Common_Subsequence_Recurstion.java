public class Longest_Common_Subsequence_Recurstion {
    public static int lcs(String str1, String str2, int n, int m) {

        // base case: if any string is empty
        if (n == 0 || m == 0) {
            return 0;
        }

        // if last characters match
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            // include this character
            return lcs(str1, str2, n - 1, m - 1) + 1;
        } else {
            // skip one character from either string
            int ans1 = lcs(str1, str2, n - 1, m);
            int ans2 = lcs(str1, str2, n, m - 1);

            // take maximum
            return Math.max(ans1, ans2);
        }
    }

    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg"; // LCS = "abdg", length = 4

        System.out.println(lcs(str1, str2, str1.length(), str2.length()));
    }
}
