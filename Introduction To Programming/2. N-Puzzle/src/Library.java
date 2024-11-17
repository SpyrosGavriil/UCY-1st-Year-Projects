

public class Library {

//	Find 0 in the puzzle
	public static int[] indexZero(int arr[][]) {
		int a[] = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 0) {
					a[0] = i;
					a[1] = j;
					break;
				}
			}
		}
		return a;
	}

//  Function that initialises our puzzle solved
	public static int[][] initializePuzzle(int N) {
		int rows = N, cols = N;

		int array[][] = new int[rows][cols];
		int num = 1;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols && num < Math.pow(N, 2); j++) {
				array[i][j] = num;
				num++;
			}
		}
		return array;
	}

//	Function that displays the NPuzzle text-based
	public static void displayPuzzle(int array[][]) {
		int size = array.length;
		String format = "";
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				System.out.print("+-----");
			}
			System.out.println("+");
			for (int j = 0; j < size; j++) {
				if (array[i][j] > 99) {
					format = String.format("%4d", array[i][j]);
					System.out.print("!" + format + " ");
				} else {
					format = String.format("%3d", array[i][j]);
					System.out.print("!" + format + "  ");
				}
			}
			System.out.println("!");
		}
		for (int i = 0; i < size; i++) {
			System.out.print("+-----");
		}
		System.out.print("+");
	}

//	Function that takes the moves as input
	public static String getUserCommand(int cnt, String suffix) {
		System.out.println("Give " + cnt + suffix + " Move: ");
		String move = StdIn.readString();
		return move;
	}

//	Function for interactive mode
	public static void play(int N, int NPuzzle[][]) {
		int cnt = 1;
		int size = NPuzzle.length, rowOfZero = -1, colOfZero = -1;
		boolean solved = false;
		while (!solved) { // Check whether the puzzle is solved
			String suffix;
			if (cnt == 1) {
				suffix = "st";
			} else if (cnt == 2) {
				suffix = "nd";
			} else if (cnt == 3) {
				suffix = "rd";
			} else {
				suffix = "th";
			}
			int arr[] = indexZero(NPuzzle);
			rowOfZero = arr[0]; // x coordinate of 0
			colOfZero = arr[1]; // y coordinate of 0
			String move = getUserCommand(cnt, suffix); // Get the next move from the user
			if (move.equals("e")) { // If user exited the puzzle without solving it, print error message
				System.out.println("*******************************");
				System.out.println("You didn't solve the puzzle, maybe try again");
				break;
			} else { 
				while (!isValidMove(move, rowOfZero, colOfZero, N)) { // While the move is out of bounds, print error messages and ask for new input
					if (move.equals("e")) { // If the move given is exit, break from the loop
						break;
					} else if (move.equals("d")) {
						System.out.println("You are not allowed to go down");
						System.out.println("*******************************");
					} else if (move.equals("u")) {
						System.out.println("You are not allowed to go up");
						System.out.println("*******************************");
					} else if (move.equals("l")) {
						System.out.println("You are not allowed to go left");
						System.out.println("*******************************");
					} else if (move.equals("r")) {
						System.out.println("You are not allowed to go right");
						System.out.println("*******************************");
					}
					displayPuzzle(NPuzzle); // Print the puzzle
					System.out.println("\n");
					move = getUserCommand(cnt, suffix); // Ask for new move
				} // Exit when move is valid
				System.out.println("*******************************");
				if (move.equals("e")) {
					break;
				} else { // Swap the numbers 
					NPuzzle = swapNumbers(NPuzzle, move, rowOfZero, colOfZero);
				}

				displayPuzzle(NPuzzle); // Print the updated puzzle
				if (isSolution(NPuzzle)) { // Check if the puzzle is solved and print messages
					solved = true;
					System.out.println();
					System.out.println("Congratulations, puzzle solved!!");
					System.out.println("Moves needed: " + cnt);
				}
				System.out.println("\n");
				cnt++; // If the puzzle is not solved, add a move
			}
		}
	}

	public static String mode() {
		System.out.println("+-------------------------+");
		System.out.println("|1. Interactive play      |");
		System.out.println("|2. Automatic play        |");
		System.out.println("|3. Exit                  |");
		System.out.println("+-------------------------+");
		System.out.print("Choose Option:");
		String mode = StdIn.readString();
		System.out.println("*******************************");
		return mode;
	}

