package hw3.squarelotron;

/**
 * Represents a small squarelotron.
 * A small squarelotron is a type of Squarelotron with a size of 4x4.
 * 
 * @author Spyros Gavriil
 */
public class SmallSquarelotron extends Squarelotron {

    /**
     * Constructs a SmallSquarelotron with the given array of numbers.
     * The array represents the initial configuration of the SmallSquarelotron.
     *
     * @param array The array of numbers to initialize the SmallSquarelotron.
     */
    public SmallSquarelotron(int[] array) {
        super(array); // SmallSquarelotron has a size of 4x4
    }
}
