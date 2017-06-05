package ch.hepia.IL.ReedMuller;

import java.math.BigInteger;

public class BitUtil {

	private BitUtil() {
	}

	/**
	 * Get a bit from a word
	 * @param position position of the bit
	 * @param word word to fetch bit from
	 * @return the position-th bit
	 */
	public static int getBitAt(int position, BigInteger word) {
		return word.testBit(position) ? 1 : 0;
	}

	@Deprecated
	/**
	 * Set a bit in a long word 
	 * @param position position of the bit
	 * @param word word to set from
	 * @param value value to set to
	 * @return new word with the bit setted
	 */
	public static long setBitAt(int position, long word, int value) {
		word &= ~(1L << position);
		return (word | (value << position));
	}

	/**
	 * Hamming Distance function
	 * @param w1 word 1
	 * @param w2 word 2
	 * @return dH(w1, w2)
	 */
	public static int HamDist(BigInteger w1, BigInteger w2) {
		BigInteger delta = w1.xor(w2);
		return delta.bitCount();
	}

	/**
	 * Convert a number to a binary string with just enough padding 0
	 * @param w word to convert
	 * @param r r parameter from the code
	 * @return string version of the word
	 */
	public static String toBinaryString(long w, int r) {
		return String.format("%" + (1 << r) + "s", Long.toBinaryString(Long.reverse(w) >>> (64 - (1 << r)))).replace(' ', '0');

	}

}
