public class FloodFill_algo {

    // O(m*n) - visits each pixel at most once
    public void helper(int[][] image, int sr, int sc, int color, boolean[][] vis, int orgCol) {

        // Base case: out of bounds check
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length)
            return;

        // Skip if already visited or not the original color
        if (vis[sr][sc] || image[sr][sc] != orgCol)
            return;

        vis[sr][sc] = true; // Mark current cell as visited
        image[sr][sc] = color; // BUG FIX 3: Actually paint the pixel with new color

        helper(image, sr, sc - 1, color, vis, orgCol); // Explore left
        helper(image, sr, sc + 1, color, vis, orgCol); // Explore right
        helper(image, sr - 1, sc, color, vis, orgCol); // Explore up
        helper(image, sr + 1, sc, color, vis, orgCol); // Explore down
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        boolean[][] vis = new boolean[image.length][image[0].length]; // Track visited cells
        helper(image, sr, sc, color, vis, image[sr][sc]); // Start DFS from (sr, sc)
        return image; // Return modified image
    }

    public static void main(String[] args) {
        FloodFill_algo obj = new FloodFill_algo();

        // Test case from the problem
        int[][] image = {
                { 1, 1, 1 }, // Row 0
                { 1, 1, 0 }, // Row 1
                { 1, 0, 1 } // Row 2
        };

        int sr = 1, sc = 1, color = 2; // Start at (1,1), fill with color 2

        int[][] result = obj.floodFill(image, sr, sc, color);

        // Print the result grid
        System.out.println("Result after Flood Fill:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " "); // Print each pixel value
            }
            System.out.println(); // New line after each row
        }
        // Expected Output:
        // 2 2 2
        // 2 2 0
        // 2 0 1
    }
}