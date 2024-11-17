/*
* Author: Spyros Gavriil
* Written: 26/11/2023
* Last updated: 26/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class is responsible for the main gameplay of our Sudoku game. It takes the size of the Sudoku N, and the file name as command
* line arguments, checks for any errors in our input, and if everything is valid, the game starts. The user is faced with out Sudoku 
* puzzle on screen and he can add values to it, remove and end the game whenever he wants. If our user decides to exit the game we save 
* his progress in a file, and we exit the program.
*
*/
public class Sudoku {

	public static int N; // Sudoku size

	// Method to play the Sudoku game
	public static void play(int N, String file, Board sudoku) {
	    UserChoice choice = new UserChoice();
	    boolean solved = false; // Flag to check if the Sudoku is solved

	    // Continue the game until it's solved
	    while (!solved) {
	        sudoku.displayBoard(sudoku.getBoard()); // Print the Sudoku board
	        System.out.println("Enter your command in the following format:");
	        System.out.println("+ i,j=val: for entering val at position (i,j)");
	        System.out.println("+ i,j=0 : for clearing cell (i,j)");
	        System.out.println("+ 0,0=0 : for saving and ending the game");
	        System.out.println("Notice: i, j, val numbering is from [1.." + N + "]");
	        System.out.print('>');

	        // Get the user input and parse the commands
	        getUserInput(choice);
	        int row = choice.getRow();
	        int col = choice.getColumn();
	        int val = choice.getValue();

	        // Handle wrong inputs by asking for a new one
	        if (row == -1 && col == -1 && val == -1) {
	            continue;
	        } 

	        // Handle valid input
	        else {
	            if (row == 0 && col == 0 && val == 0) {
	                // Save the game to a file and end the game
	                Out outFile = new Out("out-" + file);
	                for (int i = 0; i < sudoku.getBoard().length; i++) {
	                    for (int j = 0; j < sudoku.getBoard().length; j++) {
	                        outFile.print(sudoku.getBoard()[i][j] + " ");
	                    }
	                    outFile.println();
	                }
	                System.out.println("Saving game to out-" + file + "\nbye!");
	                outFile.close(); // Close the outfile
	                return;
	            } else if (val == 0) {
	                // Clear the value in the specified cell
	                row--;
	                col--;
	                if (sudoku.getBoard()[row][col] < 0) {
	                    System.out.println("Error: cell is already occupied!");
	                } else {
	                    sudoku.getBoard()[row][col] = 0;
	                    System.out.println("Value cleared!");
	                }
	            } else {
	                // Insert the value in the specified cell
	                row--;
	                col--;
	                if (sudoku.getBoard()[row][col] != 0) {
	                    System.out.println("Error: cell is already occupied!");
	                } else {
	                    int temp = sudoku.getBoard()[row][col];
	                    sudoku.getBoard()[row][col] = val;

	                    // Check if the insertion violates Sudoku rules
	                    if (Board.checkRows(sudoku.getBoard(), N)) {
	                        System.out.println("Error: Illegal value insertion! Same row rule not met!");
	                        sudoku.getBoard()[row][col] = temp;
	                    } else if (Board.checkCols(sudoku.getBoard(), N)) {
	                        System.out.println("Error: Illegal value insertion! Same column rule not met!");
	                        sudoku.getBoard()[row][col] = temp;
	                    } else if (Board.checkBox(sudoku.getBoard(), N)) {
	                        System.out.println("Error: Illegal value insertion! Same box rule not met!");
	                        sudoku.getBoard()[row][col] = temp;
	                    } else {
	                        System.out.println("Value inserted!");
	                    }
	                }
	            }
	        }

	        // Check if the Sudoku is solved
	        solved = true;
	        int[][] checkPuzzle = sudoku.getBoard();
	        for (int i = 0; i < checkPuzzle.length && solved; i++) {
	            for (int j = 0; j < checkPuzzle.length && solved; j++) {
	                if (checkPuzzle[i][j] == 0) {
	                    solved = false; // If 0 is found, sudoku is not solved
	                }
	            }
	        }
	    }

	    // Game completed
	    System.out.println("Game completed!!!");
	    sudoku.displayBoard(sudoku.getBoard());
	}


	// Method to get user input and validate the format
	public static void getUserInput(UserChoice choice) {
	    // Read a line of input from the user
	    String input = StdIn.readLine();

	    // Check for spaces in the input
	    for (int i = 0; i < input.length(); i++) {
	        if (input.charAt(i) == ' ') {
	            System.out.println("Error: wrong format of command!");
	            choice.setChoice(-1, -1, -1); // Set a default choice with error values
	            return;
	        }
	    }

	    // Validate the format of the input
	    if (input.charAt(1) != ',' || input.charAt(3) != '=') {
	        System.out.println("Error: wrong format of command!");
	        choice.setChoice(-1, -1, -1); // Set a default choice with error values
	    } else {
	        // Extract row, column, and value from the input
	        int row = (int) (input.charAt(0) - '0');
	        int col = (int) (input.charAt(2) - '0');
	        int val = (int) (input.charAt(4) - '0');

	        // Check if any number is outside the Sudoku bounds
	        if (row < 0 || row > N || col < 0 || col > N || val < 0 || val > N) {
	            System.out.println("Error: i, j, or val are outside the allowed range [1.." + N + "]");
	            choice.setChoice(-1, -1, -1); // Set a default choice with error values
	            return;
	        } else {
	            // Set the user choice with valid values
	            choice.setChoice(row, col, val);
	        }
	    }
	}


	public static void main(String[] args) {
	    // Check if the correct number of command-line arguments is provided
	    if (args.length != 2) {
	        System.out.println("Please give the dimension N followed by a <game-file> as the only 2 arguments");
	        return;
	    }

	    // Parse the first command-line argument to get the dimension N
	    N = Integer.parseInt(args[0]);

	    // Check if the value of N is either 4 or 9
	    if (N != 4 && N != 9) {
	        System.out.println("The allowed value for N is either 4 or 9!\n" +
	                "Please re-run the program with a valid value for N");
	        return;
	    }

	    // Parse the second command-line argument to get the game file
	    String file = args[1];

	    // Create a new Sudoku board with the specified dimension
	    Board sudoku = new Board(N);

	    // Read the Sudoku board from the file
	    sudoku.readBoard(file, N);

	    // Start playing the Sudoku game
	    play(N, file, sudoku);
	}

	}


