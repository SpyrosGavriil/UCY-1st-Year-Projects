package hw2;

/**
 * Represents a bed and breakfast reservation, which is a type of hotel reservation.
 */
public class BnBReservation extends HotelReservation {

    /**
     * Constructs a hotel reservation with the specified parameters.
     *
     * @param name        the name of the guest making the reservation
     * @param hotel       the hotel where the reservation is made
     * @param type        the type of room reserved
     * @param numOfNights the number of nights for the reservation
     */
    public BnBReservation(String name, Hotel hotel, String type, int numOfNights) {
        super(name, hotel, type, numOfNights);
    }

    /**
     * Calculates the cost of the bed and breakfast reservation.
     * The cost is calculated based on the base cost from the hotel plus additional cost per night.
     *
     * @return the total cost of the reservation
     */
    public int getCost() {
        return super.getCost() + super.getNumOfNights() * 10 * 100; // Assuming additional cost per night is $10
    }
}
