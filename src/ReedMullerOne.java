
public class ReedMullerOne {

	private int r;
	private long[] b;

	public ReedMullerOne(int r) {
		this.r = r;
		this.b = new long[r + 1];
		generateBase();
	}

	private void generateBase() {
		for (int k = 0; k < r; k++) {
			b[k] = 0;
			for (long i = 0; i < (1 << r); i++) {
				b[k] |= ((1L & ((i & (1L << k)) >> k)) << i);
			}
			b[k] = Long.reverse(b[k]) >> (64 - (1 << r));
		}
		b[r] = ~0;
	}

	public int encode(int x) {
		return bitwiseMatrixProduct(b, x);
	}

	public int bitwiseMatrixProduct(long[] lines, int vector) {
		int y = 0;
		for (int i = 0; i < (1 << r); i++) {
			for (int j = 0; j < r + 1; j++) {
				y ^= (((vector >> (r - j) & 1) & (lines[j] >> ((1 << r)-1-i) & 1)) << ((1 << r)-1-i));
			}
		}
		return y;
	}

	public int getR() {
		return r;
	}

	public long[] getB() {
		return b;
	}

}
