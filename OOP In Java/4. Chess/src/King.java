package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a king chess piece.
 * 
 * @author Spyros Gavriil
 */
public class King extends Piece {
	/**
	 * Constructs a King piece with the given color.
	 *
	 * @param color The color of the king piece.
	 */
	public King(Color color) {
		super(color);
	}

	/**
	 * Returns the algebraic notation name of the king piece.
	 *
	 * @return The algebraic notation name of the king piece ("K").
	 */
	@Override
	public String algebraicName() {
		return "K";
	}

	/**
	 * Computes the possible moves for the king from a given square.
	 *
	 * @param square The square from which moves are to be determined.
	 * @return An array of squares representing the possible moves.
	 * @throws InvalidSquareException 
	 */
	@Override
	public Square[] movesFrom(Square square) throws InvalidSquareException {
		ArrayList<Square> possibleMoves = new ArrayList<>();

		// Iterate through adjacent squares
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				char newRow = (char) (square.getRow() + i);
				char newCol = (char) (square.getCol() + j);

				// Check if the new position is valid and not the same as the current position
				if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
					Square newSquare = new Square(newCol, newRow);
					possibleMoves.add(newSquare);
				}
			}
		}

		return possibleMoves.toArray(new Square[possibleMoves.size()]);
	}

}
