package ch.hepia.IL.ReedMuller.searches;

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
	public int nearestWord(int z, ReedMullerOne rmo) {
		int nn = 0;
		int nd = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << rmo.getR()); i++) {
			int dist = BitUtil.HamDist(rmo.encode(i), z);
			if(dist < nd) {
				nn = i;
				nd = dist;
			}
		}
		return nn;
	}
	
	
	
}
