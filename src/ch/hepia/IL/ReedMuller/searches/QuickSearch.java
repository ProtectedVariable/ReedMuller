package ch.hepia.IL.ReedMuller.searches;

import java.math.BigInteger;

import ch.hepia.IL.ReedMuller.BitUtil;
import ch.hepia.IL.ReedMuller.NearestSearchFunction;
import ch.hepia.IL.ReedMuller.ReedMullerOne;

public class QuickSearch implements NearestSearchFunction {

	private static QuickSearch instance;
	
	public static QuickSearch getInstance() {
		if(instance == null) {
			instance = new QuickSearch();
		}
		return instance;
	}
	

	//0 => -1, 1 => 1.
	private BigInteger[] getMatrix(int r) {
		if(r > 1) {
			BigInteger[] Hrm1 = getMatrix(r-1);
			BigInteger[] Hr = new BigInteger[2*Hrm1.length];
			for (int i = 0; i < Hr.length; i++) {
				if(i < Hrm1.length) {
					String line = String.format("%"+(1 << r-1)+"s", Hrm1[i].toString(2)).replace(' ', '0');
					Hr[i] = new  BigInteger(line+line, 2);
				} else {
					String mHrm1 = String.format("%"+(1 << r-1)+"s", Hrm1[i-Hrm1.length].toString(2)).replace('0', ' ').replace('1', '0').replace(' ', '1');
					Hr[i] = new BigInteger(mHrm1+String.format("%"+(1 << r-1)+"s", Hrm1[i-Hrm1.length].toString(2)).replace(' ', '0'), 2);
				}
			}
			return Hr;
		} else {
			BigInteger[] H1 = new BigInteger[2];
			H1[0] = new BigInteger("11", 2);
			H1[1] = new BigInteger("01", 2);
			return H1;
		}
	}
	
	private int[] vecTimesMat(BigInteger vec, BigInteger[] mat) {
		int[] result = new int[mat.length];
		for (int i = 0; i < mat.length; i++) {
			int sum = 0;
			for (int j = 0; j < mat.length; j++) {
				int fj = BitUtil.getBitAt(j, vec);
				if(fj == 0) fj = -1;
				int matji = BitUtil.getBitAt(i, mat[j]);
				if(matji == 0) matji = -1;
				sum += fj * matji;
			}
			result[i] = sum;
		}
		return result;
	}
	
	@Override
	public BigInteger nearestWord(BigInteger z, ReedMullerOne rmo) {
		BigInteger[] Hr = getMatrix(rmo.getR());
		BigInteger F = BigInteger.valueOf(0);
		for (int i = 0; i < (1 << rmo.getR()); i++) {
			if(!z.testBit(i)) {
				F = F.setBit(i);
			}
		}
		System.out.println(F.toString(2));
		int[] Fhat = vecTimesMat(F, Hr);
		int maxi = 0;
		int maxv = 0;
		for (int i = 0; i < Fhat.length; i++) {
			if(Math.abs(Fhat[i]) > maxv) {
				maxi = i;
				maxv = Math.abs(Fhat[i]);
			}
		}
		if(Fhat[maxi] < 0) maxi += (1 << rmo.getR());
		return BigInteger.valueOf(maxi);
	}

}