//	Function that checks if a move is valid
	public static boolean isValidMove(String move, int row, int col, int N) {
		int moveIndex = 0; // Variable to store where the 0 wants to go

		if (move.equals("l")) {
			moveIndex = col - 1;
		} else if (move.equals("r")) {
			moveIndex = col + 1;
		} else if (move.equals("u")) {
			moveIndex = row - 1;
		} else if (move.equals("d")) {
			moveIndex = row + 1;
		} else if (move.equals("e")) {
			System.out.println("*******************************");
			System.out.println("You didn't solve the puzzle, maybe try again");
		} else { // If anything else is typed, print error message
			System.out.println("Wrong input, expected input is (l, u, r, d, e)");
			return false;
		}

		if (moveIndex < 0 || moveIndex == N) { // If the move is out of bounds, return false
			return false;
		}
		return true;
	}

//	Function that checks if the puzzle given is solved
	public static boolean isSolution(int NPuzzle[][]) {
		int size = NPuzzle.length;
		int previousValue = NPuzzle[0][0]; // Variable that stores the previous value depending on the index
		if (NPuzzle[size - 1][size - 1] != 0) { // If 0 is not in the bottom right corner, then puzzle isn't solved
			return false;
		} else { // Algorithm that checks whether the puzzle is sorted in ascennding order
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					int currentValue = NPuzzle[i][j];
					if (currentValue == 0) {
						return true;
					} else if (currentValue < previousValue) { 
						return false; // The puzzle is not sorted in ascending order
					}
					previousValue = currentValue;
				}
			}
		}
		return true;
	}

//	Function that takes the available moves of 0, splits the space [0,1), and returns the index of the move to execute
	public static int moveSelection(String str) {
		double num = Math.random(); // Generate random number 
		int size = str.length();
		double array[] = new double[size - 1]; // Array to store the spaces
		if (size == 4) { // If available moves are 4, then split spaces with 0.25 difference each
			for (int i = 0; i < size - 1; i++) {
				array[i] = ((int) ((1.0 / size) * 100) / 100.0) * (i + 1);
			}
		} else { // Split spaces depending on available moves
			for (int i = 0; i < size - 1; i++) {
				array[i] = ((int) ((1.0 / size) * 10) / 10.0) * (i + 1);
			}
		}

		for (int i = 0; i < array.length; i++) { // Find the move to execute and return the index of the move
			if (num <= array[i]) {
				return i;
			}
		}
		return size - 1; // If move isn't found then return the last index of the available moves

	}

//	Function that swaps the numbers based on the input
	public static int[][] swapNumbers(int NPuzzle[][], String move, int rowOfZero, int colOfZero) {
		if (move.equals("d")) {
			int temp = NPuzzle[rowOfZero + 1][colOfZero];
			NPuzzle[rowOfZero + 1][colOfZero] = 0;
			NPuzzle[rowOfZero][colOfZero] = temp;
		} else if (move.equals("u")) {
			int temp = NPuzzle[rowOfZero - 1][colOfZero];
			NPuzzle[rowOfZero - 1][colOfZero] = 0;
			NPuzzle[rowOfZero][colOfZero] = temp;
		} else if (move.equals("l")) {
			int temp = NPuzzle[rowOfZero][colOfZero - 1];
			NPuzzle[rowOfZero][colOfZero - 1] = 0;
			NPuzzle[rowOfZero][colOfZero] = temp;
		} else if (move.equals("r")) {
			int temp = NPuzzle[rowOfZero][colOfZero + 1];
			NPuzzle[rowOfZero][colOfZero + 1] = 0;
			NPuzzle[rowOfZero][colOfZero] = temp;
		}
		return NPuzzle; // Return the updated puzzle
	}

