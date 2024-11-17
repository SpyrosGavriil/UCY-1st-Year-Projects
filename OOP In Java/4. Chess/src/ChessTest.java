package hw3.chess;

import java.util.Scanner;

/**
 * This class implements a simple chess game simulation.
 * 
 * @author Spyros Gavriil
 */
public class ChessTest {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the chess game.
     *
     * @param args Command-line arguments (not used)
     * @throws InvalidSquareException 
     */
    public static void main(String args[]) throws InvalidSquareException {
        boolean continuePlaying;
        do {
            String pieceName = getValidPieceName(); // Get the piece name
            String pieceColor = getValidPieceColor(); // Get the piece color
            Square square = getValidSquare(); // Get the square
            Piece piece = constructPiece(pieceName, pieceColor); // Conctruct based on user choice

            boolean continueMove;
            do {
                printChessBoard(piece, square); // Print the board

                Square[] availableMoves = piece.movesFrom(square); // Calculate and print the available moves
                System.out.println("Available moves:");
                for (Square move : availableMoves) {
                    System.out.print(move.toString() + " ");
                }
                System.out.println();

                square = executeMove(piece, square); // Execute the move given by the user

                System.out.print("Do you want to continue with this piece? (yes/no): ");
                String response;
                do {
                    response = scanner.nextLine().trim().toLowerCase();
                } while (!response.equals("yes") && !response.equals("no"));

                continueMove = response.equals("yes");
            } while (continueMove);

            System.out.print("Do you want to continue playing? (yes/no): ");
            String response;
            do {
                response = scanner.nextLine().trim().toLowerCase();
            } while (!response.equals("yes") && !response.equals("no"));

            continuePlaying = response.equals("yes");

        } while (continuePlaying);

        System.out.println("Thank you for playing");
    }

    /**
     * Prompts the user to enter a valid piece name.
     *
     * @return The valid piece name entered by the user
     */
    private static String getValidPieceName() {
        String[] validPieceNames = {"pawn", "rook", "knight", "bishop", "queen", "king"};
        String pieceName;
        do {
            System.out.print("Enter piece name: ");
            pieceName = scanner.nextLine().trim().toLowerCase();
        } while (!isValidInput(pieceName, validPieceNames));
        return pieceName;
    }

    /**
     * Prompts the user to enter a valid piece color.
     *
     * @return The valid piece color entered by the user
     */
    private static String getValidPieceColor() {
        String[] validPieceColors = {"white", "black"};
        String pieceColor;
        do {
            System.out.print("Enter piece color: ");
            pieceColor = scanner.nextLine().trim().toLowerCase();
        } while (!isValidInput(pieceColor, validPieceColors));
        return pieceColor;
    }

    /**
     * Prompts the user to enter a valid square name.
     *
     * @return The valid square entered by the user
     */
    private static Square getValidSquare() {
        Square square;
        do {
            System.out.print("Enter square to place the piece (e.g., a3, b5, etc.): ");
            String squareName = scanner.nextLine().trim().toLowerCase();
            try {
                square = new Square(squareName);
            } catch (InvalidSquareException e) {
                System.out.println("Invalid square. Please enter a valid square.");
                square = null;
            }
        } while (square == null);
        return square;
    }

    /**
     * Constructs a piece object based on the given name and color.
     *
     * @param pieceName  The name of the piece
     * @param pieceColor The color of the piece
     * @return The constructed piece object
     */
    private static Piece constructPiece(String pieceName, String pieceColor) {
        switch (pieceName) {
            case "pawn":
                return new Pawn(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            case "rook":
                return new Rook(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            case "knight":
                return new Knight(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            case "bishop":
                return new Bishop(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            case "queen":
                return new Queen(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            case "king":
                return new King(pieceColor.equals("white") ? Color.WHITE : Color.BLACK);
            default:
                throw new IllegalArgumentException("Invalid piece name.");
        }
    }

    /**
     * Checks if the given input is valid among the provided options.
     *
     * @param input        The input to be validated
     * @param validOptions An array of valid options
     * @return true if the input is valid, otherwise false
     */
    private static boolean isValidInput(String input, String[] validOptions) {
        for (String validOption : validOptions) {
            if (input.equals(validOption)) {
                return true;
            }
        }
        System.out.println("Invalid input. Please try again.");
        return false;
    }

    /**
     * Executes a move for the given piece from the start square.
     *
     * @param piece       The piece to move
     * @param startSquare The starting square of the piece
     * @return The square the piece was moved to
     * @throws InvalidSquareException 
     */
    private static Square executeMove(Piece piece, Square startSquare) throws InvalidSquareException {
        boolean isValidMove = false;
        Square endSquare = null;
        String moveName = null; // Initialize moveName to null

        while (!isValidMove) {
            System.out.print("Enter the move you want to execute (e.g., a3, b5, etc.): ");
            moveName = scanner.nextLine().trim().toLowerCase();

            try {
                endSquare = new Square(moveName);
            } catch (InvalidSquareException e) {
                System.out.println("Invalid move. Please enter a valid square.");
                continue; // Skip to next iteration of the loop
            }

            Square[] availableMoves = piece.movesFrom(startSquare);
            for (Square availableMove : availableMoves) {
                if (availableMove.equals(endSquare)) {
                    isValidMove = true;
                    break;
                }
            }

            if (!isValidMove) {
                System.out.println("Invalid move. Please select a valid move.");
            }
        }
        printChessBoardAfterMove(piece, startSquare, endSquare);
        System.out.println("Move executed successfully.");
        return endSquare;
    }

    /**
     * Prints the chessboard after a move has been executed.
     *
     * @param piece       The piece that was moved
     * @param startSquare The starting square of the piece
     * @param endSquare   The ending square of the piece after the move
     */
    private static void printChessBoardAfterMove(Piece piece, Square startSquare, Square endSquare) {
        if (endSquare == null) {
            System.out.println("Invalid move. Please select a valid move.");
            return;
        }

        char[][] board = generateEmptyBoard();
        board[8 - (startSquare.getRow() - '0')][startSquare.getCol() - 'a'] = '-';
        board[8 - (endSquare.getRow() - '0')][endSquare.getCol() - 'a'] = piece.fenName().charAt(0);

        printChessBoard(piece, endSquare); // Print the updated board
    }

    /**
     * Generates an empty chessboard.
     *
     * @return A 2D array representing an empty chessboard
     */
    private static char[][] generateEmptyBoard() {
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }
        return board;
    }

    /**
     * Prints the current chessboard with the given piece at the specified square.
     *
     * @param piece  The piece to be placed on the board
     * @param square The square where the piece is placed
     */
    private static void printChessBoard(Piece piece, Square square) {
        char[][] board = generateEmptyBoard();
        board[8 - (square.getRow() - '0')][square.getCol() - 'a'] = piece.fenName().charAt(0);

        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
