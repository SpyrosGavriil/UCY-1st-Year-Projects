package hw3.squarelotron;

import java.util.Scanner;

/**
 * This class provides a simple command-line interface to interact with the
 * Squarelotron.
 * 
 * 
 * @author Spyros Gavriil
 */
public class SquarelotronTest {
    /**
     * Main method to start the Squarelotron testing.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continueTesting = true;
        while (continueTesting) {
            int size = getSquarelotronSize(scanner);
            if(size != 4 && size != 5)
            	throw new IllegalArgumentException("Invalid Squarelotron size");

            Squarelotron squarelotron = createSquarelotron(scanner, size);

            System.out.println("Starting Squarelotron:");
            System.out.println(squarelotron);

            boolean continueFlipping = true;
            while (continueFlipping) {
                String flip = selectFlip(scanner);

                switch (flip) {
                    case "1":
                        // Perform upside-down flip (outer ring)
                        squarelotron = squarelotron.upsideDownFlip("outer");
                        break;
                    case "2":
                        // Perform upside-down flip (inner ring)
                        squarelotron = squarelotron.upsideDownFlip("inner");
                        break;
                    case "3":
                        // Perform left-right flip (outer ring)
                        squarelotron = squarelotron.leftRightFlip("outer");
                        break;
                    case "4":
                        // Perform left-right flip (inner ring)
                        squarelotron = squarelotron.leftRightFlip("inner");
                        break;
                    case "5":
                        // Perform main diagonal flip (outer ring)
                        squarelotron = squarelotron.mainDiagonalFlip("outer");
                        break;
                    case "6":
                        // Perform main diagonal flip (inner ring)
                        squarelotron = squarelotron.mainDiagonalFlip("inner");
                        break;
                    case "7":
                        // Perform inverse diagonal flip (outer ring)
                        squarelotron = squarelotron.inverseDiagonalFlip("outer");
                        break;
                    case "8":
                        // Perform inverse diagonal flip (inner ring)
                        squarelotron = squarelotron.inverseDiagonalFlip("inner");
                        break;
                    case "9":
                        // Rotate right 90 degrees
                        squarelotron.rotateRight(1);
                        break;
                    case "10":
                        // Rotate left 90 degrees
                        squarelotron.rotateRight(-1);
                        break;
                    case "11":
                        // Rotate right 180 degrees
                        squarelotron.rotateRight(2);
                        break;
                    case "12":
                        // Rotate left 180 degrees
                        squarelotron.rotateRight(-2);
                        break;
                    case "13":
                        // Rotate 0 degrees
                        squarelotron.rotateRight(0);
                        break;
                    case "14":
                        // Rotate right 270 degrees
                        squarelotron.rotateRight(3);
                        break;
                    case "15":
                        // Rotate left 270 degrees
                        squarelotron.rotateRight(-3);
                        break;
                    case "16":
                        // Rotate right 360 degrees
                        squarelotron.rotateRight(4);
                        break;
                    case "17":
                        // Rotate left 360 degrees
                        squarelotron.rotateRight(-4);
                        break;
                    case "18":
                        // Perform side flip (left)
                        squarelotron = squarelotron.sideFlip("left");
                        break;
                    case "19":
                        // Perform side flip (right)
                        squarelotron = squarelotron.sideFlip("right");
                        break;
                    case "20":
                        // Perform side flip (top)
                        squarelotron = squarelotron.sideFlip("top");
                        break;
                    case "21":
                        // Perform side flip (bottom)
                        squarelotron = squarelotron.sideFlip("bottom");
                        break;
                    case "exit":
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }

                System.out.println("New Squarelotron after flip:");
                System.out.println(squarelotron);
            }

            if (continueTesting) { 
                System.out.println("Do you want to test another Squarelotron? (yes/no)");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    continueTesting = false;
                }
            }
        }

        scanner.close();
    }

    /**
     * Asks the user to enter the size of the Squarelotron.
     *
     * @param scanner The scanner object to read user input.
     * @return The size of the Squarelotron.
     */
    private static int getSquarelotronSize(Scanner scanner) {
        System.out.println("Enter the size of the Squarelotron (4 for 4x4, 5 for 5x5):");
        return scanner.nextInt();
    }

    /**
     * Creates a Squarelotron based on user input.
     *
     * @param scanner The scanner object to read user input.
     * @param size    The size of the Squarelotron.
     * @return The created Squarelotron object.
     */
    private static Squarelotron createSquarelotron(Scanner scanner, int size) {
        System.out.println("Enter the numbers for the Squarelotron (separated by spaces):");
        int[] array = new int[size * size];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.nextLine(); // Consume newline
        Squarelotron sq = Squarelotron.makeSquarelotron(array);
    	return sq;
        	
    }

    /**
     * Displays the available flip options and asks the user to select one.
     *
     * @param scanner The scanner object to read user input.
     * @return The selected flip option.
     */
    private static String selectFlip(Scanner scanner) {
        System.out.println("Choose an option:");
        System.out.println("1. Upside-down flip (outer ring)");
        System.out.println("2. Upside-down flip (inner ring)");
        System.out.println("3. Left-right flip (outer ring)");
        System.out.println("4. Left-right flip (inner ring)");
        System.out.println("5. Main diagonal flip (outer ring)");
        System.out.println("6. Main diagonal flip (inner ring)");
        System.out.println("7. Inverse diagonal flip (outer ring)");
        System.out.println("8. Inverse diagonal flip (inner ring)");
        System.out.println("9. Rotate 90 degrees clockwise");
        System.out.println("10. Rotate 90 degrees counterclockwise");
        System.out.println("11. Rotate 180 degrees clockwise");
        System.out.println("12. Rotate 180 degrees counterclockwise");
        System.out.println("13. Rotate 0 degrees (no change)");
        System.out.println("14. Rotate 270 degrees clockwise");
        System.out.println("15. Rotate 270 degrees counterclockwise");
        System.out.println("16. Rotate 360 degrees clockwise");
        System.out.println("17. Rotate 360 degrees counterclockwise");
        System.out.println("18. Side flip (left)");
        System.out.println("19. Side flip (right)");
        System.out.println("20. Side flip (top)");
        System.out.println("21. Side flip (bottom)");
        System.out.println("Type 'exit' to stop.");

        return scanner.nextLine().trim().toLowerCase();
    }
}
