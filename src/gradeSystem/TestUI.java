package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/* class: TestUserInterface
 * 實現了對 UserInterface 類的測試
 */
public class TestUI {
	UI ui = null; 
	
	@Before
	public void setUp() throws Exception {
		ui = new UI("C://Users//linghao//eclipse-workspace//GradeSystem//gradeinput.txt");
	}

	@After
	public void tearDown() throws Exception {
		ui = null;
	}

	/* testcase: testParseIntScore
	 * 測試 UserInterface.parseIntScore
	 * 分為不低於 60 分和低於 60 分兩種情形
	 */
	@Test
	public void testParseIntScore() {
		assertEquals("100", ui.parseIntScore(100));
		assertEquals("59*", ui.parseIntScore(59));
	}
	
	/* testcase: testParseDoubleScore
	 * 測試 UserInterface.parseDoubleScore
	 * 分為不低於 60 分和低於 60 分兩種情形
	 */
	@Test
	public void testParseDoubleScore() {
		assertEquals("100.0", ui.parseDoubleScore(99.99));
		assertEquals("59.9*", ui.parseDoubleScore(59.94));
	}

	/* testcase: testCheckAndSetID
	 * 測試 UserInterface.checkAndSetID
	 * 分為設定的 ID 存在和不存在兩種情形
	 */
	@Test
	public void testCheckAndSetID() {
		assertEquals(true, ui.checkAndSetID("962001044"));
		assertEquals("962001044", ui.currentID);
		assertEquals(false, ui.checkAndSetID("999999999"));
		assertEquals("962001044", ui.currentID);
	}
	
}
