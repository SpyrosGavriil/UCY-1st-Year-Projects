package hw3.squarelotron;

/**
 * An interface representing common operations for a Squarelotron.
 * 
 * @author Spyros Gavriil
 */
public interface SquarelotronMethods {

    /**
     * Returns an array containing all the numbers in the Squarelotron in row-major order.
     *
     * @return An array containing all the numbers in the Squarelotron.
     */
    public int[] numbers();

    /**
     * Flips the Squarelotron upside down either on the outer or inner ring.
     *
     * @param ring The ring to flip (outer or inner).
     * @return A new Squarelotron object representing the flipped Squarelotron.
     */
    public Squarelotron upsideDownFlip(String ring);

    /**
     * Flips the Squarelotron left to right either on the outer or inner ring.
     *
     * @param ring The ring to flip (outer or inner).
     * @return A new Squarelotron object representing the flipped Squarelotron.
     */
    public Squarelotron leftRightFlip(String ring);

    /**
     * Flips the Squarelotron along the inverse diagonal either on the outer or inner ring.
     *
     * @param ring The ring to flip (outer or inner).
     * @return A new Squarelotron object representing the flipped Squarelotron.
     */
    public Squarelotron inverseDiagonalFlip(String ring);

    /**
     * Flips the Squarelotron along the main diagonal either on the outer or inner ring.
     *
     * @param ring The ring to flip (outer or inner).
     * @return A new Squarelotron object representing the flipped Squarelotron.
     */
    public Squarelotron mainDiagonalFlip(String ring);

    /**
     * Flips the Squarelotron on a specified side (left, right, top, or bottom).
     *
     * @param side The side to flip (left, right, top, or bottom).
     * @return A new Squarelotron object representing the flipped Squarelotron.
     */
    public Squarelotron sideFlip(String side);

    /**
     * Rotates the Squarelotron to the right by the specified number of turns.
     *
     * @param numberOfTurns The number of clockwise turns.
     */
    public void rotateRight(int numberOfTurns);

    /**
     * Compares this Squarelotron with another object for equality.
     *
     * @param object The object to compare.
     * @return true if the Squarelotrons are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object);

    /**
     * Returns a string representation of the Squarelotron.
     *
     * @return A string representation of the Squarelotron.
     */
    @Override
    public String toString();
}
