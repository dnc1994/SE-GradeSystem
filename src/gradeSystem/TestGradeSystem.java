package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class TestGradeSystem {
	/**
	 * @uml.property  name="gs"
	 * @uml.associationEnd  
	 */
	GradeSystem gs = null;
	
	@Before
	public void setUp() throws Exception {
		gs = new GradeSystem("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput.txt");
	}

	@After
	public void tearDown() throws Exception {
		gs = null;
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testReadGradesFileNotFoundException() throws FileNotFoundException, UnsupportedEncodingException {
		GradeSystem gs_ = new GradeSystem("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_not_exist.txt");
	}
	
//	@Test(expected=UnsupportedEncodingException.class)
//	public void testReadGradesUnsupportedEncodingException() throws FileNotFoundException, UnsupportedEncodingException {
//		GradeSystem gs_ = new GradeSystem("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_ANSI.txt");
//	}
	
	@Test(expected=ParseException.class)
	public void testParseLineParseException() throws ParseException {
		String[] info = gs.parseLine("955002056 許文馨 88 92 88 98");
	}
	
	@Test
	public void testParseLineParse() throws ParseException {
		String[] info = gs.parseLine("955002056 許文馨 88 92 88 98 91");
		assertEquals("許文馨", info[1]);
	}
	
	@Test
	public void testGetNumStudents() {
		assertEquals(63, gs.getNumStudents());
	}

	@Test
	public void testCheckAndSetWeights() {
		int[] testWeights1 = new int[]{10, 10, 10, 30, 40};
		assertEquals(true, gs.checkAndSetWeights(testWeights1));
		int[] testWeights2 = new int[]{10, 10, 10, 30, 30};
		assertEquals(false, gs.checkAndSetWeights(testWeights2));
		int[] testWeights3 = new int[]{10, 10, 10, 80, -10};
		assertEquals(false, gs.checkAndSetWeights(testWeights3));
	}
	
	@Test
	public void testContainsID() {
		String testID1 = "955002056";
		boolean testFlag1 = gs.containsID(testID1);
		assertEquals(true, testFlag1);
		String testID2 = "999999999";
		boolean testFlag2 = gs.containsID(testID2);
		assertEquals(false, testFlag2);
	}

	@Test
	public void testGetNameByID() {
		String testID1 = "955002056";
		String testName1 = gs.getNameByID(testID1);
		assertEquals("許文馨", testName1);
		String testID2 = "999999999";
		String testName2 = gs.getNameByID(testID2);
		assertEquals("", testName2);
	}

	@Test
	public void testGetGrade() {
		String testID1 = "955002056";
		Grade testGrade1 = gs.getGrade(testID1);
		assertEquals(true, testGrade1.equals(new Grade("955002056", "許文馨", 88, 92, 88, 98, 91)));
		String testID2 = "999999999";
		Grade testGrade2 = gs.getGrade(testID2);
		assertEquals(true, testGrade2.equals(new Grade()));
	}

	@Test
	public void testGetRank() {
		String testID1 = "955002056";
		int testRank1 = gs.getRank(testID1);
		assertEquals(14, testRank1);
		String testID2 = "999999999";
		int testRank2 = gs.getRank(testID2);
		assertEquals(-1, testRank2);
	}

	@Test
	public void testGetAverageGrade() {
		GradeAccumulator ga = gs.getAverageGrade();
		assertEquals("89.5", String.format("%.1f", ga.getAvgTotalGrade()));
	}

}
