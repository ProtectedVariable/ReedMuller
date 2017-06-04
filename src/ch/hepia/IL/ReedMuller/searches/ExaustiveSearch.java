package ch.hepia.IL.ReedMuller.searches;

import java.math.BigInteger;

import ch.hepia.IL.ReedMuller.BitUtil;
import ch.hepia.IL.ReedMuller.NearestSearchFunction;
import ch.hepia.IL.ReedMuller.ReedMullerOne;

public class ExaustiveSearch implements NearestSearchFunction {

	private static ExaustiveSearch instance;
	
	private ExaustiveSearch() {	}

	public static ExaustiveSearch getInstance() {
		if(instance == null) {
			instance = new ExaustiveSearch();
		}
		return instance;
	}
	
	@Override
	public BigInteger nearestWord(BigInteger z, ReedMullerOne rmo) {
		BigInteger nn = BigInteger.valueOf(0);
		int nd = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << rmo.getR()+1); i++) {
			int dist = BitUtil.HamDist(rmo.encode(BigInteger.valueOf(i)), z);
			if(dist < nd) {
				nn = BigInteger.valueOf(i);
				nd = dist;
			}
		}
		return nn;
	}
	
	
	
}
