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
	 */
	@Test
	public void testEvaluateStartingPositionBlackPOW() {
		System.out.println("evaluate, starting position, black POW");
		boolean white = false;
		int expResult = 0;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 */
	@Test
	public void testEvaluateStartingPositionWhitePOW() {
		System.out.println("evaluate, starting position, black POW");
		boolean white = true;
		int expResult = 0;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
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
		int expResult = 23905;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
	 */
	@Test
	public void testEvaluateWhiteAllBlackNoneBlackPOW() {
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
		int expResult = -23905;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
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
		int expResult = 40;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
	/**
	 * Test of evaluate method, of class Evaluate.
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
		int expResult = 40;
		int result = Evaluate.evaluate(chessboard, white);
		assertTrue(expResult + 20 > result && expResult <= result || expResult - 20 < result && expResult >= result);
	}
	
}
