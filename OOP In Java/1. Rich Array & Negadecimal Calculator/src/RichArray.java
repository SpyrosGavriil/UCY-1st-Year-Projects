package hw1;

/**
 * This class represents a 2D array with additional functionalities.
 * 
 * @author Spyros Gavriil
 * @since 20/02/2024
 */

public class RichArray {

	/**
	 * This presents the 2D array of a {@link RichArray} array;
	 */

	private int[][] array;

	/**
	 * Constructs a new RichArray with the given 2D array.
	 *
	 * @param array The 2D array to initialize the RichArray with.
	 */

	public RichArray(int[][] array) {
		this.array = new int[array.length][array[0].length]; // Initialize the array with same dimensions
		// Copy elements from the given array to the internal array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				this.array[i][j] = array[i][j];
			}
		}
	}

	/**
	 * Constructs a new RichArray with a single row and the given array elements,
	 * when given a 1D array.
	 *
	 * @param array The array whose elements are used to initialize the single row
	 *              of the RichArray.
	 */

	public RichArray(int[] array) {
		this.array = new int[1][array.length]; // Initialize the array with one row and same length as input array
		// Copy elements from the given array to the internal array
		for (int i = 0; i < array.length; i++) {
			this.array[0][i] = array[i];
		}
	}

	/**
	 * This method reverses the {@link RichArray}. If the RichArray has only one
	 * element, returns a new RichArray with the same element. If the RichArray has
	 * multiple elements, reverses each row individually.
	 *
	 * @return A new RichArray with the elements reversed.
	 */

	public RichArray reverse() {
		if (this.array.length == 1 && this.array[0].length == 1) {
			// If RichArray has only one element, return a new RichArray with the same
			// element
			return new RichArray(this.array);
		} else {
			// If RichArray has multiple elements
			RichArray revArray = new RichArray(this.array); // Create a copy of the RichArray
			// Reverse each row individually
			for (int i = 0; i < revArray.array.length; i++) {
				reverseLine(revArray.array[i]);
			}
			return revArray; // Return the reversed RichArray
		}
	}

	/**
	 * Rotates the {@link RichArray} 90 degrees to the right.
	 * 
	 * @return A new RichArray with the elements rotated 90 degrees to the right.
	 */
	public RichArray rotateRight() {
		// Create a new array with dimensions swapped to accommodate the rotation
		int[][] rotatedArray = new int[array[0].length][array.length];

		// Iterate through the original array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				// Calculate new indices for the rotated array
				int newRow = j; // New row index becomes the original column index
				int newCol = array.length - 1 - i; // New column index is calculated based on the original row index

				// Assign the value from the original array to the rotated array
				rotatedArray[newRow][newCol] = array[i][j];
			}
		}

		// Return the rotated RichArray
		return new RichArray(rotatedArray);
	}

	/**
	 * Rotates the {@link RichArray} 90 degrees to the left.
	 * 
	 * @return A new RichArray with the elements rotated 90 degrees to the left.
	 */
	public RichArray rotateLeft() {
		// Create a new array with dimensions swapped to accommodate the rotation
		int[][] rotatedArray = new int[array[0].length][array.length];

		// Iterate through the original array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				// Calculate new indices for the rotated array
				int newRow = array[0].length - 1 - j; // New row index is calculated based on the original column index
				int newCol = i; // New column index becomes the original row index

				// Assign the value from the original array to the rotated array
				rotatedArray[newRow][newCol] = array[i][j];
			}
		}

		// Return the rotated RichArray
		return new RichArray(rotatedArray);
	}

	/**
	 * Transposes the {@link RichArray} elements.
	 * 
	 * @return A new RichArray with the elements transposed.
	 */
	public RichArray transpose() {
		// Create a new array with dimensions swapped
		int[][] transposedArray = new int[array[0].length][array.length];

		// Iterate through the original array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				// Swap row and column indices to transpose the element
				transposedArray[j][i] = array[i][j];
			}
		}

		// Return the transposed RichArray
		return new RichArray(transposedArray);
	}

	/**
	 * Ravel the elements of the {@link RichArray} into a new {@link RichArray} with
	 * specified number of elements in each row.
	 * 
	 * @param n The number of elements in each row of the raveled array.
	 * @return A new RichArray with the elements raveled according to the specified
	 *         number of elements per row.
	 * @throws IllegalArgumentException if the length of the original array is not
	 *                                  evenly divisible by n.
	 */
	public RichArray ravel(int n) {
		try {
			// Check if the length of the original array is evenly divisible by n
			if (array[0].length % n != 0) {
				// Throw an IllegalArgumentException if not evenly divisible
				throw new IllegalArgumentException(
						"Cannot ravel array: " + array[0].length + " is not evenly divisible with " + n);
			} else {
				// Calculate the number of rows in the raveled array
				int rows = array[0].length / n;
				// Initialize a new array to store the raveled elements
				int[][] raveledArray = new int[rows][n];
				int index = 0; // Initialize an index to track the current element in the original array

				// Iterate through the raveledArray
				for (int i = 0; i < raveledArray.length; i++) {
					for (int j = 0; j < raveledArray[0].length; j++) {
						// Assign the elements from the original array to the raveled array
						raveledArray[i][j] = array[0][index++];
					}
				}
				// Return the raveled RichArray
				return new RichArray(raveledArray);
			}
		} catch (IllegalArgumentException e) {
			// Print the error message and return
			System.out.println(e.getMessage() + "\n");
			return null;
		}
	}

	/**
	 * Unravels the elements of the {@link RichArray} into a one-dimensional array.
	 * 
	 * @return A new RichArray with the elements unraveled into a one-dimensional
	 *         array.
	 */
	public RichArray unravel() {
		// Create a new one-dimensional array to store the unraveled elements
		int[] unraveledArray = new int[array.length * array[0].length];
		int index = 0; // Initialize an index to track the current position in the unraveled array

		// Iterate through the original array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				// Assign the elements from the original array to the unraveled array
				unraveledArray[index++] = array[i][j];
			}
		}

		// Return the unraveled RichArray
		return new RichArray(unraveledArray);
	}

	/**
	 * Reshapes the {@link RichArray} into a new {@link RichArray} with the
	 * specified number of elements in each row.
	 * 
	 * @param n The number of elements in each row of the reshaped array.
	 * @return A new RichArray with the elements reshaped according to the specified
	 *         number of elements per row.
	 * @throws IllegalArgumentException if the total number of elements in the array
	 *                                  cannot be evenly divided into n parts.
	 */
	public RichArray reshape(int n) {
		try {
			// Unravel the RichArray into a one-dimensional array
			RichArray reshapedArray = this.unravel();

			// Check if the total number of elements in the array can be evenly divided into
			// n parts
			if ((array.length * array[0].length) % n != 0) {
				// Throw an IllegalArgumentException if not evenly divisible
				throw new IllegalArgumentException(
						"Cannot reshape array: total elements cannot be evenly divided into " + n + " parts.");
			} else {
				// Ravel the unraveled array into a new RichArray with the specified number of
				// elements per row
				reshapedArray = reshapedArray.ravel(n);
			}

			// Return the reshaped RichArray
			return new RichArray(reshapedArray.array);
		} catch (IllegalArgumentException e) {
			// Print the error message when IllegalArgumentException is caught
			System.out.println(e.getMessage() + "\n");
			return null;
		}
	}

	/**
	 * Joins the {@link RichArray} with another RichArray by concatenating their
	 * columns.
	 * 
	 * @param array The RichArray to join with.
	 * @return A new RichArray resulting from joining the columns of the two arrays.
	 * @throws IllegalArgumentException if the arrays have different numbers of
	 *                                  rows.
	 */
	public RichArray join(RichArray array) {
		try {
			// Create a new array to store the joined columns
			int[][] joinedArray = new int[this.array.length][this.array[0].length + array.array[0].length];

			// Check if the arrays have the same number of rows
			if (this.array.length != array.array.length) {
				// Throw an IllegalArgumentException if the number of rows is different
				throw new IllegalArgumentException("Cannot join arrays: arrays must have the same number of rows");
			} else {
				// Copy the columns of the first array to the joined array
				for (int i = 0; i < this.array.length; i++) {
					for (int j = 0; j < this.array[0].length; j++) {
						joinedArray[i][j] = this.array[i][j];
					}
				}

				// Merge the columns of the second array to the joined array
				for (int i = 0; i < array.array.length; i++) {
					for (int j = 0; j < array.array[0].length; j++) {
						joinedArray[i][j + this.array[0].length] = array.array[i][j];
					}
				}
			}

			// Return the joined RichArray
			return new RichArray(joinedArray);
		} catch (IllegalArgumentException e) {
			// Print the error message when IllegalArgumentException is caught
			System.out.println(e.getMessage() + "\n");
			return null;
		}
	}

	/**
	 * Stacks the {@link RichArray} on top of another RichArray by concatenating
	 * their rows.
	 * 
	 * @param array The RichArray to stack on top.
	 * @return A new RichArray resulting from stacking the rows of the two arrays.
	 * @throws IllegalArgumentException if the arrays have different numbers of
	 *                                  columns.
	 */
	public RichArray stack(RichArray array) {
		try {
			// Create a new array to store the stacked rows
			int[][] stackedArray = new int[this.array.length + array.array.length][this.array[0].length];

			// Check if the arrays have the same number of columns
			if (this.array[0].length != array.array[0].length) {
				// Throw an IllegalArgumentException if the number of columns is different
				throw new IllegalArgumentException("Cannot stack arrays: arrays must have the same number of columns");
			} else {
				// Copy the rows of the first array to the stacked array
				for (int i = 0; i < this.array.length; i++) {
					for (int j = 0; j < this.array[0].length; j++) {
						stackedArray[i][j] = this.array[i][j];
					}
				}

				// Merge the rows of the second array to the stacked array
				for (int i = 0; i < array.array.length; i++) {
					for (int j = 0; j < array.array[0].length; j++) {
						stackedArray[i + this.array.length][j] = array.array[i][j];
					}
				}
			}

			// Return the stacked RichArray
			return new RichArray(stackedArray);
		} catch (IllegalArgumentException e) {
			// Print the error message when IllegalArgumentException is caught
			System.out.println(e.getMessage() + "\n");
			return null;
		}
	}

	/**
	 * Extracts a slice of the {@link RichArray} defined by the specified range of
	 * rows and columns.
	 * 
	 * @param firstRow    The index of the first row of the slice (inclusive).
	 * @param lastRow     The index of the last row of the slice (inclusive).
	 * @param firstColumn The index of the first column of the slice (inclusive).
	 * @param lastColumn  The index of the last column of the slice (inclusive).
	 * @return A new RichArray containing the slice of the original array.
	 */
	public RichArray slice(int firstRow, int lastRow, int firstColumn, int lastColumn) {
		// Calculate the number of rows and columns in the slice
		int rows = lastRow - firstRow + 1;
		int cols = lastColumn - firstColumn + 1;
		// Create a new array to store the sliced elements
		int[][] slicedArray = new int[rows][cols];
		int slicedRowIndex = 0, slicedColIndex;

		// Iterate through the specified range to extract the slice
		for (int i = firstRow; i <= lastRow; i++) {
			slicedColIndex = 0;
			for (int j = firstColumn; j <= lastColumn; j++) {
				slicedArray[slicedRowIndex][slicedColIndex++] = array[i][j];
			}
			slicedRowIndex++;
		}

		// Return the sliced RichArray
		return new RichArray(slicedArray);
	}

	/**
	 * Replaces a portion of the {@link RichArray} with the specified RichArray
	 * starting from the given row and column indices.
	 *
	 * @param array  The RichArray to replace with.
	 * @param row    The index of the row where replacement starts.
	 * @param column The index of the column where replacement starts.
	 * @return A new RichArray with the specified portion replaced by the given
	 *         RichArray.
	 * @throws IllegalArgumentException if the new array rows or columns exceed the
	 *                                  bounds of the original array.
	 */
	public RichArray replace(RichArray array, int row, int column) {
		try {
			// Create a new array to store the replaced elements
			int[][] replacedArray = new int[this.array.length][this.array[0].length];

			// Check if the new array rows exceed the bounds of the original array
			if (array.array.length + row > this.array.length) {
				throw new IllegalArgumentException("Cannot replace array: new array rows will be out of bounds");
			}
			// Check if the new array columns exceed the bounds of the original array
			else if (array.array[0].length + column > this.array[0].length) {
				throw new IllegalArgumentException("Cannot replace array: new array columns will be out of bounds");
			} else {
				// Copy the elements of the original array to the replaced array
				for (int i = 0; i < this.array.length; i++) {
					for (int j = 0; j < this.array[0].length; j++) {
						replacedArray[i][j] = this.array[i][j];
					}
				}

				// Replace the portion of the replaced array with the specified RichArray
				for (int i = 0; i < array.array.length; i++) {
					for (int j = 0; j < array.array[0].length; j++) {
						// Adjust indices based on row and column parameters
						replacedArray[i + row][j + column] = array.array[i][j];
					}
				}
			}

			// Return the new RichArray with the replaced array
			return new RichArray(replacedArray);
		} catch (IllegalArgumentException e) {
			// Print the error message when IllegalArgumentException is caught
			System.out.println(e.getMessage() + "\n");
			return null;
		}
	}

	/**
	 * Reverses the elements of the given array in place.
	 * 
	 * @param array The array to reverse.
	 */
	private static void reverseLine(int[] array) {
		int start = 0;
		int end = array.length - 1;

		while (start < end) {
			// Swap elements at start and end indices
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;

			// Move towards the center of the array
			start++;
			end--;
		}
	}

	/**
	 * Returns a string representation of the object. Each element of the
	 * two-dimensional array is appended to the result string with a space
	 * separator, and each row is separated by a newline character.
	 *
	 * @return a string representation of the object
	 */
	public String toString() {
	    // Create a StringBuilder object to store the result
	    StringBuilder result = new StringBuilder();

	    // Iterate over each row of the array
	    for (int i = 0; i < this.array.length; i++) {
	        // Iterate over each column of the array
	        for (int j = 0; j < this.array[0].length; j++) {
	            // Append the element to the StringBuilder with a space separator
	            result.append(this.array[i][j]).append(" ");
	        }
	        // Append a new line to separate rows
	        result.append("\n");
	    }

	    // Return the string representation of the StringBuilder
	    return result.toString();
	}


}
