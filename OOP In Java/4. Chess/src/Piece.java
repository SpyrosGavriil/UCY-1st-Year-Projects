package hw3.chess;

/**
 * Abstract class representing a chess piece.
 * 
 * @author Spyros Gavriil
 */
public abstract class Piece {

	/** The color of the piece. */
	Color color;

	/**
	 * Constructor to initialize the Piece with a given color.
	 *
	 * @param color The color of the piece.
	 */
	public Piece(Color color) {
		this.color = color;
	}

	/**
	 * Returns the algebraic notation name of the piece.
	 *
	 * @return The algebraic notation name of the piece.
	 */
	public abstract String algebraicName();

	/**
	 * Returns an array of squares representing the valid moves from a given square
	 * for this piece.
	 *
	 * @param square The square from which valid moves are to be determined.
	 * @return An array of squares representing the valid moves.
	 */
	public abstract Square[] movesFrom(Square square) throws InvalidSquareException;

	/**
	 * Returns the color of the piece.
	 *
	 * @return The color of the piece.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Returns the FEN (Forsyth–Edwards Notation) name of the piece.
	 *
	 * @return The FEN name of the piece.
	 */
	public String fenName() {
		return this.color == Color.WHITE ? this.algebraicName().toUpperCase() : this.algebraicName().toLowerCase();
	}

	/**
	 * Helper method to check if a position is valid on the board.
	 *
	 * @param row The row coordinate of the position.
	 * @param col The column coordinate of the position.
	 * @return True if the position is valid, false otherwise.
	 */
	public boolean isValidPosition(char row, char col) {
		// Assuming the board is an 8x8 grid with rows from 'a' to 'h' and columns from
		// '1' to '8'
		return row >= '1' && row <= '8' && col >= 'a' && col <= 'h';
	}

}
