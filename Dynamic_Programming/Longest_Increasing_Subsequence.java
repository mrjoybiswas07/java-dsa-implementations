import java.util.Arrays;
import java.util.HashSet;

public class Longest_Increasing_Subsequence {

    // LCS function →arr1 & arr2 finds longest common subsequence between
    public static int lcs(int arr1[], int arr2[]) {
        int n = arr1.length; // length of original array
        int m = arr2.length; // length of sorted unique array

        int dp[][] = new int[n + 1][m + 1]; // DP table

        // initialize first row & column with 0 (base case)
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }

        // bottom-up DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                // if elements match → take diagonal + 1
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {
                    // else take max of skipping one element
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }

        return dp[n][m]; // final LCS length = LIS length
    }

    public static int lis(int arr1[]) {

        // step 1: store unique elements (remove duplicates)
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }

        // step 2: convert set → array
        int arr2[] = new int[set.size()];
        int i = 0;
        for (int num : set) {
            arr2[i] = num;
            i++;
        }

        // step 3: sort array (ascending order)
        Arrays.sort(arr2);

        // step 4: LIS = LCS(original array, sorted unique array)
        return lcs(arr1, arr2);
    }

    public static void main(String[] args) {
        int arr[] = { 50, 3, 10, 7, 40, 80 };

        // calling LIS function
        System.out.println(lis(arr)); // output: 4
    }
}