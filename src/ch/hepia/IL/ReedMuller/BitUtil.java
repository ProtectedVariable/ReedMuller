package ch.hepia.IL.ReedMuller;

public class BitUtil {

	private BitUtil() {}
	
	public static int getBitAt(int position, long word) {
		return (int) ((word >> (position)) & 1);
	}

	public static long setBitAt(int position, long word, int value) {
		word &= ~(1L << position);
		return (word | (value << position));
	}
	
	public static int HamDist(long w1, long w2) {
		long delta = w1 ^ w2;
		int dist = 0;
		for (int i = 0; i < 64; i++) {
			dist += (delta >> i) & 1;
		}
		return dist;
	}
	
}
