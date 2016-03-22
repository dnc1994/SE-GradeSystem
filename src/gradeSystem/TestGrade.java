package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGrade {
	Grade g = null;
	
	@Before
	public void setUp() throws Exception {
		g = new Grade("955002056", "許文馨", 88, 92, 88, 98, 91);
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void testEquals() {
		assertEquals(true, g.equals(new Grade("955002056", "許文馨", 88, 92, 88, 98, 91)));
		assertEquals(false, g.equals(new Grade()));
	}
	
	@Test
	public void testCalcuateTotalGrade() {
		int weights[] = new int[]{10, 10, 10, 30, 40};
		double expected = 88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4;
		g.calcuateTotalGrade(weights);
		assertEquals(expected, g.getTotalGrade(), 0.0001);
	}

}
