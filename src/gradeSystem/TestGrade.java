package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGrade {
	private int[] weights = new int[]{10, 10, 10, 30, 40};
	Grade g = null;
	
	@Before
	public void setUp() throws Exception {
		g = new Grade("955002056", "許文馨", 88, 92, 88, 98, 91);
		g.calcuateTotalGrade(weights);
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
	public void testGetLab1() {
		assertEquals(88, g.getLab1());
	}

	@Test
	public void testGetLab2() {
		assertEquals(92, g.getLab2());
	}

	@Test
	public void testGetLab3() {
		assertEquals(88, g.getLab3());
	}

	@Test
	public void testGetMidTerm() {
		assertEquals(98, g.getMidTerm());
	}

	@Test
	public void testGetFinalExam() {
		assertEquals(91, g.getFinalExam());
	}

	@Test
	public void testGetTotalGrade() {
		double expected = 88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4;
		assertEquals(expected, g.getTotalGrade(), 0.0001);
	}

	@Test
	public void testCalcuateTotalGrade() {
		int weights[] = new int[]{10, 10, 10, 30, 40};
		double expected = 88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4;
		g.calcuateTotalGrade(weights);
		assertEquals(expected, g.getTotalGrade(), 0.0001);
	}

}
