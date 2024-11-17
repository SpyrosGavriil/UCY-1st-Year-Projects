package hw3.chess;

/**
 * A checked exception indicating that an attempt was made to access an invalid square in a chess game.
 * This exception is typically thrown to signal exceptional conditions where a specific square requested by
 * the caller does not exist on the chessboard. Checked exceptions are chosen here to enforce handling
 * by the calling code, ensuring that the programmer explicitly addresses the possibility of invalid square
 * access. By making it a checked exception, the compiler enforces handling, leading to more robust and
 * reliable error management. This approach is suitable for situations where the absence of a square may
 * be recoverable or requires special handling logic. Additionally, using a checked exception enhances code
 * documentation and readability, as it clearly communicates to callers that the method may throw this
 * exception and needs to be handled appropriately. Checked exceptions are particularly beneficial in
 * scenarios where the error can be anticipated and addressed within the program logic, rather than
 * being indicative of a critical system failure or external factors beyond the program's control.
 * 
 *@author Spyros Gavriil 
 *
 */

public class InvalidSquareException extends Exception {

    /**
     * Constructs a new InvalidSquareException with the specified detail message.
     * @param message the detail message.
     */
    public InvalidSquareException(String message) {
        super(message);
    }
}
