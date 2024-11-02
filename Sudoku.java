import java.util.Random;
import java.util.Scanner;

/**
 * Author: Ishanvi Kaur
 * Date: November 02, 2024
 * Description: A simple Java program that generates and displays a Sudoku puzzle, 
 * allowing users to view the solution, generate a new puzzle, or quit the program.
 */
public class Sudoku {

    /**
     * Generates a complete, valid Sudoku grid by populating each cell with 
     * numbers 1-9 according to Sudoku rules. It returns a fully filled 9x9
     * Sudoku grid as a 2D integer array.
     */
    public static int[][] createFullSudokuGrid() {
        int[][] grid = new int[9][9];
        fillGrid(grid); // Fill the grid with numbers according to Sudoku rules
        return grid; // Return the completed grid
    }

    /**
     * Checks if placing a number in a specific row and column is valid according 
     * to Sudoku rules (no duplicates in row, column, or 3x3 box).
     */
    private static boolean isValid(int[][] grid, int num, int row, int col) {
        // Check if the number already exists in the row or column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num)
                return false;
        }
        // Check if the number exists in the 3x3 sub-box
        int startRow = (row / 3) * 3, startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num)
                    return false;
            }
        }
        return true;
    }

    private static boolean fillGrid(int[][] grid) {
        // Loop through each cell in the grid
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // If cell is empty (0), attempt to fill it
                if (grid[row][col] == 0) {
                    Random rand = new Random();
                    int[] numbers = rand.ints(1, 10).distinct().limit(9).toArray();
                    for (int num : numbers) {
                        // If placement is valid, place the number
                        if (isValid(grid, num, row, col)) {
                            grid[row][col] = num;
                            // Continue filling the grid
                            if (fillGrid(grid))
                                return true;
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Grid is complete with no empty cells
    }

    /**
     * Creates a Sudoku puzzle by removing numbers from a completed grid to create
     * blanks for the player to fill in.
     */
    public static int[][] createSudokuPuzzle(int[][] fullGrid) {
        int[][] puzzleGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(fullGrid[i], 0, puzzleGrid[i], 0, 9);
        }

        // Remove a specified number of cells to create the puzzle
        int cellsToRemove = 41; // Number of cells to remove
        Random rand = new Random();
        while (cellsToRemove > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (puzzleGrid[row][col] != 0) {
                puzzleGrid[row][col] = 0; // Set cell to empty
                cellsToRemove--;
            }
        }
        return puzzleGrid; // Return the puzzle grid with blanks
    }

    /**
     * Prints the given Sudoku grid to the console in a readable format.
     * Displays empty cells as '.' for clarity.
     */
    public static void printSudoku(int[][] grid) {
        System.out.println("Sudoku Grid:");
        for (int[] row : grid) {
            for (int num : row) {
                System.out.print(num == 0 ? ". " : num + " "); // Print number or '.'
            }
            System.out.println();
        }
    }

    /**
     * Main method to run the Sudoku program. Allows the user to view the puzzle, 
     * view the solution, generate a new puzzle, or quit the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Generate a fully solved Sudoku grid
        int[][] fullGrid = createFullSudokuGrid();
        // Create a puzzle version of the grid with some cells removed
        int[][] puzzleGrid = createSudokuPuzzle(fullGrid);

        while (true) {
            System.out.println("\nHere is your Sudoku puzzle:");
            printSudoku(puzzleGrid); // Display the puzzle

            System.out.println("\nOptions:");
            System.out.println("(a) Show solution");
            System.out.println("(b) Generate new puzzle");
            System.out.println("(c) Quit");
            System.out.print("Choose an option (a/b/c): ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a": // Show the solution grid
                    System.out.println("\nSolution:");
                    printSudoku(fullGrid);
                    break;
                case "b": // Generate and display a new puzzle
                    fullGrid = createFullSudokuGrid();
                    puzzleGrid = createSudokuPuzzle(fullGrid);
                    break;
                case "c": // Quit the program
                    System.out.println("Goodbye! Thank you for using the Sudoku program.");
                    scanner.close();
                    return;
                default: // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
