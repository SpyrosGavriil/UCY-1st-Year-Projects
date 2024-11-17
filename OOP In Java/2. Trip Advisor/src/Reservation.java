package hw2;

/**
 * Represents an abstract reservation made by a customer.
 */
public abstract class Reservation {
    private String name; // Name of the reservation holder

    /**
     * Constructs a reservation with the specified name.
     *
     * @param name the name of the reservation holder
     */
    public Reservation(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the reservation holder.
     *
     * @return the name of the reservation holder
     */
    public final String reservationName() {
        return this.name;
    }

    /**
     * Abstract method to get the cost of the reservation.
     * The according cost is calculated by this class's subclasses
     *
     * @return the cost of the reservation
     */
    public abstract int getCost();

    /**
     * Abstract method to compare this reservation to the specified object for equality.
     * The according equals() is calculated by this class's subclasses
     *
     * @param obj the object to compare
     * @return true if the reservations are equal, false otherwise
     */
    public abstract boolean equals(Object obj);
}
