package ch.hepia.IL.ReedMuller;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ch.hepia.IL.ReedMuller.searches.ExaustiveSearch;
import ch.hepia.IL.ReedMuller.searches.SemiExaustiveSearch;

public class Tests {

	@Test
	public void CodeGeneration() {
		ReedMullerOne rmo = new ReedMullerOne(5);
		assertEquals(2863311530L, rmo.getB()[0]);
		assertEquals(3435973836L, rmo.getB()[1]);
		assertEquals(4042322160L, rmo.getB()[2]);
		assertEquals(4278255360L, rmo.getB()[3]);
		assertEquals(4294901760L, rmo.getB()[4]);
		assertEquals(4294967295L, rmo.getB()[5]);
	}
	
	@Test
	public void EncodeTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		assertEquals(0, rmo.encode(0));
		assertEquals(170, rmo.encode(1));
		assertEquals(204, rmo.encode(2));
		assertEquals(102, rmo.encode(3));
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
	
	@Test
	public void SemiExaustiveSearchTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		Random rand = new Random();
		for (int i = 0; i < (1 << 3); i++) {
			int y = rmo.encode(i);
			int z = y | (1 << rand.nextInt(1 << 3));
			y = SemiExaustiveSearch.getInstance().nearestWord(z, rmo);
			assertEquals(i, y);
		}
	}

}
