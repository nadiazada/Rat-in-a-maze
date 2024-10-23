public class RatMaze {
    final int N;  // Maze size

    // Constructor that initializes the maze size
    public RatMaze(int size) {
        N = size;
    }

    // This prints the path found by the algorithm with 1s indicating the solution path
    void printSolution(int solution[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean isSafe(int maze[][], int x, int y) {
        // Return true if the current cell is within bounds and is open (represented by 1)
        return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
    }


    boolean solveMaze(int maze[][]) {
        // Solution matrix initialized to zero. It will mark the path taken by the algorithm.
        int[][] solution = new int[N][N];

        // Start the recursive backtracking from the top-left corner (0, 0)
        if (solveMazehelper(maze, 0, 0, solution) == false) {
            System.out.println("No solution exists for the maze.");
            return false;  // Return false if no path is found
        }

        // If a solution is found, print the solution matrix
        printSolution(solution);  
        return true;
    }

    /*
      Algorithm:
      1. Mark the current cell as part of the solution path.
      2. Try moving right (x direction). If that works, return true.
      3. If moving right fails, try moving down (y direction). If that works, return true.
      4. If both directions fail, backtrack: unmark the current cell and return false.
     */
    boolean solveMazehelper(int maze[][], int x, int y, int solution[][]) {
        // Base case: if (x, y) is the bottom-right corner, the maze is solved
        if (x == N - 1 && y == N - 1) {
            solution[x][y] = 1;  // Mark the final cell as part of the solution
            return true;
        }

        if (isSafe(maze, x, y)) {
=            solution[x][y] = 1;

            // Recursive Case: Try moving forward in the x direction (rightward) */
            if (solveMazehelper(maze, x + 1, y, solution))
                return true;  // If moving right leads to a solution, return true

            // If moving right doesn't lead to a solution, try moving down in the y direction */
            if (solveMazehelper(maze, x, y + 1, solution))
                return true;  // If moving down leads to a solution, return true

            //If neither right nor down works, BACKTRACK:
            
            solution[x][y] = 0;
            return false;  // Return false to signal that this path is not valid
        }

        return false;  // Return false if the current cell is not valid
    }

    public static void main(String args[]) {
        // Test Case 1
        System.out.println("Test 1: Easy Maze");
        int[][] easyMaze = {
            { 1, 0, 1, 0 },
            { 1, 1, 0, 1 },
            { 0, 1, 0, 0 },
            { 0, 1, 1, 1 }
        };
        RatMaze easy = new RatMaze(4);
        easy.solveMaze(easyMaze);

        // Test Case 2
        System.out.println("\nTest 2: Hard Maze");
        int[][] hardMaze = {
            { 1, 0, 0, 0, 1, 1 },
            { 1, 1, 0, 0, 1, 0 },
            { 0, 1, 1, 1, 1, 0 },
            { 0, 0, 1, 1, 0, 0 },
            { 0, 0, 0, 1, 1, 0 },
            { 1, 1, 0, 0, 1, 1 }
        };
        RatMaze hard = new RatMaze(6);
        hard.solveMaze(hardMaze);
    }
}
