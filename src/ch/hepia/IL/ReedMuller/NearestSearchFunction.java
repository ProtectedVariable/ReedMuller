package ch.hepia.IL.ReedMuller;

import java.math.BigInteger;

public interface NearestSearchFunction {

	/**
	 * Finds the nearest word to cancel noise on an altered ReedMuller word
	 * Already decoded
	 * @param z the altered word
	 * @param rmo the code in which z has been coded
	 * @return The word which produces the nearest neighbour to the altered word
	 */
	BigInteger nearestWord(BigInteger z, ReedMullerOne rmo);
	
}
