package a8se2020ws;


import a8se2020ws.assignment8_int.Reconstructor;

/**
 * Test for A8 WS2020
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

public class A8Test {

	private Reconstructor rec;
	
	@Before
	public void setUp() throws Exception {
		rec = new A8SE2020WS();
	}

	@Test
	public void testReturnCls() {
		assertNull("Check null return",rec.reconstructFromClass(null));
	}
	
	@Test
	public void testReturnClsName() {
		try{
			assertNull("Check null return",rec.reconstructFromClassName(null));
		}
		catch(Exception e){
			fail("Should not throw exception on argument = null");
		}
	}
}
