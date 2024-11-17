package hw2;

/**
 * Represents a customer who can make reservations and manage their balance.
 */
public class Customer {
    private String name; // Name of the customer
    private int balance; // Balance of the customer
    private Basket basket; // Basket to hold reservations

    /**
     * Constructs a customer with the specified name and initial balance.
     *
     * @param name    the name of the customer
     * @param balance the initial balance of the customer
     */
    public Customer(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.basket = new Basket(); // Create the customer's basket
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the balance of the customer.
     *
     * @return the balance of the customer
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Retrieves the basket of the customer.
     *
     * @return the basket of the customer
     */
    public Basket getBasket() {
        return this.basket;
    }

    /**
     * Adds funds to the customer's balance.
     *
     * @param funds the amount of funds to add
     * @return the new balance of the customer
     * @throws IllegalArgumentException if funds is negative
     */
    public int addFunds(int funds) {
        if (funds < 0)
            throw new IllegalArgumentException("Cannot add negative funds");
        this.balance += funds;
        return this.balance;
    }

    /**
     * Adds a reservation to the customer's basket.
     *
     * @param res the reservation to add
     * @return the new number of reservations in the basket
     * @throws IllegalArgumentException if the reservation's name doesn't match the customer's name
     */
    public int addToBasket(Reservation res) {
        if (res.reservationName().equals(this.name)) {
            this.basket.add(res);
            return this.basket.getNumOfReservations();
        }
        throw new IllegalArgumentException("Name of reservation doesn't match with the customer's name");
    }

    /**
     * Adds a hotel reservation to the customer's basket.
     *
     * @param hotel         the hotel for the reservation
     * @param type          the type of room reserved
     * @param nights        the number of nights for the reservation
     * @param wantsBreakfast true if the customer wants breakfast included, false otherwise
     * @return the new number of reservations in the basket
     */
    public int addToBasket(Hotel hotel, String type, int nights, boolean wantsBreakfast) {
        if (wantsBreakfast) {
            basket.add(new BnBReservation(name, hotel, type, nights));
        } else {
            basket.add(new HotelReservation(name, hotel, type, nights));
        }
        return basket.getNumOfReservations();
    }

    /**
     * Adds a flight reservation to the customer's basket.
     *
     * @param a1 the departure airport
     * @param a2 the arrival airport
     * @return the new number of reservations in the basket
     */
    public int addToBasket(Airport a1, Airport a2) {
        this.basket.add(new FlightReservation(this.name, a1, a2));
        return this.basket.getNumOfReservations();
    }

    /**
     * Removes a reservation from the customer's basket.
     *
     * @param res the reservation to remove
     * @return true if the reservation was successfully removed, false otherwise
     */
    public boolean removeFromBasket(Reservation res) {
        return this.basket.remove(res);
    }

    /**
     * Checks out the reservations in the customer's basket.
     * Deducts the total cost from the customer's balance.
     *
     * @return the new balance of the customer after checkout
     * @throws IllegalArgumentException if the basket is empty
     * @throws IllegalStateException    if the balance is insufficient for checkout
     */
    public int checkOut() {
        int total = 0;
        Reservation[] newArray = this.basket.getProducts();

        if (newArray.length == 0)
            throw new IllegalArgumentException("Can't check out: Basket is empty!");

        for (Reservation res : newArray)
            total += res.getCost();

        if (this.balance < total)
            throw new IllegalStateException("Can't check out: Insufficient balance");
        else {
            this.basket.clear();
            this.balance -= total;
            return this.balance;
        }
    }
}
