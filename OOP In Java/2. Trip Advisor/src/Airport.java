package hw2;

/**
 * Represents an airport with its coordinates and associated fees.
 */
public class Airport {
    private int x; // x-coordinate of the airport
    private int y; // y-coordinate of the airport
    private int fees; // fees associated with the airport

    /**
     * Constructs an Airport object with specified coordinates and fees.
     *
     * @param x     the x-coordinate of the airport
     * @param y     the y-coordinate of the airport
     * @param fees  the fees associated with the airport
     */
    public Airport(int x, int y, int fees) {
        this.x = x;
        this.y = y;
        this.fees = fees;
    }

    /**
     * Gets the fees associated with the airport.
     *
     * @return the fees associated with the airport
     */
    public int getFees() {
        return this.fees;
    }

    /**
     * Calculates the distance between two airports.
     *
     * @param a1    the first airport
     * @param a2    the second airport
     * @return      the distance between the two airports
     */
    public static int getDistance(Airport a1, Airport a2) {
        return (int) Math.ceil(Math.sqrt(Math.pow(a1.x - a2.x, 2) + Math.pow(a1.y - a2.y, 2)));
    }
}
