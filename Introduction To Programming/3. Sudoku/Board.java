/*
* Author: Spyros Gavriil
* Written: 26/11/2023
* Last updated: 26/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class in responsible for initializing our sudoku puzzle with the given size N. It takes the file name from our main Sudoku class 
* constructs the tableau, while checking for any errors in our given file (same row, column, box). IF everything is correct it fills in
* the tableau array and prints our Sudoku on screen for the user to play
*
*/
public class Board {

	private int N; // Sudoku size
	private int[][] tableau; // Sudoku board

	// Default constructor 
	public Board() {
	    N = 9; // Default size is 9x9 for a standard Sudoku board
	    tableau = new int[N][N]; // Initialize the Sudoku board with the default size
	}

	// Constructor with input
	public Board(int N) {
	    this.N = N; // Set the Sudoku size to the specified value
	    tableau = new int[N][N]; // Initialize the Sudoku board with the specified size
	}

	// Method to check whether the sudoku is valid
	public static boolean checkValidity(int[][] tableau, int N) {
	    // Check for duplicate values in rows
	    if (checkRows(tableau, N)) {
	        System.out.println("This is not a valid sudoku! Same row rule not met!");
	        return false;
	    } 
	    // Check for duplicate values in columns
	    else if (checkCols(tableau, N)) {
	        System.out.println("This is not a valid sudoku! Same column rule not met!");
	        return false;
	    } 
	    // Check for duplicate values in boxes
	    else if (checkBox(tableau, N)) {
	        System.out.println("This is not a valid sudoku! Same box rule not met!");
	        return false;
	    }
	    // If all checks pass, the sudoku is valid
	    return true;
	}

	// Method that checks the validity of the sudoku's rows
	public static boolean checkRows(int[][] tableau, int N) {
	    // Iterate over each row in the tableau
	    for (int i = 0; i < N; i++) {
	        // Iterate over each pair of columns in the row
	        for (int j = 0; j < N; j++) {
	            for (int k = 0; k < N; k++) {
	                // Check if the values in the current and other column are duplicates
	                if (j != k && tableau[i][j] != 0 && Math.abs(tableau[i][j]) == Math.abs(tableau[i][k])) {
	                    // Found a duplicate in the row, return true
	                    return true;
	                }
	            }
	        }
	    }
	    // No duplicates found in any row, return false
	    return false;
	}

	// Method that checks the validity of the sudoku's columns
	public static boolean checkCols(int[][] tableau, int N) {
	    // Iterate over each column in the tableau
	    for (int j = 0; j < N; j++) {
	        // Iterate over each pair of rows in the column
	        for (int i = 0; i < N; i++) {
	            for (int k = 0; k < N; k++) {
	                // Check if the values in the current and other row are duplicates
	                if (i != k && tableau[i][j] != 0 && Math.abs(tableau[i][j]) == Math.abs(tableau[k][j])) {
	                    // Found a duplicate in the column, return true
	                    return true;
	                }
	            }
	        }
	    }
	    // No duplicates found in any column, return false
	    return false;
	}

	// Method that check the validity of the sudoku's boxes
	public static boolean checkBox(int[][] tableau, int N) {
		for (int i = 0; i < N; i += Math.sqrt(N)) { // Increase the row index by the square root of our Sudoku size
			for (int j = 0; j < N; j += Math.sqrt(N)) { // Increase the column index by the square root of our Sudoku size
				int[] array = new int[N];
				int index = 0; // Index of the new 1d array
				
				// Fill the new 1d array with the absolute values of our normal Sudoku
				for (int k = i; k < i + Math.sqrt(N); k++) {
					for (int z = j; z < j + Math.sqrt(N); z++) {
						array[index] = Math.abs(tableau[k][z]);
						index++;
					}
				}
				for (int k = 0; k < array.length; k++) { // Loop through the array
					int n = array[k];
					if (n != 0) {
						for (int z = k + 1; z < array.length; z++) { // Check number with other numbers in box
							if (n == array[z]) {
								return true; // Found a duplicate in the box
							}
						}
					}
				}
			}

		}
		return false;
	}

