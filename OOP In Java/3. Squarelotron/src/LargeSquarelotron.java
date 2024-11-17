package hw3.squarelotron;

/**
 * Represents a large squarelotron.
 * A large squarelotron is a type of Squarelotron with a size of 5x5.
 * 
 * @author Spyros Gavriil
 */
public class LargeSquarelotron extends Squarelotron {

    /**
     * Constructs a LargeSquarelotron with the given array of numbers.
     * The array represents the initial configuration of the LargeSquarelotron.
     *
     * @param array The array of numbers to initialize the LargeSquarelotron.
     */
    public LargeSquarelotron(int[] array) {
        super(array); // LargeSquarelotron has a size of 5x5
    }
}
