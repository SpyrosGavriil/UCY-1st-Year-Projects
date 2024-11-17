package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a rook chess piece.
 * 
 * @author Spyros Gavriil
 */
public class Rook extends Piece {

	/**
	 * Constructs a Rook piece with the given color.
	 *
	 * @param color The color of the rook piece.
	 */
	public Rook(Color color) {
		super(color);
	}

	/**
	 * Returns the algebraic notation name of the rook piece.
	 *
	 * @return The algebraic notation name of the rook piece ("R").
	 */
	@Override
	public String algebraicName() {
		return "R";
	}

	/**
	 * Computes the possible moves for the rook from a given square.
	 *
	 * @param square The square from which moves are to be determined.
	 * @return An array of squares representing the possible moves.
	 * @throws InvalidSquareException 
	 */
	@Override
	public Square[] movesFrom(Square square) throws InvalidSquareException {
		ArrayList<Square> possibleMoves = new ArrayList<>();

		// Rook-like moves (horizontal and vertical)
		for (int i = -7; i < 8; i++) {
			char newRow = (char) (square.getRow());
			char newCol = (char) (square.getCol() + i);

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}

			newRow = (char) (square.getRow() + i);
			newCol = (char) (square.getCol());

			// Check if the new position is valid and not the same as the current position
			if (isValidPosition(newRow, newCol) && !(newRow == square.getRow() && newCol == square.getCol())) {
				Square newSquare = new Square(newCol, newRow);
				possibleMoves.add(newSquare);
			}
		}

		return possibleMoves.toArray(new Square[possibleMoves.size()]);
	}

}
