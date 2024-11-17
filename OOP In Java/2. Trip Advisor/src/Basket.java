package hw2;

/**
 * Represents a basket for holding reservations.
 */
public class Basket {
    private Reservation[] reservations; // Array to store reservations

    /**
     * Constructs an empty basket.
     */
    public Basket() {
        reservations = new Reservation[0];
    }

    /**
     * Retrieves a copy of the reservations in the basket.
     *
     * @return an array of reservations
     */
    public Reservation[] getProducts() {
        Reservation[] copy = new Reservation[reservations.length];

        for (int i = 0; i < copy.length; i++)
            copy[i] = reservations[i];

        return copy;
    }

    /**
     * Adds a reservation to the basket.
     *
     * @param res the reservation to add
     * @return the new number of reservations in the basket
     */
    public int add(Reservation res) {
        Reservation[] newArray = new Reservation[reservations.length + 1];

        for (int i = 0; i < reservations.length; i++)
            newArray[i] = reservations[i];

        newArray[reservations.length] = res; // Corrected index to add the new reservation
        this.reservations = newArray;
        return newArray.length;
    }

    /**
     * Removes a reservation from the basket.
     *
     * @param res the reservation to remove
     * @return true if the reservation was successfully removed, false otherwise
     */
    public boolean remove(Reservation res) {
        int index = -1; // Initialize index to -1 to indicate not found

        // Find the index of the reservation to remove
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i].equals(res)) {
                index = i;
                break;
            }
        }

        // If the reservation is found
        if (index != -1) {
            Reservation[] newArray = new Reservation[reservations.length - 1];
            int newIndex = 0;

            // Copy reservations from the original array to the new array, excluding the
            // removed reservation
            for (int i = 0; i < reservations.length; i++) {
                if (i != index) {
                    newArray[newIndex] = reservations[i];
                    newIndex++;
                }
            }

            // Update the reservations array with the new array
            this.reservations = newArray;
            return true;
        } else {
            // If the reservation is not found, return false
            return false;
        }
    }

    /**
     * Clears all reservations from the basket.
     */
    public void clear() {
        this.reservations = new Reservation[0];
    }

    /**
     * Gets the number of reservations in the basket.
     *
     * @return the number of reservations
     */
    public int getNumOfReservations() {
        return this.reservations.length;
    }

    /**
     * Calculates the total cost of all reservations in the basket.
     *
     * @return the total cost of all reservations
     */
    public int getTotalCost() {
        int total = 0;

        for (Reservation res : reservations) {
            total += res.getCost();
        }

        return total;
    }
}
