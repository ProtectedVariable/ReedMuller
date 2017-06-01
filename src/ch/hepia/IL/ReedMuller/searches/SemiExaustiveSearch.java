package ch.hepia.IL.ReedMuller.searches;

import ch.hepia.IL.ReedMuller.BitUtil;
import ch.hepia.IL.ReedMuller.NearestSearchFunction;
import ch.hepia.IL.ReedMuller.ReedMullerOne;

public class SemiExaustiveSearch implements NearestSearchFunction {

	private static SemiExaustiveSearch instance;
	
	private SemiExaustiveSearch() { }
	
	public static SemiExaustiveSearch getInstance() {
		if(instance == null) {
			instance = new SemiExaustiveSearch();
		}
		return instance;
	}
	
	@Override
	public int nearestWord(int z, ReedMullerOne rmo) {
		int nn = 0;
		int nd = Integer.MAX_VALUE;
		for (int i = 0; i < (1 << (rmo.getR())); i++) {
			int dist = BitUtil.HamDist(rmo.encode(i), z);
			if(dist < nd) {
				nn = i;
				nd = dist;
			}
			if((1 << rmo.getR()) - dist < nd) {
				nn = i + (1 << rmo.getR());
				nd = (1 << rmo.getR()) - dist;
			}
		}
		return nn;
	}

} 
