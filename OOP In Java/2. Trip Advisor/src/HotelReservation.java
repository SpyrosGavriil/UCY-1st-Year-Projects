package hw2;

/**
 * Represents a hotel reservation made by a customer for a specific hotel, room type, and duration.
 */
public class HotelReservation extends Reservation {
    private Hotel hotel; // The hotel for the reservation
    private String type; // The type of room reserved
    private int numOfNights; // The number of nights for the reservation
    private int pricePerNight; // The price per night of the reserved room

    /**
     * Constructs a hotel reservation with the specified parameters.
     *
     * @param name       the name of the reservation holder
     * @param hotel      the hotel for the reservation
     * @param type       the type of room reserved
     * @param numOfNights the number of nights for the reservation
     */
    public HotelReservation(String name, Hotel hotel, String type, int numOfNights) {
        super(name);
        this.hotel = hotel;
        this.type = type;
        this.numOfNights = numOfNights;
        pricePerNight = hotel.reserveRoom(type);
    }

    /**
     * Gets the number of nights for the reservation.
     *
     * @return the number of nights for the reservation
     */
    public int getNumOfNights() {
        return this.numOfNights;
    }

    /**
     * Calculates the cost of the hotel reservation.
     *
     * @return the total cost of the hotel reservation
     */
    public int getCost() {
        return this.numOfNights * this.pricePerNight;
    }

    /**
     * Compares this hotel reservation to the specified object for equality.
     *
     * @param obj the object to compare
     * @return true if the reservations are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        HotelReservation other = (HotelReservation) obj;

        return this.reservationName().equals(other.reservationName()) &&
                this.hotel.equals(other.hotel) &&
                this.type.equals(other.type) &&
                this.numOfNights == other.numOfNights &&
                this.getCost() == other.getCost();
    }
}
