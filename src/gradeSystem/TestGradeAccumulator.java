package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGradeAccumulator {
	GradeAccumulator ga = null;
	Grade g = null;
	
	@Before
	public void setUp() throws Exception {
		ga = new GradeAccumulator();
		g = new Grade("955002056", "許文馨", 88, 92, 88, 98, 91);
	}

	@After
	public void tearDown() throws Exception {
		ga = null;
	}
	
	@Test
	public void testAverageGrade() {
		ga.addGrade(g);
		ga.averageGrade(1);
		assertEquals(g.getLab1(), ga.getAvgLab1(), 0.0001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAverageGradeIllegalArgumentException() {
		ga.averageGrade(0);
	}
	
}
