package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGradeAccumulator {
	private int[] weights = new int[]{10, 10, 10, 30, 40};
	GradeAccumulator ga = null;
	Grade g1 = null, g2 = null, g3 = null;
	
	@Before
	public void setUp() throws Exception {
		ga = new GradeAccumulator();
		g1 = new Grade("955002056", "許文馨", 88, 92, 88, 98, 91); g1.calcuateTotalGrade(weights);
		g2 = new Grade("962001044", "凌宗廷", 87, 86, 98, 88, 87); g2.calcuateTotalGrade(weights);
		g3 = new Grade("962001051", "李威廷", 81, 32, 50, 90, 93); g3.calcuateTotalGrade(weights);
		ga.addGrade(g1);
		ga.addGrade(g2);
		ga.addGrade(g3);
		ga.averageGrade(3);
	}

	@After
	public void tearDown() throws Exception {
		ga = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAverageGradeIllegalArgumentException() {
		ga.averageGrade(0);
	}

	@Test
	public void testGetAvgLab1() {
		double expected = (88.0 + 87.0 + 81.0) / 3;
		assertEquals(expected, ga.getAvgLab1(), 0.0001);
	}

	@Test
	public void testGetAvgLab2() {
		double expected = (92.0 + 86.0 + 32.0) / 3;
		assertEquals(expected, ga.getAvgLab2(), 0.0001);
	}

	@Test
	public void testGetAvgLab3() {
		double expected = (88.0 + 98.0 + 50.0) / 3;
		assertEquals(expected, ga.getAvgLab3(), 0.0001);
	}

	@Test
	public void testGetAvgMidTerm() {
		double expected = (98.0 + 88.0 + 90.0) / 3;
		assertEquals(expected, ga.getAvgMidTerm(), 0.0001);
	}

	@Test
	public void testGetAvgFinalExam() {
		double expected = (91.0 + 87.0 + 93.0) / 3;
		assertEquals(expected, ga.getAvgFinalExam(), 0.0001);
	}

	@Test
	public void testGetAvgTotalGrade() {
		double expected = (88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4
						+  87 * 0.1 + 86 * 0.1 + 98 * 0.1 + 88 * 0.3 + 87 * 0.4
						+  81 * 0.1 + 32 * 0.1 + 50 * 0.1 + 90 * 0.3 + 93 * 0.4) / 3;
		assertEquals(expected, ga.getAvgTotalGrade(), 0.0001);
	}
	
}