//	Function that randomly shuffles the puzzle k times
	public static int[][] shufflePuzzle(int[][] NPuzzle, int k) {
		int size = NPuzzle.length;
		int rowOfZero = size - 1; // Stores row index of 0
		int colOfZero = size - 1; // Stores col index of 0
		for (int i = 0; i < k; i++) {
			String posMoves = ""; // Store the available moves into a string
			int cntMoves = 0;
			if (colOfZero - 1 >= 0) {
				posMoves += "l";
			}
			if (rowOfZero - 1 >= 0) {
				posMoves += "u";
			}
			if (colOfZero + 1 < size) {
				posMoves += "r";
			}
			if (rowOfZero + 1 < size) {
				posMoves += "d";
			}

			int index = moveSelection(posMoves); // Index of move to execute
			String cmd = "" + posMoves.charAt(index); // Convert the move to a string
			NPuzzle = swapNumbers(NPuzzle, cmd, rowOfZero, colOfZero);
			int arr[] = indexZero(NPuzzle);
			rowOfZero = arr[0];
			colOfZero = arr[1];
		}
		return NPuzzle;
	}

//	Function that initializes the puzzle in automatic mode
	public static int[][] initializePuzzleK(int NPuzzle[][], int k) {
		NPuzzle = shufflePuzzle(NPuzzle, k);
		return NPuzzle;
	}

//	Function for automatic mode
	public static void automaticPlay(int NPuzzle[][], int kmin, int kmax, int p, int q) {
		int row0 = 0, col0 = 0;
		for (int i = kmin; i <= kmax; i++) {
			int cntSolved = 0; // Store the solved puzzles
			double sumMoves = 0; // Store the moves needed to solve puzzles
			NPuzzle = initializePuzzle(NPuzzle.length);
			int tempNPuzzle[][] = initializePuzzleK(NPuzzle, i); // New array to store the shuffled array
			for (int j = 0; j < p; j++) {
				int arr[][] = tempNPuzzle; // New array to initialize the puzzle with the shuffled one, every time a new game starts
				int size = arr.length;
				for (int k = 0; k < q; k++) {
					int a[] = indexZero(NPuzzle); // Find 0
					row0 = a[0];
					col0 = a[1];
					arr = automaticMove(arr, row0, col0); // Updated puzzle with swapped numbers
					if (isSolution(arr)) { // Check if solved
						cntSolved++; 
						sumMoves += k + 1; // Sum up the moves
						break;
					}
				}
			}
			automaticPrint (cntSolved, i, sumMoves, p); // Print the results
		}
	}

//	Function that finds the possible moves, and executes the swap
	public static int[][] automaticMove(int arr[][], int row0, int col0) {
		int size = arr.length;
		String posMoves = ""; // Store possible moves

		if ((col0 - 1) >= 0 && (col0 - 1) < size) {
			posMoves += "l";
		}
		if ((row0 - 1) >= 0 && (row0 - 1) < size) {
			posMoves += "u";
		}
		if ((col0 + 1) >= 0 && (col0 + 1) < size) {
			posMoves += "r";
		}
		if ((row0 + 1) >= 0 && (row0 + 1) < size) {
			posMoves += "d";
		}
		int index = moveSelection(posMoves);
		String cmd = "" + posMoves.charAt(index);
		arr = swapNumbers(arr, cmd, row0, col0); // Swap the numbers
		return arr;
	}

//	Function that prints the automatic play results
	public static void automaticPrint (int cntSolved, int i, double sumMoves, int p) {
		if (cntSolved == 0) {
			System.out.println("Gia k = " + i + " ekane meso oro kiniseon NaN kai nikise 0.00 % fores");
		} else {
			double avg = sumMoves / cntSolved;
			double perc = ((double) cntSolved / p) * 100; // Calculate the percentage
			System.out.println("Gia k = " + i + " ekane meso oro kiniseon " + String.format("%.2f", avg)
					+ " kai nikise " + String.format("%.2f", perc) + " % fores");

		}
	}

}
