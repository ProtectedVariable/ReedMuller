package ch.hepia.IL.ReedMuller;

import java.math.BigInteger;

public class ReedMullerOne {

	private int r;
	private BigInteger[] b;

	public ReedMullerOne(int r) {
		this.r = r;
		this.b = new BigInteger[r + 1];
		generateBase();
	}

	private void generateBase() {
		for (int k = 0; k < r; k++) {
			b[k] = BigInteger.valueOf(0);
			for (int i = 0; i < (1 << r); i++) {
				b[k] = b[k].or(BigInteger.ONE.and(BigInteger.valueOf(i).and(BigInteger.ONE.shiftLeft(k)).shiftRight(k)).shiftLeft(i));
				//b[k] |= ((1L & ((i & (1L << k)) >> k)) << i);
			}
		}
		b[r] = BigInteger.valueOf(1);
		b[r] = b[r].shiftLeft(BigInteger.ONE.shiftLeft(r).intValue()).subtract(BigInteger.ONE);
		//b[r] = (1L << (1L << r)) - 1;
	}

	public BigInteger encode(BigInteger x) {
		return bitwiseMatrixProduct(b, x);
	}

	public BigInteger decode(BigInteger y) {
		BigInteger x = BigInteger.valueOf(0);
		BigInteger y0 = BigInteger.valueOf(BitUtil.getBitAt(0, y));
		if(BitUtil.getBitAt(0, y) == 1) {
			x = x.setBit(0); //x = (int) BitUtil.setBitAt(0, x, BitUtil.getBitAt(0, y));
		}
		
		BigInteger w = y.xor(b[r].multiply(y0));
		//long w = y ^ (BitUtil.getBitAt(0, y) * b[r]);

		for (int i = 0; i < r; i++) {
			int ctn = BitUtil.getBitAt(1 << i, w);
			if(ctn == 1) {
				x = x.setBit(i);
				//x = (int) BitUtil.setBitAt(i, x, ctn);
				w = w.xor(b[i]);
				//w ^= ctn * b[i];
			}
			
		}
		return x;
	}

	public BigInteger bitwiseMatrixProduct(BigInteger[] lines, BigInteger vector) {
		BigInteger y = BigInteger.valueOf(0);
		for (int i = 0; i < (1 << r); i++) {
			for (int j = 0; j < r + 1; j++) {
				y = y.xor(BigInteger.valueOf(BitUtil.getBitAt(j, vector) & BitUtil.getBitAt(i, lines[j])).shiftLeft(i));
				//y ^= ((BitUtil.getBitAt(j, vector) & BitUtil.getBitAt(i, lines[j])) << (i));
			}
		}
		return y;
	}

	public int getR() {
		return r;
	}

	public BigInteger[] getB() {
		return b;
	}

}
