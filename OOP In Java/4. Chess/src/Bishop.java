package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a bishop chess piece.
 * 
 * @author Spyros Gavriil
 */
public class Bishop extends Piece {

	/**
	 * Constructs a Bishop piece with the given color.
	 *
	 * @param color The color of the bishop piece.
	 */
	public Bishop(Color color) {
		super(color);
	}

	/**
	 * Returns the algebraic notation name of the bishop piece.
	 *
	 * @return The algebraic notation name of the bishop piece ("B").
	 */
	@Override
	public String algebraicName() {
		return "B";
	}

	/**
	 * Computes the possible moves for the bishop from a given square.
	 *
	 * @param square The square from which moves are to be determined.
	 * @return An array of squares representing the possible moves.
	 * @throws InvalidSquareException 
	 */
	@Override
	public Square[] movesFrom(Square square) throws InvalidSquareException {
		ArrayList<Square> possibleMoves = new ArrayList<>();

		// Iterate through diagonals
		for (int i = -7; i < 8; i++) {
			if (i == 0)
				continue;
			char newRow = (char) (square.getRow() + i);
			char newCol = (char) (square.getCol() + i);

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}

			newRow = (char) (square.getRow() + i);
			newCol = (char) (square.getCol() - i);

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}
		}

		return possibleMoves.toArray(new Square[possibleMoves.size()]);
	}

}
