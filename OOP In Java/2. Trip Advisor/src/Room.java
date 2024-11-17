package hw2;

/**
 * Represents a room in a hotel with its type, price, and availability.
 */
public class Room {
    private String type; // Type of the room
    private int price; // Price of the room
    private boolean availability; // Availability status of the room

    /**
     * Constructs a room of the specified type.
     *
     * @param type the type of the room
     * @throws IllegalArgumentException if the specified room type is invalid
     */
    public Room(String type) {
        if (type.equalsIgnoreCase("double")) {
            this.type = type;
            this.price = 90 * 100; // Assuming the price is in cents
            this.availability = true;
        } else if (type.equalsIgnoreCase("queen")) {
            this.type = type;
            this.price = 110 * 100; // Assuming the price is in cents
            this.availability = true;
        } else if (type.equalsIgnoreCase("king")) {
            this.type = type;
            this.price = 150 * 100; // Assuming the price is in cents
            this.availability = true;
        } else {
            throw new IllegalArgumentException("Invalid room type: " + type);
        }
    }

    /**
     * Constructs a room with the same attributes as another room.
     *
     * @param r the room to copy attributes from
     */
    public Room(Room r) {
        this.type = r.type;
        this.price = r.price;
        this.availability = r.availability;
    }

    /**
     * Retrieves the type of the room.
     *
     * @return the type of the room
     */
    public String getType() {
        return this.type;
    }

    /**
     * Retrieves the price of the room.
     *
     * @return the price of the room
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Toggles the availability status of the room.
     */
    public void changeAvailability() {
        this.availability = !this.availability;
    }

    /**
     * Finds an available room of the specified type from an array of rooms.
     *
     * @param rooms an array of rooms to search from
     * @param type  the type of room to find
     * @return an available room of the specified type, or null if not found
     */
    public static Room findAvailableRoom(Room[] rooms, String type) {
        for (Room room : rooms) {
            if (room.type.equals(type) && room.availability) {
                return new Room(room);
            }
        }
        return null; // Return null if no available room of the specified type is found
    }

    /**
     * Makes a room of the specified type available from an array of rooms.
     *
     * @param rooms an array of rooms to search from
     * @param type  the type of room to make available
     * @return true if the room is successfully made available, false otherwise
     */
    public static boolean makeRoomAvailable(Room[] rooms, String type) {
        for (Room room : rooms) {
            if (room.type.equals(type) && !room.availability) {
                room.changeAvailability();
                return true;
            }
        }
        return false;
    }
}
