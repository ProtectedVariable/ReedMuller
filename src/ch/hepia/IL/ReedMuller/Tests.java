package ch.hepia.IL.ReedMuller;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ch.hepia.IL.ReedMuller.searches.ExaustiveSearch;

public class Tests {

	@Test
	public void CodeGeneration() {
		ReedMullerOne rmo = new ReedMullerOne(5);
		assertEquals(1431655765L, rmo.getB()[0]);
		assertEquals(858993459L, rmo.getB()[1]);
		assertEquals(252645135L, rmo.getB()[2]);
		assertEquals(16711935L, rmo.getB()[3]);
		assertEquals(65535, rmo.getB()[4]);
		assertEquals(4294967295L, rmo.getB()[5]);
	}
	
	@Test
	public void EncodeTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		assertEquals(0, rmo.encode(0));
		assertEquals(85, rmo.encode(8));
		assertEquals(51, rmo.encode(4));
		assertEquals(102, rmo.encode(12));
	}
	
	@Test
	public void DecodeTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		for (int i = 0; i < (1 << 3); i++) {
			assertEquals(i, rmo.decode(rmo.encode(i)));
		}
	}
	
	@Test
	public void HamDistTest() {
		long w1 = 0b000111;
		long w2 = 0b010101;
		assertEquals(2, BitUtil.HamDist(w1, w2));
	}
	
	@Test
	public void ExaustiveSearchTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		Random rand = new Random();
		for (int i = 0; i < (1 << 3); i++) {
			int y = rmo.encode(i);
			int z = y | (1 << rand.nextInt(1 << 3));
			y = ExaustiveSearch.getInstance().nearestWord(z, rmo);
			assertEquals(i, y);
		}
	}

}
