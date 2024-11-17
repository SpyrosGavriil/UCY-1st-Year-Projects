import java.awt.Color;
import java.awt.Font;

public class Graphics {

//	Intitialize colors
	public static Color backgroundColor = new Color(47, 47, 47);
	public static Color gold = new Color(255, 203, 116);
	public static Color grey = new Color(67, 67, 67);
	public static Color white = new Color(246, 246, 246);

//	Initialize fonts
	public static Font title = new Font("SansSerif", Font.BOLD, 45);
	public static Font text = new Font("SansSerif", Font.BOLD, 20);
	public static Font settings = new Font("SansSerif", Font.BOLD, 25);
	public static Font count = new Font("SansSerif", Font.BOLD, 20);
	public static Font buttons = new Font("SansSerif", Font.BOLD, 12);
	public static Font auto = new Font("SansSerif", Font.BOLD, 15);

//	Handles the automatic algorithm, and prints the resutls
	public static void automaticPlayGraphic(int NPuzzle[][], int kmin, int kmax, int p, int q) {
		int difference = kmax - kmin;
		int canvasWidth = 700;
		int canvasHeight = 200 + difference * 20; // Responsive canvas height to the difficulty range
		double place = 0.7 / (difference + 1); // y value of where each difficulty results print

		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.clear(backgroundColor);
		StdDraw.setPenColor(grey);
		StdDraw.filledRectangle(0.5, 0.9, 1, 0.1);

		StdDraw.setFont(title);
		StdDraw.setPenColor(gold);
		StdDraw.text(0.5, 0.89, "RESULTS");
		StdDraw.setFont(auto);
		int cnt = difference + 1; // Value for y coordinates of each difficulty message
		int row0 = 0, col0 = 0;
		for (int i = kmin; i <= kmax; i++) {
			int cntSolved = 0; // Puzzles solved
			double sumMoves = 0; // Sum of moves for solved puzzles
			NPuzzle = Library.initializePuzzle(NPuzzle.length);
			int tempNPuzzle[][] = Library.initializePuzzleK(NPuzzle, i);
			for (int j = 0; j < p; j++) {
				int arr[][] = tempNPuzzle;
				int size = arr.length;
				for (int k = 0; k < q; k++) {
					int a[] = Library.indexZero(NPuzzle);
					row0 = a[0]; // Row index of 0
					col0 = a[1]; // Col index of 0
					arr = Library.automaticMove(arr, row0, col0); // Move 0 to the available moves
					if (Library.isSolution(arr)) {
						cntSolved++;
						sumMoves += k + 1;
						break; // Exit the moves loop, if the puzzle is solved
					}
				}
			}
			if (cntSolved == 0) { // None solved message
				StdDraw.textLeft(0.1, place * cnt,
						"Gia k = " + i + " ekane meso oro kiniseon NaN kai nikise 0.00% fores");
				cnt--;
			} else { // Solved message
				double avg = sumMoves / cntSolved; // Calculate the average moves of the solved puzzles
				double perc = ((double) cntSolved / p) * 100; // Calculate the percentage
				StdDraw.textLeft(0.1, place * cnt, "Gia k = " + i + " ekane meso oro kiniseon "
						+ String.format("%.2f", avg) + " kai nikise " + String.format("%.2f", perc) + " % fores");
				cnt--;
			}
		}

	}

//	Checks whether the move given is valid
	public static boolean isValidMoveGraphic(String move, int row, int col, int N) {
		int moveIndex = 0;

		if (move.equals("l")) {
			moveIndex = col - 1; // If the move is "left", decrease the column index by 1
		} else if (move.equals("r")) {
			moveIndex = col + 1; // If the move is "right", increase the column index by 1
		} else if (move.equals("u")) {
			moveIndex = row - 1; // If the move is "up", decrease the row index by 1
		} else if (move.equals("d")) {
			moveIndex = row + 1; // If the move is "down", increase the row index by 1
		} else if (move.equals("e")) {
			return true; // It's automatically valid, exits the game
		} else if (move.equals("o")) {
			return false; // Any other input is invalid, so return false
		}
		if (moveIndex < 0 || moveIndex == N) { // Check if the move is out of bounds
			return false;
		}
		return true;
	}

//	Home screen
	public static void homeScreen(int canvasWidth, int canvasHeight) {
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.clear(backgroundColor);

		StdDraw.setPenColor(grey);
		StdDraw.filledRectangle(0.5, 0.9, 1, 0.18);

		StdDraw.setFont(title);
		StdDraw.setPenColor(gold);
		StdDraw.text(0.5, 0.89, "PUZZLE");
		StdDraw.setPenColor(white);
		StdDraw.text(0.5, 0.79, "GAME");

//		Button
		StdDraw.setPenColor(grey);
		StdDraw.filledEllipse(0.5, 0.37, 0.22, 0.19);
		StdDraw.setPenColor(white);
		StdDraw.filledEllipse(0.5, 0.37, 0.2, 0.17);
		double triangleX[] = { 0.61, 0.44, 0.44 };
		double triangleY[] = { 0.37, 0.45, 0.29 };
		StdDraw.setPenColor(gold);
		StdDraw.filledPolygon(triangleX, triangleY);

		boolean check = true;

		while (check) {

			// Check if the mouse button is pressed and inside the button area
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				if (mouseX >= 0.3 && mouseX <= 0.7 && mouseY >= 0.2 && mouseY <= 0.54) {
					check = false;
				}
			}
		}
	}

