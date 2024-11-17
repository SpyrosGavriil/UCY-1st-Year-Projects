package hw3.squarelotron;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquarelotronJUnit {

	int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

	@Test
	void testSmallSquarelotronConstructor() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron squarelotron = new SmallSquarelotron(array);

		// Verify the squarelotron size
		assertEquals(4, squarelotron.getSquarelotron().length);
		assertEquals(4, squarelotron.getSquarelotron()[0].length);

		// Verify the squarelotron contents
		int[][] expected = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		assert2DArrayEquals(expected, squarelotron.getSquarelotron());
	}

	@Test
	void testLargeSquarelotronConstructor() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
		Squarelotron squarelotron = new LargeSquarelotron(array);

		// Verify the squarelotron size
		assertEquals(5, squarelotron.getSquarelotron().length);
		assertEquals(5, squarelotron.getSquarelotron()[0].length);

		// Verify the squarelotron contents
		int[][] expected = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };
		assert2DArrayEquals(expected, squarelotron.getSquarelotron());
	}

	@Test
	void testInvalidSquarelotronArray() {
		// Test with an invalid array size
		int[] invalidArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		assertThrows(IllegalArgumentException.class, () -> new SmallSquarelotron(invalidArray));

		// Test with invalid numbers in the array
		int[] invalidNumbersArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 101 };
		assertThrows(IllegalArgumentException.class, () -> new LargeSquarelotron(invalidNumbersArray));
	}

	private void assert2DArrayEquals(int[][] expected, int[][] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], actual[i]);
		}
	}

	@Test
	void testUpsideDownFlip() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);
		Squarelotron flippedSmallSq = smallSq.upsideDownFlip("outer");

		// Verify the size remains the same for small squarelotron
		assertEquals(4, flippedSmallSq.getSquarelotron().length);
		assertEquals(4, flippedSmallSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for small squarelotron
		int[][] expectedSmall = { { 13, 14, 15, 16 }, { 9, 6, 7, 12 }, { 5, 10, 11, 8 }, { 1, 2, 3, 4 } };
		assert2DArrayEquals(expectedSmall, flippedSmallSq.getSquarelotron());

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 21, 25, 22, 19, 24,
				20 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);
		Squarelotron flippedLargeSq = largeSq.upsideDownFlip("outer");

		// Verify the size remains the same for large squarelotron
		assertEquals(5, flippedLargeSq.getSquarelotron().length);
		assertEquals(5, flippedLargeSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for large squarelotron
		int[][] expectedLarge = { { 25, 22, 19, 24, 20 }, { 16, 7, 8, 9, 21 }, { 11, 12, 13, 14, 15 },
				{ 6, 17, 18, 23, 10 }, { 1, 2, 3, 4, 5 } };
		assert2DArrayEquals(expectedLarge, flippedLargeSq.getSquarelotron());

		flippedSmallSq = smallSq.upsideDownFlip("inner");
		int[][] newExpectedSmall = { { 1, 2, 3, 4 }, { 5, 10, 11, 8 }, { 9, 6, 7, 12 }, { 13, 14, 15, 16 } };
		assert2DArrayEquals(newExpectedSmall, flippedSmallSq.getSquarelotron());

		flippedLargeSq = largeSq.upsideDownFlip("inner");
		int[][] newExpectedLarge = { { 1, 2, 3, 4, 5 }, { 6, 17, 18, 23, 10 }, { 11, 12, 13, 14, 15 },
				{ 16, 7, 8, 9, 21 }, { 25, 22, 19, 24, 20 } };
		assert2DArrayEquals(newExpectedLarge, flippedLargeSq.getSquarelotron());
	}

	@Test
	void testLeftRightFlip() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);
		Squarelotron flippedSmallSq = smallSq.leftRightFlip("inner");

		// Verify the size remains the same for small squarelotron
		assertEquals(4, flippedSmallSq.getSquarelotron().length);
		assertEquals(4, flippedSmallSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for small squarelotron
		int[][] expectedSmall = { { 1, 2, 3, 4 }, { 5, 7, 6, 8 }, { 9, 11, 10, 12 }, { 13, 14, 15, 16 } };
		assert2DArrayEquals(expectedSmall, flippedSmallSq.getSquarelotron());

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 21, 25, 22, 19, 24,
				20 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);
		Squarelotron flippedLargeSq = largeSq.leftRightFlip("inner");

		// Verify the size remains the same for large squarelotron
		assertEquals(5, flippedLargeSq.getSquarelotron().length);
		assertEquals(5, flippedLargeSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for large squarelotron
		int[][] expectedLarge = { { 1, 2, 3, 4, 5 }, { 6, 9, 8, 7, 10 }, { 11, 14, 13, 12, 15 }, { 16, 23, 18, 17, 21 },
				{ 25, 22, 19, 24, 20 } };
		assert2DArrayEquals(expectedLarge, flippedLargeSq.getSquarelotron());

		flippedSmallSq = largeSq.leftRightFlip("outer");

		int[][] newExpectedLarge = { { 5, 4, 3, 2, 1 }, { 10, 7, 8, 9, 6 }, { 15, 12, 13, 14, 11 },
				{ 21, 17, 18, 23, 16 }, { 20, 24, 19, 22, 25 } };
		assert2DArrayEquals(newExpectedLarge, flippedSmallSq.getSquarelotron());

		flippedLargeSq = largeSq.leftRightFlip("outer");
	}

	@Test
	void testInverseDiagonalFlip() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);
		Squarelotron flippedSmallSq = smallSq.inverseDiagonalFlip("inner");

		// Verify the size remains the same for small squarelotron
		assertEquals(4, flippedSmallSq.getSquarelotron().length);
		assertEquals(4, flippedSmallSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for small squarelotron
		int[][] expectedSmall = { { 1, 2, 3, 4 }, { 5, 11, 7, 8 }, { 9, 10, 6, 12 }, { 13, 14, 15, 16 } };
		assert2DArrayEquals(expectedSmall, flippedSmallSq.getSquarelotron());

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 21, 25, 22, 19, 24,
				20 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);
		Squarelotron flippedLargeSq = largeSq.inverseDiagonalFlip("inner");

		// Verify the size remains the same for large squarelotron
		assertEquals(5, flippedLargeSq.getSquarelotron().length);
		assertEquals(5, flippedLargeSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for large squarelotron
		int[][] expectedLarge = { { 1, 2, 3, 4, 5 }, { 6, 23, 14, 9, 10 }, { 11, 18, 13, 8, 15 }, { 16, 17, 12, 7, 21 },
				{ 25, 22, 19, 24, 20 } };
		assert2DArrayEquals(expectedLarge, flippedLargeSq.getSquarelotron());

		flippedSmallSq = smallSq.inverseDiagonalFlip("outer");

		int[][] newExpectedSmall = { { 16, 12, 8, 4 }, { 15, 6, 7, 3 }, { 14, 10, 11, 2 }, { 13, 9, 5, 1 } };
		assert2DArrayEquals(newExpectedSmall, flippedSmallSq.getSquarelotron());

		flippedLargeSq = largeSq.inverseDiagonalFlip("outer");

		int[][] newExpectedLarge = { { 20, 21, 15, 10, 5 }, { 24, 7, 8, 9, 4 }, { 19, 12, 13, 14, 3 },
				{ 22, 17, 18, 23, 2 }, { 25, 16, 11, 6, 1 } };
		assert2DArrayEquals(newExpectedLarge, flippedLargeSq.getSquarelotron());
	}

	@Test
	void testMainDiagonalFlip() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);
		Squarelotron flippedSmallSq = smallSq.mainDiagonalFlip("inner");

		// Verify the size remains the same for small squarelotron
		assertEquals(4, flippedSmallSq.getSquarelotron().length);
		assertEquals(4, flippedSmallSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for small squarelotron
		int[][] expectedSmall = { { 1, 2, 3, 4 }, { 5, 6, 10, 8 }, { 9, 7, 11, 12 }, { 13, 14, 15, 16 } };
		assert2DArrayEquals(expectedSmall, flippedSmallSq.getSquarelotron());

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 21, 25, 22, 19, 24,
				20 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);
		Squarelotron flippedLargeSq = largeSq.mainDiagonalFlip("inner");

		// Verify the size remains the same for large squarelotron
		assertEquals(5, flippedLargeSq.getSquarelotron().length);
		assertEquals(5, flippedLargeSq.getSquarelotron()[0].length);

		// Verify the contents after the flip for large squarelotron
		int[][] expectedLarge = { { 1, 2, 3, 4, 5 }, { 6, 7, 12, 17, 10 }, { 11, 8, 13, 18, 15 }, { 16, 9, 14, 23, 21 },
				{ 25, 22, 19, 24, 20 } };
		assert2DArrayEquals(expectedLarge, flippedLargeSq.getSquarelotron());

		flippedSmallSq = smallSq.mainDiagonalFlip("outer");

		int[][] newExpectedSmall = { { 1, 5, 9, 13 }, { 2, 6, 7, 14 }, { 3, 10, 11, 15 }, { 4, 8, 12, 16 } };
		assert2DArrayEquals(newExpectedSmall, flippedSmallSq.getSquarelotron());

		flippedLargeSq = largeSq.mainDiagonalFlip("outer");

		int[][] newExpectedLarge = { { 1, 6, 11, 16, 25 }, { 2, 7, 8, 9, 22 }, { 3, 12, 13, 14, 19 },
				{ 4, 17, 18, 23, 24 }, { 5, 10, 15, 21, 20 } };
		assert2DArrayEquals(newExpectedLarge, flippedLargeSq.getSquarelotron());
	}

	@Test
	void testSideFlip() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);

		// Test all possible side flips for small squarelotron
		Squarelotron leftFlippedSmall = smallSq.sideFlip("left");
		assert2DArrayEquals(new int[][] { { 2, 1, 3, 4 }, { 6, 5, 7, 8 }, { 10, 9, 11, 12 }, { 14, 13, 15, 16 } },
				leftFlippedSmall.getSquarelotron());

		Squarelotron rightFlippedSmall = smallSq.sideFlip("right");
		assert2DArrayEquals(new int[][] { { 1, 2, 4, 3 }, { 5, 6, 8, 7 }, { 9, 10, 12, 11 }, { 13, 14, 16, 15 } },
				rightFlippedSmall.getSquarelotron());

		Squarelotron topFlippedSmall = smallSq.sideFlip("top");
		assert2DArrayEquals(new int[][] { { 5, 6, 7, 8 }, { 1, 2, 3, 4 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } },
				topFlippedSmall.getSquarelotron());

		Squarelotron bottomFlippedSmall = smallSq.sideFlip("bottom");
		assert2DArrayEquals(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 13, 14, 15, 16 }, { 9, 10, 11, 12 } },
				bottomFlippedSmall.getSquarelotron());

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);

		// Test all possible side flips for large squarelotron
		Squarelotron leftFlippedLarge = largeSq.sideFlip("left");
		assert2DArrayEquals(new int[][] { { 2, 1, 3, 4, 5 }, { 7, 6, 8, 9, 10 }, { 12, 11, 13, 14, 15 },
				{ 17, 16, 18, 19, 20 }, { 22, 21, 23, 24, 25 } }, leftFlippedLarge.getSquarelotron());

		Squarelotron rightFlippedLarge = largeSq.sideFlip("right");
		assert2DArrayEquals(new int[][] { { 1, 2, 3, 5, 4 }, { 6, 7, 8, 10, 9 }, { 11, 12, 13, 15, 14 },
				{ 16, 17, 18, 20, 19 }, { 21, 22, 23, 25, 24 } }, rightFlippedLarge.getSquarelotron());

		Squarelotron topFlippedLarge = largeSq.sideFlip("top");
		assert2DArrayEquals(new int[][] { { 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5 }, { 11, 12, 13, 14, 15 },
				{ 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25 } }, topFlippedLarge.getSquarelotron());

		Squarelotron bottomFlippedLarge = largeSq.sideFlip("bottom");
		assert2DArrayEquals(new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
				{ 21, 22, 23, 24, 25 }, { 16, 17, 18, 19, 20 } }, bottomFlippedLarge.getSquarelotron());
	}

	@Test
	void testRotateRight() {
		// Test for a small squarelotron
		int[] smallArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron smallSq = new SmallSquarelotron(smallArray);

		// Test all possible rotations for small squarelotron
		smallSq.rotateRight(1);
		assert2DArrayEquals(new int[][] { { 13, 9, 5, 1 }, { 14, 10, 6, 2 }, { 15, 11, 7, 3 }, { 16, 12, 8, 4 } },
				smallSq.getSquarelotron());
		smallSq = new SmallSquarelotron(smallArray);

		smallSq.rotateRight(2);
		assert2DArrayEquals(new int[][] { { 16, 15, 14, 13 }, { 12, 11, 10, 9 }, { 8, 7, 6, 5 }, { 4, 3, 2, 1 } },
				smallSq.getSquarelotron());
		smallSq = new SmallSquarelotron(smallArray);

		smallSq.rotateRight(3);
		assert2DArrayEquals(new int[][] { { 4, 8, 12, 16 }, { 3, 7, 11, 15 }, { 2, 6, 10, 14 }, { 1, 5, 9, 13 } },
				smallSq.getSquarelotron());
		smallSq = new SmallSquarelotron(smallArray);

		// Test for a large squarelotron
		int[] largeArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25 };
		Squarelotron largeSq = new LargeSquarelotron(largeArray);

		// Test all possible rotations for large squarelotron
		largeSq.rotateRight(1);
		assert2DArrayEquals(new int[][] { { 21, 16, 11, 6, 1 }, { 22, 17, 12, 7, 2 }, { 23, 18, 13, 8, 3 },
				{ 24, 19, 14, 9, 4 }, { 25, 20, 15, 10, 5 } }, largeSq.getSquarelotron());
		largeSq = new LargeSquarelotron(largeArray);

		largeSq.rotateRight(2);
		assert2DArrayEquals(new int[][] { { 25, 24, 23, 22, 21 }, { 20, 19, 18, 17, 16 }, { 15, 14, 13, 12, 11 },
				{ 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } }, largeSq.getSquarelotron());
		largeSq = new LargeSquarelotron(largeArray);

		largeSq.rotateRight(3);
		assert2DArrayEquals(new int[][] { { 5, 10, 15, 20, 25 }, { 4, 9, 14, 19, 24 }, { 3, 8, 13, 18, 23 },
				{ 2, 7, 12, 17, 22 }, { 1, 6, 11, 16, 21 } }, largeSq.getSquarelotron());
		largeSq = new LargeSquarelotron(largeArray);

		// Test for negative rotations
		largeSq.rotateRight(-1);
		assert2DArrayEquals(new int[][] { { 5, 10, 15, 20, 25 }, { 4, 9, 14, 19, 24 }, { 3, 8, 13, 18, 23 },
				{ 2, 7, 12, 17, 22 }, { 1, 6, 11, 16, 21 } }, largeSq.getSquarelotron()); // Same as rotatedLarge270
		largeSq = new LargeSquarelotron(largeArray);

		largeSq.rotateRight(-2);
		assert2DArrayEquals(new int[][] { { 25, 24, 23, 22, 21 }, { 20, 19, 18, 17, 16 }, { 15, 14, 13, 12, 11 },
				{ 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } }, largeSq.getSquarelotron()); // Counter-clockwise 180 degrees
		largeSq = new LargeSquarelotron(largeArray);

		largeSq.rotateRight(-3);
		assert2DArrayEquals(new int[][] { { 21, 16, 11, 6, 1 }, { 22, 17, 12, 7, 2 }, { 23, 18, 13, 8, 3 },
				{ 24, 19, 14, 9, 4 }, { 25, 20, 15, 10, 5 } }, largeSq.getSquarelotron()); // Counter-clockwise 90
																							// degrees
	}

	@Test
	public void testEquals() {
		// Test for a 4x4 Squarelotron
		int[] array4x4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Squarelotron squarelotron4x4 = Squarelotron.makeSquarelotron(array4x4);
		int[] rotatedOnce = { 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3, 16, 12, 8, 4 };
		int[] rotatedTwice = { 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] rotatedThrice = { 4, 8, 12, 16, 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13 };
		Squarelotron rotatedOnce4x4 = Squarelotron.makeSquarelotron(rotatedOnce);
		Squarelotron rotatedTwice4x4 = Squarelotron.makeSquarelotron(rotatedTwice);
		Squarelotron rotatedThrice4x4 = Squarelotron.makeSquarelotron(rotatedThrice);

		// Test for a 5x5 Squarelotron
		int[] array5x5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
		Squarelotron squarelotron5x5 = Squarelotron.makeSquarelotron(array5x5);
		int[] rotatedOnce5 = { 21, 16, 11, 6, 1, 22, 17, 12, 7, 2, 23, 18, 13, 8, 3, 24, 19, 14, 9, 4, 25, 20, 15, 10, 5 };
		int[] rotatedTwice5 = { 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] rotatedThrice5 = { 5, 10, 15, 20, 25, 4, 9, 14, 19, 24, 3, 8, 13, 18, 23, 2, 7, 12, 17, 22, 1, 6, 11, 16, 21 };
		Squarelotron rotatedOnce5x5 = Squarelotron.makeSquarelotron(rotatedOnce5);
		Squarelotron rotatedTwice5x5 = Squarelotron.makeSquarelotron(rotatedTwice5);
		Squarelotron rotatedThrice5x5 = Squarelotron.makeSquarelotron(rotatedThrice5);
		
		assertTrue("Rotated should match", squarelotron4x4.equals(rotatedOnce4x4));
		assertTrue("Rotated should match", squarelotron4x4.equals(rotatedTwice4x4));
		assertTrue("Rotated should match", squarelotron4x4.equals(rotatedThrice4x4));
		
		assertTrue("Rotated should match", squarelotron5x5.equals(rotatedOnce5x5));
		assertTrue("Rotated should match", squarelotron5x5.equals(rotatedTwice5x5));
		assertTrue("Rotated should match", squarelotron5x5.equals(rotatedThrice5x5));

		assertTrue("Rotated should match", squarelotron4x4.equals(Squarelotron.makeSquarelotron(array4x4)));
		assertTrue("Rotated should match", squarelotron5x5.equals(Squarelotron.makeSquarelotron(array5x5)));

		// Array with 16 random numbers
		int[] array16 = new int[] { 23, 45, 12, 98, 56, 34, 87, 65, 21, 76, 89, 43, 67, 54, 32, 90 };

		// Array with 25 random numbers
		int[] array25 = new int[] { 56, 34, 78, 12, 45, 90, 23, 67, 89, 21, 43, 87, 65, 32, 54, 98, 76, 10, 30, 68, 88, 44, 19, 77, 55 };
		
		assertFalse("Rotated should not match", squarelotron4x4.equals(Squarelotron.makeSquarelotron(array16)));
		assertFalse("Rotated should not match", squarelotron5x5.equals(Squarelotron.makeSquarelotron(array25)));
		
		assertFalse("Rotated should not match", squarelotron4x4.equals(null));
		assertFalse("Rotated should not match", squarelotron5x5.equals(null));
		
		assertFalse("Rotated should not match", squarelotron4x4.equals(Squarelotron.makeSquarelotron(array25)));
		assertFalse("Rotated should not match", squarelotron5x5.equals(Squarelotron.makeSquarelotron(array16)));
	}

}
