package hw2;

/**
 * Represents a hotel with its name and available rooms.
 */
public class Hotel {
    private String name; // Name of the hotel
    private Room[] rooms; // Array of rooms in the hotel

    /**
     * Constructs a hotel with the specified name and array of rooms.
     *
     * @param name  the name of the hotel
     * @param rooms an array of rooms available in the hotel
     */
    public Hotel(String name, Room[] rooms) {
        this.name = name;
        this.rooms = new Room[rooms.length];
        for (int i = 0; i < this.rooms.length; i++)
            this.rooms[i] = new Room(rooms[i]);
    }

    /**
     * Reserves a room of the specified type in the hotel.
     *
     * @param type the type of room to reserve
     * @return the price of the reserved room
     * @throws IllegalArgumentException if the specified room type is invalid
     */
    public int reserveRoom(String type) {
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                room.changeAvailability();
                return room.getPrice();
            }
        }
        throw new IllegalArgumentException("Invalid room type: " + type);
    }

    /**
     * Cancels a room reservation of the specified type in the hotel.
     *
     * @param type the type of room reservation to cancel
     * @return true if the room reservation was successfully canceled, false otherwise
     */
    public boolean cancelRoom(String type) {
        return Room.makeRoomAvailable(rooms, type);
    }
}
