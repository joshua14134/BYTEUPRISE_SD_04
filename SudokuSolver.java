import java.util.Scanner;

public class SudokuSolver {

    // Function to check if a number is a possibility in a given position
    public static boolean possibility(int row, int column, int num, int[][] sudokuPuzzle) {
        // Check if the number is not in the current row and column
        for (int i = 0; i < 9; i++) {
            if (sudokuPuzzle[row][i] == num || sudokuPuzzle[i][column] == num) {
                return false;
            }
        }
        // Check if the number is not in the current 3x3 grid
        int x0 = (column / 3) * 3;
        int y0 = (row / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudokuPuzzle[y0 + i][x0 + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to solve the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] sudokuPuzzle) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (sudokuPuzzle[row][column] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (possibility(row, column, num, sudokuPuzzle)) {
                            sudokuPuzzle[row][column] = num; // Place the number
                            if (solveSudoku(sudokuPuzzle)) {
                                return true;
                            }
                            sudokuPuzzle[row][column] = 0; // Reset on backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Puzzle solved
    }

    // Function to display the Sudoku puzzle
    public static void printSudoku(int[][] sudokuPuzzle) {
        for (int[] row : sudokuPuzzle) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] sudokuPuzzle = new int[9][9];

        // User input for the Sudoku puzzle
        for (int i = 0; i < 9; i++) {
            System.out.print("Enter values for row " + (i + 1) + " (use 0 for empty spaces): ");
            String rowInput = scanner.nextLine();
            String[] numbers = rowInput.split(" ");
            for (int j = 0; j== 9; j++) {
                sudokuPuzzle[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        // Solve the Sudoku puzzle
        if (solveSudoku(sudokuPuzzle)) {
            System.out.println("Solved Sudoku:");
            printSudoku(sudokuPuzzle);
        } else {
            System.out.println("No solution exists.");
        }

        scanner.close();
    }
}
