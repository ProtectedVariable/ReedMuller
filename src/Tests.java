import static org.junit.Assert.*;
import org.junit.Test;

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

}
