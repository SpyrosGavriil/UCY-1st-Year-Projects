package hw3.squarelotron;

/**
 * Represents a Squarelotron. A Squarelotron is a matrix of numbers that can be
 * manipulated by flipping and rotating.
 * 
 * @author Spyros Gavriil
 */
public abstract class Squarelotron implements SquarelotronMethods {

	/** The matrix representing the Squarelotron */
	private int[][] squarelotron;

	/**
	 * Constructs a Squarelotron with the given array of numbers and size.
	 *
	 * @param array The array of numbers to initialize the Squarelotron.
	 * @param size  The size of the Squarelotron (e.g., 4 for a 4x4 Squarelotron).
	 * @throws IllegalArgumentException If the array length does not match the size
	 *                                  squared.
	 */
	public Squarelotron(int[] array) throws IllegalArgumentException {
		int size = (int) (Math.sqrt(array.length));

		if (size != 4 && size != 5)
			throw new IllegalArgumentException("Invalid squarelotron size");
		for (int num : array) {
			if (num < 0 || num > 99)
				throw new IllegalArgumentException("Invalid squarelotron number. Numbers must be between 0-99");
		}
		int index = 0;
		squarelotron = new int[size][size];

		for (int i = 0; i < squarelotron.length; i++) {
			for (int j = 0; j < squarelotron[0].length; j++) {
				squarelotron[i][j] = array[index];
				index++;
			}
		}
	}

	/**
	 * Creates a Squarelotron with the given array of numbers. The size of the
	 * Squarelotron is determined by the length of the array.
	 *
	 * @param array The array of numbers to initialize the Squarelotron.
	 * @return A Squarelotron instance with the given array of numbers.
	 * @throws IllegalArgumentException If the array length is not valid for a
	 *                                  Squarelotron.
	 */
	public static Squarelotron makeSquarelotron(int[] array) throws IllegalArgumentException {
		if (array.length != 16 && array.length != 25)
			throw new IllegalArgumentException("Invalid squarelotron size");
		if (array.length == 16)
			return new SmallSquarelotron(array);
		else
			return new LargeSquarelotron(array);
	}

