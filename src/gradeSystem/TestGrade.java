package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/* class: TestGrade
 * 實現了對 Grade 類的測試
 */
public class TestGrade {
	private int[] weights = new int[]{10, 10, 10, 30, 40};
	Grade g = null;
	
	@Before
	public void setUp() throws Exception {
		g = new Grade("955002056", "許文馨", 88, 92, 88, 98, 91);
		g.calculateTotalGrade(weights);
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	/* testcase: testEquals
	 * 測試 Grade.equals
	 * 分為相同和不同的兩種情形
	*/
	@Test
	public void testEquals() {
		assertEquals(true, g.equals(new Grade("955002056", "許文馨", 88, 92, 88, 98, 91)));
		assertEquals(false, g.equals(new Grade()));
	}

	/* testcase: testGetLab1
	 * 測試 Grade.getLab1
	*/
	@Test
	public void testGetLab1() {
		assertEquals(88, g.getLab1());
	}

	/* testcase: testGetLab2
	 * 測試 Grade.getLab2
	*/
	@Test
	public void testGetLab2() {
		assertEquals(92, g.getLab2());
	}

	/* testcase: testGetLab3
	 * 測試 Grade.getLab3
	*/
	@Test
	public void testGetLab3() {
		assertEquals(88, g.getLab3());
	}

	/* testcase: testGetMidTerm
	 * 測試 Grade.getMidTerm
	*/
	@Test
	public void testGetMidTerm() {
		assertEquals(98, g.getMidTerm());
	}

	/* testcase: testGetFinalExam
	 * 測試 Grade.getFinalExam
	*/
	@Test
	public void testGetFinalExam() {
		assertEquals(91, g.getFinalExam());
	}

	/* testcase: testGetTotalGrade
	 * 測試 Grade.getTotalGrade
	 * 與手動計算得到的值比較
	*/
	@Test
	public void testGetTotalGrade() {
		double expected = 88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4;
		assertEquals(expected, g.getTotalGrade(), 0.0001);
	}

	/* testcase: testCalcluateTotalGrade
	 * 測試 Grade.calculateTotalGrade
	 * 與手動計算得到的值比較
	*/
	@Test
	public void testCalculateTotalGrade() {
		int weights[] = new int[]{10, 10, 10, 30, 40};
		double expected = 88 * 0.1 + 92 * 0.1 + 88 * 0.1 + 98 * 0.3 + 91 * 0.4;
		g.calculateTotalGrade(weights);
		assertEquals(expected, g.getTotalGrade(), 0.0001);
	}

}
