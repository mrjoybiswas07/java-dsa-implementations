public class zero_one_Kanapsack_recursion { // class name for 0/1 knapsack using recursion

    public static int knapsack(int val[], int wt[], int W, int n) { // method to solve knapsack problem
        if (W == 0 || n == 0) { // base case: if capacity is 0 or no items left
            return 0; // no profit can be taken
        }

        if (wt[n - 1] <= W) { // check if current item weight is less than or equal to remaining capacity
            // include
            int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1); // include current item and solve for
                                                                             // remaining capacity/items
            // exclude
            int ans2 = knapsack(val, wt, W, n - 1); // exclude current item and solve for remaining items

            return Math.max(ans1, ans2); // return maximum profit from include and exclude cases

        } else { // if current item weight is greater than remaining capacity
            return knapsack(val, wt, W, n - 1); // cannot include item, so skip it
        }
    }

    public static void main(String[] args) { // main method starts program execution
        int val[] = { 15, 14, 10, 45, 30 }; // values (profits) of items
        int wt[] = { 2, 5, 1, 3, 4 }; // weights of items
        int W = 7; // maximum capacity of knapsack
        System.out.println(knapsack(val, wt, W, val.length)); // call knapsack function and print result
    }
}