package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;

/* class: TestGradeSystem
 * 實現了對 GradeSystem 類的測試
 */
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
	
	/* testcase: testReadGradesFileNotFoundException
	 * 測試 GradeSystem.readGrades 是否能在指定的文件不存在時正確拋出 FileNotFoundException
	 * 與手動計算得到的值比較
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadGradesFileNotFoundException() throws FileNotFoundException, UnsupportedEncodingException {
		gs.readGrades("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_not_exist.txt");
	}
	
//  後來發現正常情況下即使文件編碼不是指定的 UTF-8 也不會觸發 UnsupportedEncodingException，就先注釋掉了
//	@Test(expected=UnsupportedEncodingException.class)
//	public void testReadGradesUnsupportedEncodingException() throws FileNotFoundException, UnsupportedEncodingException {
//		GradeSystem gs_ = new GradeSystem("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput_ANSI.txt");
//	}
	
	/* testcase: testParseLineParseException
	 * 測試 GradeSystem.parseLine 是否能在傳入的字符串以空格分割后不為 7 個字段時正確拋出 ParseException
	 */	
	@Test(expected=ParseException.class)
	public void testParseLineParseException() throws ParseException {
		@SuppressWarnings("unused")
		String[] info = gs.parseLine("955002056 許文馨 88 92 88 98");
	}

	/* testcase: testParseLineParse
	 * 測試 GradeSystem.parseLineParse
	 */	
	@Test
	public void testParseLineParse() throws ParseException {
		String[] info = gs.parseLine("955002056 許文馨 88 92 88 98 91");
		assertEquals("許文馨", info[1]);
	}

	/* testcase: testGetNumStudents
	 * 測試 GradeSystem.getNumStudents
	 */	
	@Test
	public void testGetNumStudents() {
		assertEquals(63, gs.getNumStudents());
	}

	/* testcase: testGetWeights
	 * 測試 GradeSystem.getWeights
	 */
	@Test
	public void testGetWeights() {
		int[] weights = new int[]{10, 10, 10, 30, 40};
		assertEquals(true, Arrays.equals(weights, gs.getWeights()));
	}

	/* testcase: testCheckAndSetWeights
	 * 測試 GradeSystem.checkAndSetWeights
	 * 分為成功修改配分、配分相加不為 100 以及有單項配分為負三種情形
	 */
	@Test
	public void testCheckAndSetWeights() {
		int[] testWeights1 = new int[]{10, 10, 10, 30, 40};
		assertEquals(true, gs.checkAndSetWeights(testWeights1));
		int[] testWeights2 = new int[]{10, 10, 10, 30, 30};
		assertEquals(false, gs.checkAndSetWeights(testWeights2));
		int[] testWeights3 = new int[]{10, 10, 10, 80, -10};
		assertEquals(false, gs.checkAndSetWeights(testWeights3));
	}

	/* testcase: testContainsID
	 * 測試 GradeSystem.containsID
	 * 分為查詢的 ID 存在和不存在兩種情形
	 */	
	@Test
	public void testContainsID() {
		assertEquals(true, gs.containsID("955002056"));
		assertEquals(false, gs.containsID("999999999"));
	}

	/* testcase: testGetNameByID
	 * 測試 GradeSystem.getNameByID
	 * 分為查詢的 ID 存在和不存在兩種情形
	 */
	@Test
	public void testGetNameByID() {
		assertEquals("許文馨", gs.getNameByID("955002056"));
		assertEquals("", gs.getNameByID("999999999"));
	}

	/* testcase: testGetGrade
	 * 測試 GradeSystem.getGrade
	 * 分為查詢的 ID 存在和不存在兩種情形
	 */
	@Test
	public void testGetGrade() {
		Grade g1 = gs.getGrade("955002056");
		assertEquals(true, g1.equals(new Grade("955002056", "許文馨", 88, 92, 88, 98, 91)));
		Grade g2 = gs.getGrade("999999999");
		assertEquals(true, g2.equals(new Grade()));
	}

	/* testcase: testGetRank
	 * 測試 GradeSystem.getRank
	 * 分為查詢的 ID 存在和不存在兩種情形
	 */
	@Test
	public void testGetRank() {
		assertEquals(14, gs.getRank("955002056"));
		assertEquals(-1, gs.getRank("999999999"));
	}

	/* testcase: testGetAverageGrade
	 * 測試 GradeSystem.getAverageGrade
	 */
	@Test
	public void testGetAverageGrade() {
		GradeAccumulator ga = gs.getAverageGrade();
		assertEquals("89.5", String.format("%.1f", ga.getAvgTotalGrade()));
	}

}
