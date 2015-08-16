/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package AI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class MoveTest {
	
	public MoveTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getScore method, of class Move.
	 */
	@Test
	public void testGetScore() {
		System.out.println("getScore");
		Move instance = new Move(20, 52, 44);
		int expResult = 20;
		int result = instance.getScore();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getStart method, of class Move.
	 */
	@Test
	public void testGetStart() {
		System.out.println("getStart");
		Move instance = new Move(20, 52, 44);
		int expResult = 52;
		int result = instance.getStart();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getEnd method, of class Move.
	 */
	@Test
	public void testGetEnd() {
		System.out.println("getEnd");
		Move instance = new Move(20, 52, 44);
		int expResult = 44;
		int result = instance.getEnd();
		assertEquals(expResult, result);
	}
	
}
