package gradeSystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void testParseIntScore() {
		assertEquals("100", ui.parseIntScore(100));
		assertEquals("59*", ui.parseIntScore(59));
	}
	
	@Test
	public void testParseDoubleScore() {
		assertEquals("100.0", ui.parseDoubleScore(99.99));
		assertEquals("59.9*", ui.parseDoubleScore(59.94));
	}

	@Test
	public void testCheckAndSetID() {
		assertEquals(true, ui.checkAndSetID("962001044"));
		assertEquals("962001044", ui.currentID);
		assertEquals(false, ui.checkAndSetID("999999999"));
		assertEquals("962001044", ui.currentID);
	}
	
}
