public class Minimum_Partitioning {
    public static int minPartition(int arr[]) {
        int n = arr.length;

        // total sum of array
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        int W = sum / 2; // target

        int dp[][] = new int[n + 1][W + 1];

        // bottom-up knapsack
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {

                if (arr[i - 1] <= j) { // can include
                    int include = arr[i - 1] + dp[i - 1][j - arr[i - 1]];
                    int exclude = dp[i - 1][j];

                    dp[i][j] = Math.max(include, exclude); // take best
                } else {
                    dp[i][j] = dp[i - 1][j]; // skip item
                }
            }
        }

        int sum1 = dp[n][W]; // best subset sum
        int sum2 = sum - sum1; // remaining sum

        return Math.abs(sum1 - sum2); // min difference
    }

    public static void main(String[] args) {
        int nums[] = { 1, 6, 11, 5 };
        System.out.println(minPartition(nums)); // output: 1
    }
}