//	Gamemode screen
	public static int gameMode(int canvasSize) {
		StdDraw.setCanvasSize(canvasSize, canvasSize);
		StdDraw.clear(backgroundColor);

		StdDraw.setPenColor(grey);
		StdDraw.filledRectangle(0.5, 0.9, 1, 0.2);

		StdDraw.setFont(title);
		StdDraw.setPenColor(gold);
		StdDraw.text(0.5, 0.89, "GAME");
		StdDraw.setPenColor(white);
		StdDraw.text(0.5, 0.79, "MODE");

//		Drop shadow 
		StdDraw.setPenColor(grey);
		StdDraw.filledRectangle(0.509, 0.491, 0.3, 0.05);
		StdDraw.filledCircle(0.809, 0.491, 0.05);
		StdDraw.filledCircle(0.2, 0.5, 0.05);

//	    Drop shadow
		StdDraw.filledRectangle(0.509, 0.341, 0.3, 0.05);
		StdDraw.filledCircle(0.809, 0.341, 0.05);
		StdDraw.filledCircle(0.2, 0.35, 0.05);

//	    Drop shadow
		StdDraw.filledRectangle(0.509, 0.191, 0.3, 0.05);
		StdDraw.filledCircle(0.809, 0.191, 0.05);
		StdDraw.filledCircle(0.2, 0.2, 0.05);

		StdDraw.setPenColor(white);

//	   	Interactive Play Button
		StdDraw.filledRectangle(0.5, 0.5, 0.3, 0.05);
		StdDraw.filledCircle(0.8, 0.5, 0.05);
		StdDraw.filledCircle(0.2, 0.5, 0.05);

//	    Automatic Play Button
		StdDraw.filledRectangle(0.5, 0.35, 0.3, 0.05);
		StdDraw.filledCircle(0.8, 0.35, 0.05);
		StdDraw.filledCircle(0.2, 0.35, 0.05);

//	    Exit Button
		StdDraw.filledRectangle(0.5, 0.2, 0.3, 0.05);
		StdDraw.filledCircle(0.8, 0.2, 0.05);
		StdDraw.filledCircle(0.2, 0.2, 0.05);

//		Display the modes
		StdDraw.setFont(text);
		StdDraw.setPenColor(gold);
		StdDraw.text(0.5, 0.5, "Interactive Play");
		StdDraw.text(0.5, 0.35, "Automatic Play");
		StdDraw.text(0.5, 0.2, "Exit");

		int mode = 0; // Variable to choose mode
		boolean c = true;

//		Get mode 
		while (c) {
			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

//				Interactive play coordinates
				if (mouseX >= 0.15 && mouseX <= 0.85 && mouseY >= 0.4 && mouseY <= 0.6) {
					c = false;
					mode = 1;
//				Automatic play coordinates
				} else if (mouseX >= 0.15 && mouseX <= 0.85 && mouseY >= 0.25 && mouseY <= 0.45) {
					c = false;
					mode = 2;
//				Exit coordinates	
				} else if (mouseX >= 0.15 && mouseX <= 0.85 && mouseY >= 0.1 && mouseY <= 0.3) {
					c = false;
					mode = 3;
				}
			}
		}
		return mode;
	}

//	Game settings screen
	public static void gameSettings(int canvasSize, int canvasWidth) {
		StdDraw.setCanvasSize(canvasWidth, canvasSize);
		StdDraw.clear(backgroundColor);

		StdDraw.setPenColor(grey);
		StdDraw.filledRectangle(0.5, 0.9, 1, 0.2);

		StdDraw.setFont(title);
		StdDraw.setPenColor(gold);
		StdDraw.text(0.5, 0.89, "GAME");
		StdDraw.setPenColor(white);
		StdDraw.text(0.5, 0.79, "SETTINGS");

	}

