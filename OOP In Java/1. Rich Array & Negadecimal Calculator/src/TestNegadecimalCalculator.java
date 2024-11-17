package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class provides a main method to test the {@link NegadecimalCalculator}
 * class by reading expressions from a file and performing calculations.
 * 
 * @author SpyrosGavriil
 * @since 20/02/2024
 */
public class TestNegadecimalCalculator {

	/**
	 * The main method reads expressions from a file, calculates the results using
	 * NegadecimalCalculator, and prints the expressions along with their results.
	 * 
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {
		String fileName = "input.txt"; // Name of the input file
		readExpressionsFromFile(fileName); // Read expressions from file
	}

	/**
	 * Reads arithmetic expressions from a file and processes each expression.
	 * 
	 * @param fileName the name of the file to read arithmetic expressions from
	 */
	public static void readExpressionsFromFile(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Process each line containing an arithmetic expression
				processExpression(line);
			}
		} catch (IOException e) {
			// If an IOException occurs while reading the file, print the stack trace
			e.printStackTrace();
		}
	}

	/**
	 * Processes each arithmetic expression.
	 * 
	 * @param expression The arithmetic expression to process.
	 */
	private static void processExpression(String expression) {
		// Split the expression into parts based on space delimiter
		String[] parts = expression.split(" ");

		// Check if the expression consists of three parts (operand operator operand)
		if (parts.length == 3) {
			boolean checkExpression = true;

			// Iterate over each part of the expression
			for (int i = 0; i < parts.length; i++) {
				// Check the first and third parts to ensure they are numeric operands
				if (i != 1)
					checkExpression = isNumeric(parts[i]);
				// Check the second part to ensure it's a valid operator
				else
					checkExpression = isOperator(parts[i]);
				// If any part is not valid, print an error message and end
				if (!checkExpression) {
					System.out.println("Wrong Expression");
					return;
					// If division by zero, print an error message and end
				} else if (parts[1].equals("/") && parts[2].equals("0")) {
					System.out.println("Cannot divide by 0");
					return;
				}
			}
			// If all parts are valid, construct a calculator object to handle the equation
			NegadecimalCalculator calculator = new NegadecimalCalculator(Integer.parseInt(parts[0]), parts[1].charAt(0),
					Integer.parseInt(parts[2]));
			int result = calculator.calculate();
			// Print the expression and its result
			System.out.println(calculator.getNum1() + " " + calculator.getOperator() + " " + calculator.getNum2()
					+ " = " + result);

		} else {
			// If the expression does not consist of three parts, return "Wrong Expression"
			System.out.println("Wrong Expression");
			return;
		}
	}

	/**
	 * Checks if a string represents a numeric value.
	 * 
	 * @param str The string to check.
	 * @return true if the string represents a numeric value, false otherwise.
	 */
	private static boolean isNumeric(String str) {
		// Iterate over each character in the string
		for (int i = 0; i < str.length(); i++) {
			// Check if the character is a digit
			if (!Character.isDigit(str.charAt(i))) {
				// If any character is not a digit, return false
				return false;
			}
		}
		// If all characters are digits, return true
		return true;
	}

	/**
	 * Checks if a string represents a valid operator (+, -, *, /).
	 * 
	 * @param str The string to check.
	 * @return true if the string represents a valid operator, false otherwise.
	 */
	private static boolean isOperator(String str) {
		// Define a string containing all valid operators
		String operators = "+-*/";
		// Check if the string has length 1 and is contained within the operators string
		return str.length() == 1 && operators.contains(str);
	}
}
