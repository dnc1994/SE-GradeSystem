package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* class: TestIntegration
 * 包含兩個整合測試
 * 由於系統全部的邏輯都在 GradeSystem 類中執行，而 UserInterface 又將其包裹了一層
 * 所以兩個整合測試分別測試這兩個類
 */
public class TestIntegration {
	UserInterface ui = null;
	GradeSystem gs = null;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/* testcase: integrationTest1
	 * 整合測試 #1
	 */
	@Test
	public void integrationTest1() throws FileNotFoundException, UnsupportedEncodingException {
		ui = new UserInterface("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput.txt");
		assertEquals("100", ui.parseIntScore(100));
		assertEquals("59*", ui.parseIntScore(59));
		assertEquals("100.0", ui.parseDoubleScore(99.99));
		assertEquals("59.9*", ui.parseDoubleScore(59.94));
		assertEquals(true, ui.checkAndSetID("962001044"));
		assertEquals("962001044", ui.currentID);
		assertEquals(false, ui.checkAndSetID("999999999"));
		assertEquals("962001044", ui.currentID);
	}
	
	/* testcase: integrationTest2
	 * 整合測試 #2
	 */
	@Test
	public void integrationTest2() throws FileNotFoundException, UnsupportedEncodingException {
		gs = new GradeSystem("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput.txt");
		int[] weights = new int[]{10, 10, 10, 30, 40};
		assertEquals(63, gs.getNumStudents());
		assertEquals(true, Arrays.equals(weights, gs.getWeights()));
		assertEquals(true, gs.containsID("955002056"));
		assertEquals("許文馨", gs.getNameByID("955002056"));
		assertEquals(14, gs.getRank("955002056"));
		GradeAccumulator ga = gs.getAverageGrade();
		assertEquals("89.5", String.format("%.1f", ga.getAvgTotalGrade()));
		int[] newWeights1 = new int[]{10, 10, 10, 10, 10};
		assertEquals(false, gs.checkAndSetWeights(newWeights1));
		int[] newWeights2 = new int[]{110, 0, 0, 0, -1};
		assertEquals(false, gs.checkAndSetWeights(newWeights2));
		int[] newWeights3 = new int[]{0, 0, 0, 0, 100};
		assertEquals(true, gs.checkAndSetWeights(newWeights3));
		Grade g1 = gs.getGrade("955002056");
		assertEquals(91, g1.getTotalGrade(), 0.001);
	}

}