//	Main output function handling interactive play, automatic play and exit screen
	public static void graphicsOutput(int N) {

//		Variables for time elapsed in game
		long gameStartTime = 0, elapsedTime = 0;
		int canvasWidth = 300;
		int canvasHeight = 360;

		homeScreen(canvasWidth, canvasHeight);

		StdDraw.pause(250);
		int canvasSize = 400;

		int mode = gameMode(canvasSize);

		StdDraw.pause(250);
		canvasWidth = 600;

		gameSettings(canvasSize, canvasWidth);

//		Interactive mode
		if (mode == 1) {

//			GAME SETTINGS WINDOW
			StdDraw.setFont(settings);
			StdDraw.setPenColor(gold);
			StdDraw.text(0.2, 0.5, "DIFFICULTY: ");

			String pSize = Integer.toString(N);
			String cnt = "1"; // Value for difficulty
			String puzzleSize = pSize; // Value for the puzzle size,if wished to be changed

			StdDraw.setPenColor(white);
//	        Decrease difficulty button
			StdDraw.filledEllipse(0.42, 0.51, 0.02, 0.032);
//	        Decrease by 10
			StdDraw.filledEllipse(0.37, 0.51, 0.02, 0.032);
//	        Increase difficulty button
			StdDraw.filledEllipse(0.62, 0.51, 0.02, 0.032);
//	        Increase by 10
			StdDraw.filledEllipse(0.67, 0.51, 0.02, 0.032);

			StdDraw.setPenColor(gold);
			StdDraw.setPenRadius(0.005);
			StdDraw.line(0.355, 0.51, 0.36, 0.51); // - symbol for -10
			StdDraw.line(0.655, 0.51, 0.665, 0.51); // + symbol for +10
			StdDraw.line(0.66, 0.502, 0.66, 0.518); // + symbol for +10
			StdDraw.setFont(buttons);
			StdDraw.text(0.375, 0.507, "10");
			StdDraw.text(0.675, 0.507, "10");
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.52, 0.502, cnt); // Difficulty value

			StdDraw.setPenRadius(0.005);
			StdDraw.line(0.41, 0.51, 0.43, 0.51); // - for -1
			StdDraw.line(0.61, 0.51, 0.63, 0.51); // + for +1
			StdDraw.line(0.62, 0.492, 0.62, 0.526); // + for +1

//	        Puzzle Size 
			StdDraw.setPenColor(gold);
			StdDraw.setFont(settings);
			StdDraw.text(0.21, 0.3, "PUZZLE SIZE: ");

			StdDraw.setPenColor(white);
			StdDraw.filledEllipse(0.42, 0.31, 0.021, 0.032); // - circle
			StdDraw.filledEllipse(0.62, 0.31, 0.021, 0.032); // + circle
			StdDraw.setPenColor(gold);
			StdDraw.line(0.41, 0.31, 0.43, 0.31); // - symbol
			StdDraw.line(0.61, 0.31, 0.63, 0.31); // + symbol
			StdDraw.line(0.62, 0.293, 0.62, 0.325); // + symbol

//			Start Button
			StdDraw.setPenColor(white);
			StdDraw.filledRectangle(0.5, 0.1, 0.06, 0.05);
			StdDraw.filledEllipse(0.44, 0.1, 0.02, 0.05);
			StdDraw.filledEllipse(0.56, 0.1, 0.02, 0.05);
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.52, 0.305, puzzleSize);
			StdDraw.setFont(text);
			StdDraw.text(0.5, 0.095, "START");

			boolean isStartPressed = false;
			int intValueD = 1; // Integer value for difficulty
			int intValueP = N; // Integer value for puzzle size

