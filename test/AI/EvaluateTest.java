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
public class EvaluateTest {
	
	Chessboard chessboard;
	
	public EvaluateTest() {
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
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black POW
	 */
	@Test
	public void testEvaluateStartingPositionBlackPOW() {
		System.out.println("evaluate, starting position, black POW");
		boolean white = false;
		int expResult = 0;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black POW.
	 */
	@Test
	public void testEvaluateStartingPositionWhitePOW() {
		System.out.println("evaluate, starting position, black POW");
		boolean white = true;
		int expResult = 0;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white has all, black has none, white POW.
	 */
	@Test
	public void testEvaluateWhiteAllBlackNoneWhitePOW() {
		System.out.println("evaluate, starting position, white has all, black has none, white POW");
		char[][] newboard = {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = 20000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult < result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white has all, black has none, black POW.
	 */
	@Test
	public void testEvaluateWhiteAllBlackeNoneBlackPOW() {
		System.out.println("evaluate, starting position, white has all, black has none, black POW");
		char[][] newboard = {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = -20000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult > result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white has all, black has none, black POW.
	 */
	@Test
	public void testEvaluateWhitePawnUp() {
		System.out.println("evaluate, starting position, white has all, black has none, black POW");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = 970;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white has all, black has none, black POW.
	 */
	@Test
	public void testEvaluateBlackPawnUp() {
		System.out.println("evaluate, starting position, white has all, black has none, black POW");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = 970;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white checked, white POW.
	 */
	@Test
	public void testEvaluateWhiteCheckedWhitePOW() {
		System.out.println("evaluate, starting position, white checked, white POW");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = -500;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult > result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white checked, black POW.
	 */
	@Test
	public void testEvaluateWhiteCheckedBlackPOW() {
		System.out.println("evaluate, starting position, white checked, black POW");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = 500;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult < result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black checked, white POW.
	 */
	@Test
	public void testEvaluateBlackCheckedWhitePOW() {
		System.out.println("evaluate, starting position, black checked, white POW");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = 500;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult < result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black checked, black POW.
	 */
	@Test
	public void testEvaluateBlackCheckedBlackPOW() {
		System.out.println("evaluate, starting position, black checked, black POW");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = -500;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult > result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white check mate, white POW.
	 */
	@Test
	public void testEvaluateWhiteCheckMateWhitePOW() {
		System.out.println("evaluate, starting position, white check mate, white POW");
		char[][] newboard = {
			{'Q', 'n', ' ', ' ', 'k', ' ', ' ', ' '},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'q', 'r', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = -120000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult > result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, white check mate, black POW.
	 */
	@Test
	public void testEvaluateWhiteCheckMateBlackPOW() {
		System.out.println("evaluate, starting position, white check mate, black POW");
		char[][] newboard = {
			{'Q', 'n', ' ', ' ', 'k', ' ', ' ', ' '},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'q', 'r', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = 120000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult < result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black check mate, white POW.
	 */
	@Test
	public void testEvaluateBlackCheckMateWhitePOW() {
		System.out.println("evaluate, starting position, black check mate, white POW");
		char[][] newboard = {
			{'r', 'n', ' ', ' ', 'k', ' ', ' ', 'r'},
			{'p', 'p', 'p', ' ', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', 'Q', 'R', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'q', 'N', ' ', ' ', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		boolean white = true;
		int expResult = 120000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult < result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * Starting position, black check mate, black POW.
	 */
	@Test
	public void testEvaluateBlackCheckMateBlackPOW() {
		System.out.println("evaluate, starting position, black check mate, black POW");
		char[][] newboard = {
			{'r', 'n', ' ', ' ', 'k', ' ', ' ', 'r'},
			{'p', 'p', 'p', ' ', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', 'Q', 'R', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'q', 'N', ' ', ' ', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = -120000;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertTrue(expResult > result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 * End game for black, black POW.
	 */
	@Test
	public void testEvaluateEndGameForBlack() {
		System.out.println("evaluate, end game for black, black POW");
		char[][] newboard = {
			{'r', 'n', ' ', ' ', 'k', ' ', ' ', 'r'},
			{'p', 'p', 'p', ' ', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = -1320;
		int result = Evaluate.evaluate(chessboard, white, 0);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 */
	@Test
	public void testEvaluateWhiteCheckedVarianceBlackPOW() {
		System.out.println("evaluate, starting position, white checked, with variance, black POW");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean white = false;
		int expResult = 540;
		int result = Evaluate.evaluate(chessboard, white, 100);
		assertTrue(expResult != result);
	}
	
}
