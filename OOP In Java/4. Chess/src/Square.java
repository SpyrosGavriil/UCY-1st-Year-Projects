package hw3.chess;

/**
 * Represents a square on a chessboard.
 * 
 * @author Spyros Gavriil
 */
public class Square {

    /** The row of the square. */
    private char row;

    /** The column of the square. */
    private char col;

    /**
     * Constructs a Square with the given column and row.
     *
     * @param col The column of the square (from 'a' to 'h').
     * @param row The row of the square (from '1' to '8').
     * @throws InvalidSquareException If the provided square is invalid.
     */
    public Square(char col, char row) throws InvalidSquareException {
        if(row < '1' || row > '8' || col < 'a' || col > 'h') {
            throw new InvalidSquareException("Invalid square: " + row + col);
        }
        this.row = row;
        this.col = col;
    }

    /**
     * Constructs a Square with the given name.
     *
     * @param name The name of the square in algebraic notation (e.g., "a1").
     * @throws InvalidSquareException 
     */
    public Square(String name) throws InvalidSquareException {
        this(name.charAt(0), name.charAt(1));
    }

    /**
     * Returns a string representation of the square.
     *
     * @return The string representation of the square (e.g., "a1").
     */
    public String toString() {
        return this.col + "" + this.row;
    }

    /**
     * Returns the row of the square.
     *
     * @return The row of the square.
     */
    public char getRow() {
        return this.row;
    }

    /**
     * Returns the column of the square.
     *
     * @return The column of the square.
     */
    public char getCol() {
        return this.col;
    }

    /**
     * Checks whether this square is equal to another object.
     *
     * @param obj The object to compare.
     * @return True if the squares are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Square square = (Square) obj;
        return row == square.row && col == square.col;
    }
}