//			Check if the START button is pressed
			while (!isStartPressed) {

				if (StdDraw.isMousePressed()) {
					double mouseX = StdDraw.mouseX(); // x value of mouse
					double mouseY = StdDraw.mouseY(); // y value of mouse

//					Decrease difficulty by 1
					if (mouseX >= 0.4 && mouseX <= 0.45 && mouseY >= 0.478 && mouseY <= 0.542) {
						intValueD = Integer.parseInt(cnt);
						intValueD--;

//					Decrease difficulty by 10
					} else if (mouseX >= 0.35 && mouseX <= 0.39 && mouseY >= 0.478 && mouseY <= 0.542) {
						intValueD = Integer.parseInt(cnt);
						intValueD -= 10;

//					Increase difficulty by 1	
					} else if (mouseX >= 0.6 && mouseX <= 0.64 && mouseY >= 0.478 && mouseY <= 0.542) {
						intValueD = Integer.parseInt(cnt);
						intValueD++;

//					Increase difficulty by 10	
					} else if (mouseX >= 0.65 && mouseX <= 0.69 && mouseY >= 0.478 && mouseY <= 0.542) {
						intValueD = Integer.parseInt(cnt);
						intValueD += 10;

//					Decrease puzzle size by 1	
					} else if (mouseX >= 0.399 && mouseX <= 0.441 && mouseY >= 0.272 && mouseY <= 0.342) {
						intValueP = Integer.parseInt(puzzleSize);
						intValueP--;

//					Increase puzzle size by 1	
					} else if (mouseX >= 0.599 && mouseX <= 0.641 && mouseY >= 0.272 && mouseY <= 0.342) {
						intValueP = Integer.parseInt(puzzleSize);
						intValueP++;
					}

//					Min. difficulty should be 1
					if (intValueD < 1) {
						intValueD = 1;
					}

//					Min. puzzle size should be 3
					if (intValueP < 3) {
						intValueP = 3;
					}

					cnt = Integer.toString(intValueD); // Turn intValueD to a string
					puzzleSize = Integer.toString(intValueP); // Turn intValueP to a string
					StdDraw.setPenColor(backgroundColor);
					StdDraw.filledRectangle(0.52, 0.51, 0.03, 0.04); // Rectangle to cover the previous value of cnt
					StdDraw.filledRectangle(0.52, 0.31, 0.03, 0.04); // Rectangle to cover the previous value of
																		// puzzleSize

					StdDraw.setPenColor(gold);
					StdDraw.setFont(count);
					StdDraw.text(0.52, 0.502, cnt); // Print new difficulty
					StdDraw.text(0.52, 0.305, puzzleSize); // Print new puzzle size

//					Check if START button has been pressed
					if (mouseX >= 0.42 && mouseX <= 0.58 && mouseY >= 0.05 && mouseY <= 0.15) {
						isStartPressed = true;
					}
					StdDraw.pause(100);
				}
				StdDraw.pause(80);
			}

			int size = intValueP;
			canvasSize = size * 150; // Make the canvas size responsive to the puzzle size
			int cellSize = 70; // Size of each cell of the puzzle
			double lineThickness = 0.01;

			StdDraw.setCanvasSize(canvasSize, canvasSize);
			StdDraw.clear(backgroundColor);

			StdDraw.setXscale(0, canvasSize); // Change the x scale for easier customisation
			StdDraw.setYscale(0, canvasSize); // Change the y scale for easier customisation

			StdDraw.setPenColor(grey);
			StdDraw.filledRectangle(canvasSize / 2, canvasSize * 0.9, canvasSize, canvasSize * 0.2);
			StdDraw.setPenColor(gold);
			Font respTitle = new Font("SansSerif", Font.BOLD, canvasSize / 10); // Responsive font for the title
			StdDraw.setFont(respTitle);
			StdDraw.text(canvasSize / 2, canvasSize * 0.89, "GOOD");
			StdDraw.setPenColor(white);
			StdDraw.text(canvasSize / 2, canvasSize * 0.79, "LUCK");

			StdDraw.setPenColor(white);
			double xOffset = (canvasSize - (size * cellSize)) / 2; // Change the x position of the puzzle
			double yOffset = (canvasSize - (size * cellSize)) / 4; // Change the y position of the puzzle
			int[][] puzzle = Library.initializePuzzle(size); // Initialize our puzzle
			puzzle = Library.shufflePuzzle(puzzle, intValueD); // Shuffle the puzzle

//			If the shuffled puzzle is solved, shuffle until it's not solved
			while (Library.isSolution(puzzle)) {
				puzzle = Library.shufflePuzzle(puzzle, intValueD);
			}

			int tempPuzzle[][] = puzzle; // New puzzle to play with
			boolean solved = false;
			int moves = 0; // Value to count moves played
			StdDraw.setFont(text);
			gameStartTime = System.currentTimeMillis(); // Start the timer for the game

//			Draw the cells of the puzzle with the according number in them
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					double x = xOffset + j * cellSize;
					double y = yOffset + i * cellSize;
					StdDraw.setPenColor(white);
					StdDraw.filledRectangle(x + cellSize / 2, y + cellSize / 2, cellSize / 2, cellSize / 2); // Draw
																												// cells

					int cellValue = tempPuzzle[(size - 1) - i][j]; // Numbers
					if (cellValue != 0) {
						// Draw the number in the center of the cell
						StdDraw.setPenColor(gold);
						StdDraw.text(x + cellSize / 2, y + cellSize / 2, String.valueOf(cellValue));
					}
				}
			}
			// Draw lines to separate each cell
			StdDraw.setPenRadius(lineThickness);
			StdDraw.setPenColor(grey);
			for (int i = 0; i <= size; i++) {
				double y = yOffset + i * cellSize;
				StdDraw.line(xOffset, y, xOffset + (size * cellSize), y);
			}
			for (int j = 0; j <= size; j++) {
				double x = xOffset + j * cellSize;
				StdDraw.line(x, yOffset, x, yOffset + (size * cellSize));
			}

			String move = "";

