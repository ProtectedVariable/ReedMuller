package ch.hepia.IL.ReedMuller;

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
		}
		b[r] = (1L << (1L << r)) -1;
	}

	public int encode(int x) {
		return bitwiseMatrixProduct(b, x);
	}

	public int decode(int y) {
		int x = 0;
		x = (int) BitUtil.setBitAt(0, x, BitUtil.getBitAt(0, y));
		long w = y ^ (BitUtil.getBitAt(0, y) * b[r]);

		for (int i = 0; i < r; i++) {
			int ctn = BitUtil.getBitAt(1 << i, w);
			x = (int) BitUtil.setBitAt(i, x, ctn);
			w ^= ctn * b[i];
		}
		return x;
	}

	public int bitwiseMatrixProduct(long[] lines, int vector) {
		int y = 0;
		for (int i = 0; i < (1 << r); i++) {
			for (int j = 0; j < r + 1; j++) {
				y ^= ((BitUtil.getBitAt(j, vector) & BitUtil.getBitAt(i, lines[j])) << (i));
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
