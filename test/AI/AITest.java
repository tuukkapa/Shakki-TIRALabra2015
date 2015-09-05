/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package AI;

import Chessboard.Move;
import Chessboard.Chessboard;
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
public class AITest {
	
	private Chessboard chessboard;
	private AI ai;
	
	public AITest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		chessboard = new Chessboard();
		ai = new AI(false);
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of minimax method, of class AI, depth 1.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth1() throws Exception {
		System.out.println("AI, minimax, maximizing, depth 1");
		int depth = 1;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI, depth 2.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth2() throws Exception {
		System.out.println("AI, minimax, maximizing, depth 2");
		int depth = 2;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI, depth 3.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth3() throws Exception {
		System.out.println("AI, minimax, maximizing, depth 3");
		int depth = 3;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI, depth 4.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth4() throws Exception {
		System.out.println("AI, minimax, maximizing, depth 4");
		int depth = 4;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI, depth 5.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth5() throws Exception {
		System.out.println("AI, minimax, maximizing, depth 5");
		int depth = 5;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of getColour method, of class AI, getColour-method.
	 */
	@Test
	public void testGetColour() {
		System.out.println("AI, getColour");
		boolean expResult = false;
		boolean result = ai.getColour();
		assertEquals(expResult, result);
	}
	
}
