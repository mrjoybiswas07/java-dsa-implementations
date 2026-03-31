import java.util.*;

public class Minimum_Array_jump {

    public static int minJumps(int nums[]) {
        int n = nums.length;

        int dp[] = new int[n];
        Arrays.fill(dp, -1); // initialize all as unreachable

        dp[n - 1] = 0; // last index needs 0 jumps

        // traverse from right to left
        for (int i = n - 2; i >= 0; i--) {

            int steps = nums[i]; // max jump length from i
            int ans = Integer.MAX_VALUE; // store minimum jumps

            // try all reachable positions
            for (int j = i + 1; j <= i + steps && j < n; j++) {

                if (dp[j] != -1) { // if reachable
                    ans = Math.min(ans, dp[j] + 1); // take min jumps
                }
            }

            // update dp if valid path found
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }

        return dp[0]; // answer from starting index
    }

    public static void main(String[] args) {
        int nums[] = { 2, 3, 1, 1, 4 };
        System.out.println(minJumps(nums)); // output: 2
    }
}