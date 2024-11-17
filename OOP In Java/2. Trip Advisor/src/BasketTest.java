package hw2;

import static org.junit.Assert.*;
import org.junit.Test;

public class BasketTest {

    @Test
    public void testAddAndGetNumOfReservations() {
        Basket basket = new Basket();
        assertEquals(0, basket.getNumOfReservations());

        Airport departureAirport = new Airport(0, 0, 0);
        Airport arrivalAirport = new Airport(3, 4, 0);
        FlightReservation flightReservation = new FlightReservation("Flight 1", departureAirport, arrivalAirport);

        basket.add(flightReservation);
        assertEquals(1, basket.getNumOfReservations());
    }
//
//    @Test
//    public void testRemove() {
//        Basket basket = new Basket();
//        Airport departureAirport1 = new Airport(0, 0, 0);
//        Airport arrivalAirport1 = new Airport(3, 4, 0);
//        FlightReservation flightReservation1 = new FlightReservation("Flight 1", departureAirport1, arrivalAirport1);
//
//        Airport departureAirport2 = new Airport(1, 1, 0);
//        Airport arrivalAirport2 = new Airport(5, 6, 0);
//        FlightReservation flightReservation2 = new FlightReservation("Flight 2", departureAirport2, arrivalAirport2);
//
//        basket.add(flightReservation1);
//        basket.add(flightReservation2);
//
//        assertTrue(basket.remove(flightReservation1));
//        assertFalse(basket.remove(new FlightReservation("Flight 3", departureAirport1, arrivalAirport1)));
//    }
//
//    @Test
//    public void testClear() {
//        Basket basket = new Basket();
//        Airport departureAirport = new Airport(0, 0, 0);
//        Airport arrivalAirport = new Airport(3, 4, 0);
//        FlightReservation flightReservation = new FlightReservation("Flight 1", departureAirport, arrivalAirport);
//
//        basket.add(flightReservation);
//        basket.clear();
//        assertEquals(0, basket.getNumOfReservations());
//    }
//
    @Test
    public void testGetTotalCost() {
        Basket basket = new Basket();

        // Creating airports for flight reservations
        Airport departureAirport1 = new Airport(0, 0, 0);
        Airport arrivalAirport1 = new Airport(3, 4, 0);
        Airport departureAirport2 = new Airport(1, 1, 0);
        Airport arrivalAirport2 = new Airport(5, 6, 0);

        // Creating flight reservations
        FlightReservation flightReservation1 = new FlightReservation("Flight 1", departureAirport1, arrivalAirport1);
        FlightReservation flightReservation2 = new FlightReservation("Flight 2", departureAirport2, arrivalAirport2);

        // Creating rooms for hotel reservations
        Room room1 = new Room("double"); // Example room for Hotel 1
        Room room2 = new Room("king");   // Example room for Hotel 2

        // Creating hotels
        Hotel hotel1 = new Hotel("Hotel 1", new Room[]{room1});
        Hotel hotel2 = new Hotel("Hotel 2", new Room[]{room2});

        // Creating hotel reservations
        HotelReservation hotelReservation1 = new HotelReservation("Hotel 1", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Hotel 2", hotel2, "king", 3);

        // Adding reservations to the basket
        basket.add(flightReservation1);
        basket.add(flightReservation2);
        basket.add(hotelReservation1);
        basket.add(hotelReservation2);

        // Expected total cost: cost of flightReservation1 + cost of flightReservation2 + cost of hotelReservation1 + cost of hotelReservation2
        // You should replace these values with actual costs based on your logic
        int expectedTotalCost = flightReservation1.getCost() + flightReservation2.getCost() + hotelReservation1.getCost() + hotelReservation2.getCost();

        assertEquals(expectedTotalCost, basket.getTotalCost());
    }

	private Basket basket = new Basket();
	private Reservation reservation1 = new HotelReservation("John Doe",
			new Hotel("Test Hotel", new Room[] { new Room("double") }), "double", 2),
			reservation2 = new FlightReservation("John Doe", new Airport(0, 0, 100), new Airport(100, 100, 200));

	@Test
	public void testAdd() {
		assertEquals("Basket should initially be empty", 0, basket.getNumOfReservations());
		basket.add(reservation1);
		assertEquals("Basket should contain 1 reservation after add", 1, basket.getNumOfReservations());
	}

	@Test
	public void testRemove() {
		basket.add(reservation1);
		assertTrue("Remove should return true when removing existing reservation", basket.remove(reservation1));
		assertEquals("Basket should be empty after removal", 0, basket.getNumOfReservations());
		assertFalse("Remove should return false when removing non-existent reservation", basket.remove(reservation2));
	}

	@Test
	public void testGetProducts() {
		basket.add(reservation1);
		basket.add(reservation2);
		Reservation[] reservations = basket.getProducts();
		assertEquals("GetProducts should return an array with 2 reservations", 2, reservations.length);
		// assertEquals("The reservation returned by GetProducts should match the added
		// reservation", reservation1, reservations[0]);
	}

//	@Test
//	public void testGetTotalCost() {
//		basket.add(reservation1);
//		basket.add(reservation2);
//		int expectedCost = reservation1.getCost() + reservation2.getCost();
//		assertEquals("Total cost should be the sum of all reservations in the basket", expectedCost,
//				basket.getTotalCost());
//	}

	@Test
	public void testClear() {
		basket.add(reservation1);
		basket.add(reservation2);
		System.out.println(basket.toString());
		basket.clear();
		assertEquals("Basket should be empty after clear", 0, basket.getNumOfReservations());
		System.out.println(basket.toString());
	}

}
