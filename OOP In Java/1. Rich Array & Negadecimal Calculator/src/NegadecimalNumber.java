package hw1;

/**
 * This class represents a negadecimal number.
 * 
 * @author SpyrosGavriil
 * @since 20/02/2024
 */
public class NegadecimalNumber {

	/**
	 * This presents the negadecimal number an in integer
	 */

	private int number;

	/**
	 * Constructs a NegadecimalNumber object with the given negadecimal number.
	 * 
	 * @param number The negadecimal number.
	 */
	public NegadecimalNumber(int number) {
		this.number = number;
	}

	/**
	 * Retrieves the negadecimal number.
	 * 
	 * @return The negadecimal number.
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Returns a string representation of the negadecimal number.
	 * 
	 * @return The string representation of the negadecimal number.
	 */
	public String toString() {
		return Integer.toString(this.number);
	}
}
