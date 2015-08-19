/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package AI;

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
		ai = new AI();
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of minimax method, of class AI.
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth1() throws Exception {
		System.out.println("minimax, maximizing, depth 1");
		int depth = 1;
		boolean maximizingPlayer = true;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth2() throws Exception {
		System.out.println("minimax, maximizing, depth 2");
		int depth = 2;
		boolean maximizingPlayer = true;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth3() throws Exception {
		System.out.println("minimax, maximizing, depth 3");
		int depth = 3;
		boolean maximizingPlayer = true;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testMinimaxMaximizingPlayerDepth4() throws Exception {
		System.out.println("minimax, maximizing, depth 4");
		int depth = 4;
		boolean maximizingPlayer = true;
		Move result = ai.getMove(chessboard, depth);
		assertNotNull(result);
	}
	
	/**
	 * Test of minimax method, of class AI.
	 * @throws java.lang.Exception
	 */
	/*@Test
	public void testMinimaxMaximizingPlayerDepth5() throws Exception {
		System.out.println("minimax, maximizing, depth 5");
		int depth = 5;
		boolean maximizingPlayer = true;
		Move result = ai.minimax(chessboard, depth, maximizingPlayer);
		assertNotNull(result);
	}*/
	
	/**
	 * Test of minimax method, of class AI.
	 * @throws java.lang.Exception
	 */
	/*@Test
	public void testMinimaxMinimizingPlayerDepth5() throws Exception {
		System.out.println("minimax, minimizing, depth 5");
		int depth = 5;
		boolean maximizingPlayer = false;
		Move result = ai.minimax(chessboard, depth, maximizingPlayer);
		assertNotNull(result);
	}
	*/
}
