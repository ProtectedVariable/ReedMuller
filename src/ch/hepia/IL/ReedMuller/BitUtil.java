package ch.hepia.IL.ReedMuller;

import java.math.BigInteger;

public class BitUtil {

	private BitUtil() {
	}

	public static int getBitAt(int position, BigInteger word) {
		return word.testBit(position) ? 1 : 0;
	}

	public static long setBitAt(int position, long word, int value) {
		word &= ~(1L << position);
		return (word | (value << position));
	}

	public static int HamDist(BigInteger w1, BigInteger w2) {
		BigInteger delta = w1.xor(w2);
		//int dist = 0;
		/*
		for (int i = 0; i < delta.bitCount(); i++) {
			//dist += (delta >> i) & 1;
		}
		*/
		return delta.bitCount();
	}

	public static String toBinaryString(long w, int r) {
		return String.format("%" + (1 << r) + "s", Long.toBinaryString(Long.reverse(w) >>> (64 - (1 << r)))).replace(' ', '0');

	}

}
