package hw2;

/**
 * Represents a flight reservation for traveling between two airports.
 */
public class FlightReservation extends Reservation {
    private Airport departure; // Departure airport
    private Airport arrival; // Arrival airport

    /**
     * Constructs a flight reservation with the specified name, departure, and arrival airports.
     *
     * @param name      the name of the reservation holder
     * @param departure the departure airport
     * @param arrival   the arrival airport
     * @throws IllegalArgumentException if departure and arrival airports are the same
     */
    public FlightReservation(String name, Airport departure, Airport arrival) {
        super(name);
        if (!departure.equals(arrival)) {
            this.departure = departure;
            this.arrival = arrival;
        } else
            throw new IllegalArgumentException("Departure and arrival airports can't be the same");
    }

    /**
     * Calculates the cost of the flight reservation based on departure and arrival airports.
     *
     * @return the total cost of the flight reservation
     */
    public int getCost() {
        return (int) Math.ceil(this.departure.getFees() + this.arrival.getFees() + 53.75
                + (Airport.getDistance(departure, arrival) / 167.52 * 1.24));
    }

    /**
     * Compares this flight reservation to the specified object for equality.
     *
     * @param obj the object to compare
     * @return true if the reservations are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        FlightReservation other = (FlightReservation) obj;

        return this.reservationName().equals(other.reservationName()) &&
                this.departure.equals(other.departure) &&
                this.arrival.equals(other.arrival);
    }
}
