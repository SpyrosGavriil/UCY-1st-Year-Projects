package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a pawn chess piece.
 * 
 * @author Spyros Gavriil
 */
public class Pawn extends Piece {

	/**
	 * Constructs a Pawn piece with the given color.
	 *
	 * @param color The color of the pawn piece.
	 */
	public Pawn(Color color) {
		super(color);
	}

	/**
	 * Returns the algebraic notation name of the pawn piece.
	 *
	 * @return The algebraic notation name of the pawn piece ("P").
	 */
	@Override
	public String algebraicName() {
		return " ";
	}

	/**
	 * Computes the possible moves for the pawn from a given square.
	 *
	 * @param square The square from which moves are to be determined.
	 * @return An array of squares representing the possible moves.
	 * @throws InvalidSquareException 
	 */
	@Override
	public Square[] movesFrom(Square square) throws InvalidSquareException {
		ArrayList<Square> possibleMoves = new ArrayList<>();

		// If pawn is black
		if (this.getColor() == Color.BLACK) {
			if (isValidPosition((char) (square.getRow() + 1), square.getCol())) {
				Square newSquare = new Square(square.getCol(), (char) (square.getRow() - 1));
				possibleMoves.add(newSquare);
			}
			if (square.getRow() == '7') {
				Square newSquare = new Square(square.getCol(), (char) (square.getRow() - 2));
				possibleMoves.add(newSquare);
			}
		}
		// If pawn is white
		else {
			if (isValidPosition((char) (square.getRow() - 1), square.getCol())) {
				Square newSquare = new Square(square.getCol(), (char) (square.getRow() + 1));
				possibleMoves.add(newSquare);
			}
			if (square.getRow() == '2') {
				Square newSquare = new Square(square.getCol(), (char) (square.getRow() + 2));
				possibleMoves.add(newSquare);
			}
		}

		return possibleMoves.toArray(new Square[possibleMoves.size()]);
	}

}