	// Method to handle the input from our file
	public void readBoard(String file, int N) {
	    // Create a new In object to read from the specified file
	    In inFile = new In(file);
	    
	    // Counter for the number of lines read from the file
	    int cntLines = 0;

	    // Loop through each line in the file
	    while (inFile.hasNextLine()) {
	        // Read the current line
	        String line = inFile.readLine();
	        cntLines++;
	        
	        // Flag to check if the values in the line are within bounds
	        boolean check = true;

	        // Split the line into an array of strings using whitespace as delimiter
	        String[] values = line.split("\\s+");

	        // Loop through each value in the line
	        for (int i = 0; i < values.length && check; i++) {
	            // Convert the string value to an integer
	            int value = Integer.parseInt(values[i]);

	            // Check if the value is within the valid range [-N, N]
	            if (value < -N || value > N) {
	                check = false;
	            }
	        }

	        // Check for the number of values in the line
	        if (values.length < N) {
	            System.out.println("Error: Missing values from file!");
	            System.exit(0);
	        } else if (values.length > N) {
	            System.out.println("Error: Illegal number in input file!");
	            System.exit(0);
	        // Check if a number is out of bounds
	        } else if (!check) {
	            System.out.println("A number is out of bounds");
	            System.exit(0);
	        } // Check for the number of lines in the file
	         else if (cntLines > N) {
		        System.out.println("Error: Illegal number in input file!");
		        System.exit(0);
		    }
	        else {
	            // If all checks pass, store the values in the tableau array
	            int row = cntLines - 1;
	            for (int i = 0; i < values.length; i++) {
	                tableau[row][i] = Integer.parseInt(values[i]);
	            }
	        }
	    }

	    // Check for the number of lines in the file
	    if (cntLines < N) {
	        System.out.println("Error: Missing values from file!");
	        System.exit(0);
	    } 

	    // Check the validity of the tableau using a separate method
	    if (!checkValidity(tableau, N)) {
	        System.exit(0);
	    } else {
	        // If all checks pass, close the file and return
	        inFile.close();
	        return;
	    }
	}

	// Method to handle the Sudoky display
	public void displayBoard(int[][] tableau) {
	    int size = tableau.length; // Get the size of the Sudoku board
	    int limit = (int) Math.sqrt(N); // Calculate the square root of N to determine block limits

	    // Iterate through each row
	    for (int i = 0; i < size; i++) {
	        // Draw horizontal lines between blocks
	        if (i % limit == 0) {
	            for (int k = 0; k < limit; k++) {
	                System.out.print("+");
	                System.out.print("---".repeat((int) Math.sqrt(N))); // Draw block separator
	            }
	            System.out.println("+");
	        }

	        // Iterate through each column in the row
	        for (int j = 0; j < size; j++) {
	            // Draw vertical lines between blocks
	            if (j % limit == 0) {
	                System.out.print("|");
	            }

	            // Display the Sudoku cell content
	            if (tableau[i][j] < 0) {
	                System.out.print("(" + Math.abs(tableau[i][j]) + ")");
	            } else if (tableau[i][j] == 0) {
	                System.out.print(" . ");
	            } else {
	                System.out.print(" " + tableau[i][j] + " ");
	            }

	            // Draw vertical lines at the end of each row
	            if (j + 1 == size) {
	                System.out.println("|");
	            }
	        }

	        // Draw horizontal lines at the end of the board
	        if (i + 1 == size) {
	            for (int k = 0; k < limit; k++) {
	                System.out.print("+");
	                System.out.print("---".repeat((int) Math.sqrt(N))); // Draw block separator
	            }
	            System.out.println("+");
	        }
	    }
	}
	
	// Getter method for the Sudoku board
	public int[][] getBoard() {
	    return tableau;
	}

}
	


