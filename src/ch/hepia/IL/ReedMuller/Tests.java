package ch.hepia.IL.ReedMuller;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

import ch.hepia.IL.ReedMuller.searches.ExaustiveSearch;
import ch.hepia.IL.ReedMuller.searches.QuickSearch;
import ch.hepia.IL.ReedMuller.searches.SemiExaustiveSearch;

public class Tests {

	@Test
	public void CodeGeneration() {
		ReedMullerOne rmo = new ReedMullerOne(5);
		assertEquals(2863311530L, rmo.getB()[0].longValue());
		assertEquals(3435973836L, rmo.getB()[1].longValue());
		assertEquals(4042322160L, rmo.getB()[2].longValue());
		assertEquals(4278255360L, rmo.getB()[3].longValue());
		assertEquals(4294901760L, rmo.getB()[4].longValue());
		assertEquals(4294967295L, rmo.getB()[5].longValue());
	}

	@Test
	public void EncodeTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		assertEquals(0, rmo.encode(BigInteger.valueOf(0)).intValue());
		assertEquals(170, rmo.encode(BigInteger.valueOf(1)).intValue());
		assertEquals(204, rmo.encode(BigInteger.valueOf(2)).intValue());
		assertEquals(102, rmo.encode(BigInteger.valueOf(3)).intValue());
	}

	@Test
	public void DecodeTest() {
		ReedMullerOne rmo = new ReedMullerOne(5);
		for (int i = 0; i < (1 << 6); i++) {
			assertEquals(i, rmo.decode(rmo.encode(BigInteger.valueOf(i))).intValue());
		}
	}

	@Test
	public void HamDistTest() {
		BigInteger w1 = BigInteger.valueOf(0b000111);
		BigInteger w2 = BigInteger.valueOf(0b010101);
		assertEquals(2, BitUtil.HamDist(w1, w2));
	}

	@Test
	public void ExaustiveSearchTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		Random rand = new Random();
		for (int i = 0; i < (1 << 4); i++) {
			BigInteger y = rmo.encode(BigInteger.valueOf(i));
			BigInteger z = y.or(BigInteger.valueOf(1 << rand.nextInt(1 << 3)));
			y = ExaustiveSearch.getInstance().nearestWord(z, rmo);
			assertEquals(i, y.intValue());
		}
	}

	@Test
	public void SemiExaustiveSearchTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		Random rand = new Random();
		for (int i = 0; i < (1 << 4); i++) {
			BigInteger y = rmo.encode(BigInteger.valueOf(i));
			BigInteger z = y.or(BigInteger.valueOf(1 << rand.nextInt(1 << 3)));
			y = SemiExaustiveSearch.getInstance().nearestWord(z, rmo);
			assertEquals(i, y.intValue());
		}
	}

	@Test
	public void QuickSearchTest() {
		ReedMullerOne rmo = new ReedMullerOne(3);
		Random rand = new Random();
		for (int i = 0; i < (1 << 4); i++) {
			BigInteger y = rmo.encode(BigInteger.valueOf(i));
			BigInteger z = y.or(BigInteger.valueOf(1 << rand.nextInt(1 << 3)));
			y = QuickSearch.getInstance().nearestWord(y, rmo);
			assertEquals(i, y.intValue());
		}
	}

}