//			Check if the puzzle is solved
			while (!solved) {
				if (StdDraw.hasNextKeyTyped()) { // If there's a key typed enter the if statement
					char key = StdDraw.nextKeyTyped();
					if (key == 'w') { // If 'W' is pressed, the move is "up"
						move = "u";
					} else if (key == 'a') { // If 'A' is pressed, the move is "left"
						move = "l";
					} else if (key == 's') { // If 'S' is pressed, the move is "down"
						move = "d";
					} else if (key == 'd') { // If 'D' is pressed, the move is "right"
						move = "r";
					} else if (key == 'e') { // If 'E' is pressed, the move is "exit"
						move = "e";
					} else { // If anything else is pressed, the move is "other"
						move = "o";
					}

					int a[] = Library.indexZero(tempPuzzle);
					int row0 = a[0]; // Row index of 0
					int col0 = a[1]; // Column index of 0

					StdDraw.setFont(text);
					StdDraw.setPenColor(white);

//					Check if the move is out of bounds
					while (!isValidMoveGraphic(move, row0, col0, size)) {
						StdDraw.setPenColor(gold);
						if (move.equals("e")) { // If move is "e", then exit the loop
							break;
						} else if (move.equals("o")) { // If the move is not a valid input, print message
							StdDraw.text(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10),
									"Wrong input, expected input is (w, a, s, d, e)");
						} else if (move.equals("d")) { // If the move is "down" and out of bounds, print message
							StdDraw.text(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10),
									"You are not allowed to go down");
						} else if (move.equals("u")) { // If the move is "up" and out of bounds, print message
							StdDraw.text(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10),
									"You are not allowed to go up");
						} else if (move.equals("l")) { // If the move is "left" and out of bounds, print message
							StdDraw.text(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10),
									"You are not allowed to go left");
						} else if (move.equals("r")) { // If the move is "right" and out of bounds, print message
							StdDraw.text(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10),
									"You are not allowed to go right");
						}

//						Ask for new key
						if (StdDraw.hasNextKeyTyped()) {

							StdDraw.setPenColor(backgroundColor);
							StdDraw.filledRectangle(canvasSize / 2, canvasSize - 9.5 * (canvasSize / 10), canvasSize,
									canvasSize / 20);
							key = StdDraw.nextKeyTyped();
							if (key == 'w') { // 'W' key was pressed
								move = "u";
							} else if (key == 'a') { // 'A' key was pressed
								move = "l";
							} else if (key == 's') { // 'S' key was pressed
								move = "d";
							} else if (key == 'd') { // 'D' key was pressed
								move = "r";
							} else if (key == 'e') { // 'E' key was pressed
								move = "e";
							} else { // Anything else was pressed
								move = "o";
							}
						}
					} // Exit the out of bounds move, when the move is valid
					if (move.equals("e")) { // If move is 'e' exit the game
						break;
					}
					a = Library.indexZero(tempPuzzle);
					row0 = a[0];
					col0 = a[1];
					tempPuzzle = Library.swapNumbers(tempPuzzle, move, row0, col0); // Swap the numbers according to the
																					// move typed
					moves++; // Add a move to the overall count

//					Print the updated puzzle
					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							double x = xOffset + j * cellSize;
							double y = yOffset + i * cellSize;
							StdDraw.setPenColor(white);
							StdDraw.filledRectangle(x + cellSize / 2, y + cellSize / 2, cellSize / 2, cellSize / 2);

							int cellValue = tempPuzzle[(size - 1) - i][j];
							if (cellValue != 0) {
								// Draw the number in the center of the cell
								StdDraw.setPenColor(gold);
								StdDraw.text(x + cellSize / 2, y + cellSize / 2, String.valueOf(cellValue));
							}

						}
					}
					StdDraw.setPenRadius(lineThickness);
					StdDraw.setPenColor(grey);
					for (int i = 0; i <= size; i++) {
						double y = yOffset + i * cellSize;
						StdDraw.line(xOffset, y, xOffset + (size * cellSize), y);
					}
					for (int j = 0; j <= size; j++) {
						double x = xOffset + j * cellSize;
						StdDraw.line(x, yOffset, x, yOffset + (size * cellSize));
					}
					if (Library.isSolution(tempPuzzle)) {
						solved = true;
					}
				}
			} // End of while loop that checks if the puzzle is solved

