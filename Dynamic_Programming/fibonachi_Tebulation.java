public class fibonachi_Tebulation { // class name

    public static int fibTabulation(int n) { // method to find nth Fibonacci number using tabulation
        int dp[] = new int[n + 1]; // create DP array from index 0 to n
        dp[0] = 0; // base case: Fibonacci of 0 is 0
        dp[1] = 1; // base case: Fibonacci of 1 is 1

        for (int i = 2; i <= n; i++) { // start loop from 2 and calculate up to n
            dp[i] = dp[i - 1] + dp[i - 2]; // current Fibonacci = previous two Fibonacci numbers
        }

        return dp[n]; // return the nth Fibonacci value stored in dp[n]
    }

    public static void main(String[] args) { // main method starts program execution
        int n = 5; // find Fibonacci of 5
        System.out.println(fibTabulation(n)); // call method and print result
    }
}