	/**
	 * Retrieves the numbers of the Squarelotron and returns them as a 1D array.
	 *
	 * @return An array containing all the numbers of the Squarelotron.
	 */
	@Override
	public int[] numbers() {
		int size = squarelotron.length;
		int[] array = new int[size * size];
		int index = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[index] = squarelotron[i][j];
				index++;
			}
		}

		return array;
	}

	/**
	 * Performs an upside-down flip on the Squarelotron and returns a new
	 * Squarelotron object.
	 *
	 * @param ring Specifies which ring of the Squarelotron to flip ("outer" or
	 *             "inner").
	 * @return A new Squarelotron object with the specified ring flipped upside
	 *         down.
	 */
	@Override
	public Squarelotron upsideDownFlip(String ring) {
		Squarelotron sq = makeSquarelotron(this.numbers());
		if (ring.equals("outer")) {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[squarelotron.length - i - 1][j];
					}
				}
			}
		} else {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (!isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[squarelotron.length - i - 1][j];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs a left-right flip on the Squarelotron and returns a new Squarelotron
	 * object.
	 *
	 * @param ring Specifies which ring of the Squarelotron to flip ("outer" or
	 *             "inner").
	 * @return A new Squarelotron object with the specified ring flipped left-right.
	 */
	@Override
	public Squarelotron leftRightFlip(String ring) {
		Squarelotron sq = makeSquarelotron(this.numbers());
		if (ring.equals("outer")) {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[i][squarelotron.length - j - 1];
					}
				}
			}
		} else {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (!isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[i][squarelotron.length - j - 1];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs an inverse diagonal flip on the Squarelotron and returns a new
	 * Squarelotron object.
	 *
	 * @param ring Specifies which ring of the Squarelotron to flip ("outer" or
	 *             "inner").
	 * @return A new Squarelotron object with the specified ring flipped along the
	 *         inverse diagonal.
	 */
	@Override
	public Squarelotron inverseDiagonalFlip(String ring) {
		Squarelotron sq = makeSquarelotron(this.numbers());
		if (ring.equals("outer")) {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[squarelotron.length - 1 - j][squarelotron.length - 1 - i];
					}
				}
			}
		} else {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (!isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[squarelotron.length - 1 - j][squarelotron.length - 1 - i];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs a main diagonal flip on the Squarelotron and returns a new
	 * Squarelotron object.
	 *
	 * @param ring Specifies which ring of the Squarelotron to flip ("outer" or
	 *             "inner").
	 * @return A new Squarelotron object with the specified ring flipped along the
	 *         main diagonal.
	 */
	@Override
	public Squarelotron mainDiagonalFlip(String ring) {
		Squarelotron sq = makeSquarelotron(this.numbers());
		if (ring.equals("outer")) {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (isOnOuterRing(i, j) && i != j) {
						sq.squarelotron[i][j] = squarelotron[j][i];
					}
				}
			}
		} else {
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron.length; j++) {
					if (!isOnOuterRing(i, j)) {
						sq.squarelotron[i][j] = squarelotron[j][i];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs a side flip on the Squarelotron and returns a new Squarelotron
	 * object.
	 *
	 * @param side Specifies which side of the Squarelotron to flip ("left",
	 *             "right", "top", or "bottom").
	 * @return A new Squarelotron object with the specified side flipped.
	 */
	@Override
	public Squarelotron sideFlip(String side) {
		Squarelotron sq = makeSquarelotron(this.numbers());

		if (side.equals("left")) {
			for (int i = 0; i < squarelotron.length; i++) {
				int temp = sq.squarelotron[i][0];
				sq.squarelotron[i][0] = sq.squarelotron[i][1];
				sq.squarelotron[i][1] = temp;
			}
		} else if (side.equals("right")) {
			int n = squarelotron.length;
			for (int i = 0; i < n; i++) {
				int temp = sq.squarelotron[i][n - 1];
				sq.squarelotron[i][n - 1] = sq.squarelotron[i][n - 2];
				sq.squarelotron[i][n - 2] = temp;
			}
		} else if (side.equals("top")) {
			int n = squarelotron.length;
			for (int j = 0; j < n; j++) {
				int temp = sq.squarelotron[0][j];
				sq.squarelotron[0][j] = sq.squarelotron[1][j];
				sq.squarelotron[1][j] = temp;
			}
		} else if (side.equals("bottom")) {
			int n = squarelotron.length;
			for (int j = 0; j < n; j++) {
				int temp = sq.squarelotron[n - 2][j];
				sq.squarelotron[n - 2][j] = sq.squarelotron[n - 1][j];
				sq.squarelotron[n - 1][j] = temp;
			}
		}

		return sq;
	}

	/**
	 * Rotates the Squarelotron to the right by the specified number of turns.
	 *
	 * @param numberOfTurns The number of clockwise turns to perform on the
	 *                      Squarelotron.
	 */
	@Override
	public void rotateRight(int numberOfTurns) {
		numberOfTurns = numberOfTurns % 4;
		if (numberOfTurns == 0)
			return;

		int rows = squarelotron.length;
		int cols = squarelotron[0].length;

		int[][] tempArray = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (Math.abs(numberOfTurns) == 2)
					tempArray[rows - 1 - i][cols - 1 - j] = squarelotron[i][j]; // 180 degrees
				else if (numberOfTurns == 1 || numberOfTurns == -3)
					tempArray[j][rows - 1 - i] = squarelotron[i][j]; // Clockwise
				else
					tempArray[cols - 1 - j][i] = squarelotron[i][j]; // Counter-clockwise
			}
		}
		squarelotron = tempArray;
	}

	/**
	 * Gets the current configuration of the Squarelotron.
	 *
	 * @return A 2D array representing the current configuration of the
	 *         Squarelotron.
	 */
	public int[][] getSquarelotron() {
		return this.squarelotron;
	}

	// Method to return the string representation of the Squarelotron
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < squarelotron.length; i++) {
			for (int j = 0; j < squarelotron[i].length; j++) {
				sb.append(squarelotron[i][j]);
				if (j < squarelotron[i].length - 1) {
					sb.append("\t"); // Add tab between elements in the same row
				}
			}
			sb.append("\n"); // Add newline after each row
		}
		return sb.toString();
	}

	/**
	 * Checks whether the elements of the current Squarelotron object are equal to
	 * the elements of another Squarelotron object.
	 *
	 * @param obj The Squarelotron object to compare with.
	 * @return true if all elements of the current Squarelotron are equal to the
	 *         corresponding elements of the specified Squarelotron; false
	 *         otherwise.
	 */
	private boolean checkElements(Squarelotron obj) {
		for (int i = 0; i < this.squarelotron.length; i++) {
			for (int j = 0; j < this.squarelotron[0].length; j++) {
				if (this.squarelotron[i][j] != obj.squarelotron[i][j])
					return false;
			}
		}

		return true;
	}

	/**
	 * Compares this Squarelotron with the specified object for equality. Returns
	 * true if the objects are equal, false otherwise. Two Squarelotron objects are
	 * considered equal if they have the same dimensions and contain the same
	 * elements in any orientation (including rotations).
	 *
	 * @param object the object to compare this Squarelotron against.
	 * @return true if the specified object is equal to this Squarelotron, false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Squarelotron other = (Squarelotron) object;
		if (this.checkElements(other)) // Check for immediate equality
			return true;
		for (int i = 1; i <= 3; i++) { // Check for rotation equality
			other.rotateRight(i);
			if (this.checkElements(other)) {
				other.rotateRight(4 - i);
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the specified position is on the outer ring of the Squarelotron.
	 *
	 * @param i The row index.
	 * @param j The column index.
	 * @return true if the position is on the outer ring, otherwise false.
	 */

	private boolean isOnOuterRing(int i, int j) {
		if (i == 0 || i == this.squarelotron.length - 1)
			return true;
		if (j == 0 || j == this.squarelotron.length - 1)
			return true;
		return false;
	}
}