//			SOLVED WINDOW
			if (solved) { // Puzzle has been solved
				if (gameStartTime > 0) { // Calculate the time spent in-game
					long endTime = System.currentTimeMillis();
					elapsedTime += (endTime - gameStartTime);
					gameStartTime = 0;
				}
				elapsedTime = elapsedTime / 1000; // Convert milliseconds to seconds
				canvasWidth = 700;
				canvasHeight = 400;
				StdDraw.setCanvasSize(canvasWidth, canvasHeight);

				StdDraw.setScale(0, 1); // Set scale to default
				StdDraw.clear(backgroundColor);
				StdDraw.setPenColor(grey);
				StdDraw.filledRectangle(0.5, 0.9, 1, 0.2);
				StdDraw.setFont(title);
				StdDraw.setPenColor(gold);
				StdDraw.text(0.5, 0.89, "CONGRATULATIONS");
				StdDraw.setPenColor(white);
				StdDraw.text(0.5, 0.79, "YOU WON");
				StdDraw.setFont(settings);
				StdDraw.text(0.3, 0.5, String.valueOf(moves)); // Print the moves needed to solve
				StdDraw.text(0.34, 0.3, String.valueOf(elapsedTime) + " Seconds"); // Print the time spent in-game
				StdDraw.setPenColor(gold);
				StdDraw.text(0.2, 0.5, "MOVES: ");
				StdDraw.text(0.18, 0.3, "TIME: ");

//			NOT SOLVED WINDOW	
			} else {
				canvasWidth = 700;
				canvasHeight = 400;
				StdDraw.setCanvasSize(canvasWidth, canvasHeight);
				StdDraw.clear(backgroundColor);
				StdDraw.setPenColor(grey);
				StdDraw.filledRectangle(0.5, 0.9, 1, 0.2);
				StdDraw.setFont(title);
				StdDraw.setPenColor(gold);
				StdDraw.text(0.5, 0.89, "GAME");
				StdDraw.setPenColor(white);
				StdDraw.text(0.5, 0.79, "OVER");
				StdDraw.setPenColor(gold);
				StdDraw.setFont(settings);
				StdDraw.text(0.5, 0.5, "You failed to solve the puzzle! Maybe try again");
				StdDraw.text(0.2, 0.3, "MOVES: ");
				StdDraw.setPenColor(white);
				StdDraw.text(0.3, 0.3, String.valueOf(moves)); // Print the moves attempter
			}

//			AUTOMATIC MODE
		} else if (mode == 2) {
			String cntMin = "1", cntMax = "1", cntG = "1", cntM = "1"; // Variable for each parameter the user should
																		// give
			StdDraw.setFont(settings);
			StdDraw.setPenColor(gold);
			StdDraw.textLeft(0.1, 0.55, "MIN. DIFFICULTY: ");
			StdDraw.textLeft(0.1, 0.4, "MAX. DIFFICULTY: ");
			StdDraw.textLeft(0.1, 0.25, "GAMES: ");
			StdDraw.textLeft(0.1, 0.1, "MAX. MOVES: ");
			StdDraw.setPenColor(white);

			StdDraw.filledEllipse(0.57, 0.555, 0.02, 0.032); // Decrease min difficulty by 1
			StdDraw.filledEllipse(0.52, 0.555, 0.02, 0.032); // Decrease min difficulty by 10
			StdDraw.filledEllipse(0.77, 0.555, 0.02, 0.032); // Increase min difficulty by 1
			StdDraw.filledEllipse(0.82, 0.555, 0.02, 0.032); // Increase min difficulty by 10
			StdDraw.filledEllipse(0.57, 0.405, 0.02, 0.032); // Decrease max difficulty by 1
			StdDraw.filledEllipse(0.52, 0.405, 0.02, 0.032); // Decrease max difficulty by 10
			StdDraw.filledEllipse(0.77, 0.405, 0.02, 0.032); // Increase max difficulty by 1
			StdDraw.filledEllipse(0.82, 0.405, 0.02, 0.032); // Increase max difficulty by 10
			StdDraw.filledEllipse(0.57, 0.255, 0.02, 0.032); // Decrease games by 1
			StdDraw.filledEllipse(0.52, 0.255, 0.02, 0.032); // Decrease games by 20
			StdDraw.filledEllipse(0.77, 0.255, 0.02, 0.032); // Increase games by 1
			StdDraw.filledEllipse(0.82, 0.255, 0.02, 0.032); // Increase games by 20
			StdDraw.filledEllipse(0.57, 0.105, 0.02, 0.032); // Decrease moves by 1
			StdDraw.filledEllipse(0.52, 0.105, 0.02, 0.032); // Decrease moves by 20
			StdDraw.filledEllipse(0.77, 0.105, 0.02, 0.032); // Increase moves by 1
			StdDraw.filledEllipse(0.82, 0.105, 0.02, 0.032); // Increase moves by 20
			StdDraw.setPenColor(gold);
			StdDraw.setPenRadius(0.005);
			StdDraw.line(0.505, 0.555, 0.51, 0.555); // -10
			StdDraw.line(0.805, 0.555, 0.815, 0.555); // +10
			StdDraw.line(0.81, 0.547, 0.81, 0.563); // +10
			StdDraw.setFont(buttons);
			StdDraw.text(0.525, 0.553, "10");
			StdDraw.text(0.825, 0.553, "10");
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.67, 0.55, cntMin); // Print value
			StdDraw.line(0.56, 0.555, 0.58, 0.555); // -1
			StdDraw.line(0.76, 0.555, 0.78, 0.555); // +1
			StdDraw.line(0.77, 0.573, 0.77, 0.537); // +1

			StdDraw.line(0.505, 0.405, 0.51, 0.405); // -10
			StdDraw.line(0.805, 0.405, 0.815, 0.405); // +10
			StdDraw.line(0.81, 0.397, 0.81, 0.413); // +10
			StdDraw.setFont(buttons);
			StdDraw.text(0.525, 0.403, "10");
			StdDraw.text(0.825, 0.403, "10");
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.67, 0.40, cntMax); // Print value
			StdDraw.line(0.56, 0.4055, 0.58, 0.405); // -1
			StdDraw.line(0.76, 0.405, 0.78, 0.405); // +1
			StdDraw.line(0.77, 0.423, 0.77, 0.387); // +1

			StdDraw.setPenColor(gold);
			StdDraw.line(0.505, 0.255, 0.51, 0.255); // -20
			StdDraw.line(0.805, 0.255, 0.815, 0.255); // +20
			StdDraw.line(0.81, 0.247, 0.81, 0.263); // +20
			StdDraw.setFont(buttons);
			StdDraw.text(0.525, 0.253, "20");
			StdDraw.text(0.825, 0.253, "20");
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.67, 0.25, cntG); // Print value
			StdDraw.line(0.56, 0.255, 0.58, 0.255); // -1
			StdDraw.line(0.76, 0.255, 0.78, 0.255); // +1
			StdDraw.line(0.77, 0.273, 0.77, 0.237); // +1

			StdDraw.line(0.505, 0.105, 0.51, 0.105); // -20
			StdDraw.line(0.805, 0.105, 0.815, 0.105); // 20
			StdDraw.line(0.81, 0.097, 0.81, 0.113); // +20
			StdDraw.setFont(buttons);
			StdDraw.text(0.525, 0.103, "20");
			StdDraw.text(0.825, 0.103, "20");
			StdDraw.setPenColor(gold);
			StdDraw.setFont(count);
			StdDraw.text(0.67, 0.10, cntM); // Print value
			StdDraw.line(0.56, 0.1055, 0.58, 0.105); // -1
			StdDraw.line(0.76, 0.105, 0.78, 0.105); // +1
			StdDraw.line(0.77, 0.123, 0.77, 0.087); // +1
			StdDraw.setPenColor(gold);
			double triangleX1[] = { 0.96, 0.92, 0.92 }; // Start button x coordinates
			double triangleY1[] = { 0.08, 0.11, 0.05 }; // Start button y coordinates
			StdDraw.filledPolygon(triangleX1, triangleY1); // Draw start buttom

			boolean isStartPressed = false;
			int intMinValueD = 0, intMaxValueD = 0, intValueG = 0, intValueM = 0;

