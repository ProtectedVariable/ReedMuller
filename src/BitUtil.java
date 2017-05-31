
public class BitUtil {

	private BitUtil() {}
	
	public static int getBitAt(int position, long word) {
		return (int) ((word >> (position)) & 1);
	}

	public static long setBitAt(int position, long word, int value) {
		word &= ~(1L << position);
		return (word | (value << position));
	}
	
}
