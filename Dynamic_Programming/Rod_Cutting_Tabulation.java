public class Rod_Cutting_Tabulation {

    // length[] = pieces size, price[] = value, totRod = total rod length
    public static int rodCutting(int length[], int price[], int totRod) {

        int n = price.length; // total number of pieces available

        int dp[][] = new int[n + 1][totRod + 1];
        // dp[i][j] = max profit using first i pieces to make rod length j

        // base case initialization
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < totRod; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    // if no piece OR rod length 0 → profit = 0
                }
            }
        }

        // fill dp table
        for (int i = 1; i < n + 1; i++) { // loop over pieces
            for (int j = 1; j < totRod + 1; j++) { // loop over rod length

                // valid case → current piece can be used
                if (length[i - 1] <= j) {

                    dp[i][j] = Math.max(
                            price[i - 1] + dp[i][j - length[i - 1]],
                            // include → take piece again (unbounded)

                            dp[i - 1][j]
                    // exclude → skip current piece
                    );
                }

                // invalid case → piece too big
                else {
                    dp[i][j] = dp[i - 1][j];
                    // just skip current piece
                }
            }
        }

        print(dp); // print dp table for understanding

        return dp[n][totRod]; // final answer
    }

    // function to print dp table
    public static void print(int dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " "); // print each cell
            }
            System.out.println(); // next row
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int length[] = { 1, 2, 3, 4, 5, 6, 7, 8 }; // piece lengths
        int price[] = { 1, 5, 8, 9, 10, 17, 17, 20 }; // corresponding prices

        int totRod = 8; // total rod length

        System.out.println(rodCutting(length, price, totRod));
        // print maximum profit
    }
}