//			Check if the START button is pressed
			while (!isStartPressed) {
				if (StdDraw.isMousePressed()) {
					double mouseX = StdDraw.mouseX();
					double mouseY = StdDraw.mouseY();

					if (mouseX >= 0.55 && mouseX <= 0.59 && mouseY >= 0.518 && mouseY <= 0.587) { // Decrease min
																									// difficulty by 1
						intMinValueD = Integer.parseInt(cntMin);
						intMinValueD--;
					} else if (mouseX >= 0.5 && mouseX <= 0.54 && mouseY >= 0.518 && mouseY <= 0.587) { // Decrease min
																										// difficulty by
																										// 10
						intMinValueD = Integer.parseInt(cntMin);
						intMinValueD -= 10;
					} else if (mouseX >= 0.75 && mouseX <= 0.79 && mouseY >= 0.518 && mouseY <= 0.587) { // Increase min
																											// difficulty
																											// by 1
						intMinValueD = Integer.parseInt(cntMin);
						intMinValueD++;
					} else if (mouseX >= 0.8 && mouseX <= 0.84 && mouseY >= 0.518 && mouseY <= 0.587) { // Increase min
																										// difficulty by
																										// 10
						intMinValueD = Integer.parseInt(cntMin);
						intMinValueD += 10;
					}

					else if (mouseX >= 0.55 && mouseX <= 0.59 && mouseY >= 0.368 && mouseY <= 0.432) { // Decrease max
																										// difficulty by
																										// 1
						intMaxValueD = Integer.parseInt(cntMax);
						intMaxValueD--;
					} else if (mouseX >= 0.5 && mouseX <= 0.54 && mouseY >= 0.368 && mouseY <= 0.432) { // Decrease max
																										// difficulty by
																										// 10
						intMaxValueD = Integer.parseInt(cntMax);
						intMaxValueD -= 10;
					} else if (mouseX >= 0.75 && mouseX <= 0.79 && mouseY >= 0.368 && mouseY <= 0.432) { // Increase max
																											// difficulty
																											// by 1
						intMaxValueD = Integer.parseInt(cntMax);
						intMaxValueD++;
					} else if (mouseX >= 0.8 && mouseX <= 0.84 && mouseY >= 0.368 && mouseY <= 0.432) { // Increase max
																										// difficulty by
																										// 10
						intMaxValueD = Integer.parseInt(cntMax);
						intMaxValueD += 10;
					}

					else if (mouseX >= 0.55 && mouseX <= 0.59 && mouseY >= 0.218 && mouseY <= 0.287) { // Decrease games
																										// by 1
						intValueG = Integer.parseInt(cntG);
						intValueG--;
					} else if (mouseX >= 0.5 && mouseX <= 0.54 && mouseY >= 0.218 && mouseY <= 0.287) { // Decrease
																										// games by 20
						intValueG = Integer.parseInt(cntG);
						intValueG -= 20;
					} else if (mouseX >= 0.75 && mouseX <= 0.79 && mouseY >= 0.218 && mouseY <= 0.287) { // Increase
																											// games by
																											// 1
						intValueG = Integer.parseInt(cntG);
						intValueG++;
					} else if (mouseX >= 0.8 && mouseX <= 0.84 && mouseY >= 0.218 && mouseY <= 0.287) { // Increase
																										// games by 20
						intValueG = Integer.parseInt(cntG);
						intValueG += 20;
					} else if (mouseX >= 0.55 && mouseX <= 0.59 && mouseY >= 0.068 && mouseY <= 0.132) { // Decrease
																											// moves by
																											// 1
						intValueM = Integer.parseInt(cntM);
						intValueM--;
					} else if (mouseX >= 0.5 && mouseX <= 0.54 && mouseY >= 0.068 && mouseY <= 0.132) { // Decrease
																										// movex by 20
						intValueM = Integer.parseInt(cntM);
						intValueM -= 20;
					} else if (mouseX >= 0.75 && mouseX <= 0.79 && mouseY >= 0.0668 && mouseY <= 0.132) { // Increase
																											// moves by
																											// 1
						intValueM = Integer.parseInt(cntM);
						intValueM++;
					} else if (mouseX >= 0.8 && mouseX <= 0.84 && mouseY >= 0.068 && mouseY <= 0.132) { // Increase
																										// moves by 20
						intValueM = Integer.parseInt(cntM);
						intValueM += 20;
					}

					if (intMinValueD < 1) { // Minimum min difficulty should be 1
						intMinValueD = 1;
					}
					if (intMaxValueD < 1) { // Minimum max difficulty should be 1
						intMaxValueD = 1;
					}
					if (intValueG < 1) { // Minimum games per difficulty should be 1
						intValueG = 1;
					}
					if (intValueM < 1) { // Minimum moves per game should be 1
						intValueM = 1;
					}

					cntMin = Integer.toString(intMinValueD);
					cntMax = Integer.toString(intMaxValueD);
					cntG = Integer.toString(intValueG);
					cntM = Integer.toString(intValueM);
					StdDraw.setPenColor(backgroundColor);
					StdDraw.filledRectangle(0.67, 0.54, 0.05, 0.04);
					StdDraw.filledRectangle(0.67, 0.385, 0.05, 0.04);
					StdDraw.filledRectangle(0.67, 0.235, 0.05, 0.04);
					StdDraw.filledRectangle(0.67, 0.085, 0.05, 0.04);
					StdDraw.setPenColor(gold);
					StdDraw.setFont(count);
					StdDraw.text(0.67, 0.55, cntMin); // Print the new min difficulty
					StdDraw.text(0.67, 0.40, cntMax); // Print the new max difficulty
					StdDraw.text(0.67, 0.25, cntG); // Print the new games value
					StdDraw.text(0.67, 0.1, cntM); // Print the new moves value

//					Check if START is pressed
					if (mouseX >= 0.92 && mouseX <= 0.96 && mouseY >= 0.05 && mouseY <= 0.11) {
						isStartPressed = true;
					}
					StdDraw.pause(100);

				}
				StdDraw.pause(80);
			}
			int NPuzzle[][] = Library.initializePuzzle(N); // Initialize a puzzle
			automaticPlayGraphic(NPuzzle, intMinValueD, intMaxValueD, intValueG, intValueM); // Execute the automatic
																								// algorithm

//	EXIT SCREEN		
		} else {
			canvasWidth = 600;
			canvasHeight = 300;
			StdDraw.setCanvasSize(canvasWidth, canvasHeight);
			StdDraw.clear(backgroundColor);
			StdDraw.setPenColor(grey);
			StdDraw.filledRectangle(0.5, 0.85, 1, 0.15);
			StdDraw.setPenColor(gold);
			StdDraw.setFont(title);
			StdDraw.text(0.5, 0.85, "EXIT");
			StdDraw.setFont(text);
			StdDraw.text(0.5, 0.45, "Thank you for playing!");
			StdDraw.text(0.5, 0.35, "Come back for more");

		}

	}

}
