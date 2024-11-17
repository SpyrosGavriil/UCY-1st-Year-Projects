package hw1;

/**
 * This class provides methods to perform arithmetic operations on
 * {@link NegadecimalNumber}.
 * 
 * @author Spyros Gavriil
 * @since 20/02/2024
 */
public class NegadecimalCalculator {

	/**
	 * The first negadecimal number used in the calculation
	 */
	private NegadecimalNumber num1;

	/**
	 * The second negadecimal number used in the calculation
	 */
	private NegadecimalNumber num2;

	/**
	 * The arithmetic operator (+, -, *, /) used in the calculation
	 */
	private char operator;

	/**
	 * Constructs a NegadecimalCalculator object with the given negadecimal numbers
	 * and operator.
	 * 
	 * @param num1     The first negadecimal number.
	 * @param operator The arithmetic operator (+, -, *, /).
	 * @param num2     The second negadecimal number.
	 */
	public NegadecimalCalculator(int num1, char operator, int num2) {
		this.num1 = new NegadecimalNumber(num1); // Construct the first negadecimal number
		this.num2 = new NegadecimalNumber(num2); // Construct the second negadecimal number
		this.operator = operator; // Initialize the operator
	}

	/**
	 * Converts an integer to its representation in the negative base.
	 * 
	 * @param integer The integer to convert.
	 * @param base    The negative base.
	 * @return The negadecimal representation of the integer.
	 */
	public String toNegativeBase(int integer, int base) {
		String result = "";
		int number = integer;
		while (number != 0) {
			int i = number % base;
			number /= base;
			if (i < 0) {
				i += Math.abs(base);
				number++;
			}
			result = i + result;
		}
		return result;
	}

	/**
	 * Converts a negadecimal number to its decimal representation.
	 * 
	 * @param integer The negadecimal number to convert.
	 * @return The decimal representation of the negadecimal number.
	 */
	public int toDecimalBase(int integer) {
		int result = 0; // Initialize the result to 0
		int number = integer; // Copy the input number to avoid modification
		int multiplier = 1; // Initialize the multiplier to 1

		// Iterate until the input number becomes 0
		while (number != 0) {
			// Extract the rightmost digit of the negadecimal number and multiply it
			result += (number % 10) * multiplier;

			// Update the multiplier by multiplying it by -10
			multiplier *= -10;

			// Remove the end digit from the negadecimal number
			number /= 10;
		}

		return result; // Return the calculated decimal representation
	}

	/**
	 * Performs the arithmetic operation specified by the operator on the given
	 * negadecimal numbers.
	 * 
	 * @return The result of the arithmetic operation in negadecimal representation.
	 */
	public int calculate() {
		int result = 0; // Initialize the result variable
		int num1 = toDecimalBase(this.num1.getNumber()); // Convert the first negadecimal number to decimal
		int num2 = toDecimalBase(this.num2.getNumber()); // Convert the second negadecimal number to decimal

		// Perform arithmetic operation based on the operator
		switch (operator) {
		case '+': // Addition
			result = num1 + num2;
			break;
		case '-': // Subtraction
			result = num1 - num2;
			break;
		case '*': // Multiplication
			result = num1 * num2;
			break;
		case '/': // Division
			result = num1 / num2;
			break;
		}
		// Convert the result to negadecimal representation and return
		return Integer.parseInt(toNegativeBase(result, -10));
	}

	/**
	 * Retrieves the first negadecimal number used in the calculation.
	 * 
	 * @return The first negadecimal number.
	 */
	public NegadecimalNumber getNum1() {
		return this.num1;
	}

	/**
	 * Retrieves the second negadecimal number used in the calculation.
	 * 
	 * @return The second negadecimal number.
	 */
	public NegadecimalNumber getNum2() {
		return this.num2;
	}

	/**
	 * Retrieves the arithmetic operator used in the calculation.
	 * 
	 * @return The arithmetic operator.
	 */
	public char getOperator() {
		return this.operator;
	}

}
