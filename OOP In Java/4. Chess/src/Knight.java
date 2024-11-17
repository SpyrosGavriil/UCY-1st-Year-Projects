package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a knight chess piece.
 * 
 * @author Spyros Gavriil
 */
public class Knight extends Piece {

	/**
	 * Constructs a Knight piece with the given color.
	 *
	 * @param color The color of the knight piece.
	 */
	public Knight(Color color) {
		super(color);
	}

	/**
	 * Returns the algebraic notation name of the knight piece.
	 *
	 * @return The algebraic notation name of the knight piece ("N").
	 */
	@Override
	public String algebraicName() {
		return "N";
	}

	/**
	 * Computes the possible moves for the knight from a given square.
	 *
	 * @param square The square from which moves are to be determined.
	 * @return An array of squares representing the possible moves.
	 * @throws InvalidSquareException 
	 */
	@Override
	public Square[] movesFrom(Square square) throws InvalidSquareException {
		ArrayList<Square> possibleMoves = new ArrayList<>();

		// Iterate through potential knight moves
		for (int i = -2; i < 3; i++) {
			if (i == 0)
				continue;
			char newRow = (char) (square.getRow() + i);
			char newCol = (char) (square.getCol() + 3 - Math.abs(i));

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}

			newRow = (char) (square.getRow() + i);
			newCol = (char) (square.getCol() - 3 + Math.abs(i));

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}
		}

		return possibleMoves.toArray(new Square[possibleMoves.size()]);
	}

}
