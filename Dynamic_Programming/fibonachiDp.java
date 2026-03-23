public class fibonachiDp { // class name for Fibonacci using Dynamic Programming

    // O(n) time complexity, because each fib value is calculated only once
    public static int fib(int n, int f[]) { // function to find nth Fibonacci number using memoization

        if (n == 0 || n == 1) { // base case: fib(0)=0 and fib(1)=1
            return n; // return n directly for 0 and 1
        }

        if (f[n] != 0) { // check if fib(n) was already calculated and stored
            return f[n]; // return stored value to avoid repeated calculation
        }

        f[n] = fib(n - 1, f) + fib(n - 2, f); // compute fib(n) using recursion and store it in array
        return f[n]; // return the calculated Fibonacci value
    }

    public static void main(String[] args) { // main method: program execution starts here
        int n = 5; // find Fibonacci of 5
        int f[] = new int[n + 1]; // array to store calculated Fibonacci values from 0 to n

        System.out.println(fib(n, f)); // call fib function and print result
    }
}