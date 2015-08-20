/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chessboard.pieces;

import AI.Move;
import Chessboard.Chessboard;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tuukka
 */
public class KingTest {
	
	private Chessboard chessboard;
	
	public KingTest() {
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
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesWhiteStartingPosition() {
		System.out.println("King: getPossibleMoves, white, starting position");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		Piece king = chessboard.getSquareContents(60);
		ArrayList<Move> expResult = new ArrayList<>();
		ArrayList<Move> result = king.getPossibleMoves(chessboard);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesBlackStartingPosition() {
		System.out.println("King: getPossibleMoves, black, starting position");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		Piece king = chessboard.getSquareContents(4);
		ArrayList<Move> expResult = new ArrayList<>();
		ArrayList<Move> result = king.getPossibleMoves(chessboard);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesWhiteNoCapture1() {
		System.out.println("King: getPossibleMoves, white, No Capture 1");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'K', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, true, true, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture1() {
		System.out.println("King: getPossibleMoves, black, no capture 1");
		char[][] newboard = {
			{' ', 'n', ' ', 'q', ' ', 'b', 'n', ' '},
			{'p', 'p', 'k', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'K', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, true, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, true, true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}

	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesWhiteNoCapture2() {
		System.out.println("King: getPossibleMoves, white, No Capture 2");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'K', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, true, true, false},
			{false, false, false, false, true, false, true, false},
			{false, false, false, false, true, true, true, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture2() {
		System.out.println("King: getPossibleMoves, black, No Capture 2");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', ' ', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'k', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'K', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, true, true, false, false, false, false},
			{false, true, false, true, false, false, false, false},
			{false, true, true, true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesWhiteOneSquareCheckedAndCapture() {
		System.out.println("King: getPossibleMoves, white, one square checked and capture");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'p', 'K', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, true, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, true, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}
	
	/**
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesBlackOneSquareCheckedAndCapture() {
		System.out.println("King: getPossibleMoves, white, one square checked and capture");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', ' ', 'b', 'n', ' '},
			{'p', 'P', 'k', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'K', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, true, false, false, false, false, false, false},
			{false, false, true, true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}
		};
		int correctMovementsExpected = 0;
		for (int i = 0; i < 64; i++) {
			if (expectedMovementMap[i/8][i%8]) {
				correctMovementsExpected++;
			}
		}
		int correctMovementsResult = 0;
		boolean noWrongMovementFound = true;
		for (int i = 0; i < resultList.size(); i++) {
			if (expectedMovementMap[resultList.get(i).getEnd()/8][resultList.get(i).getEnd()%8]) {
				correctMovementsResult++;
			} else {
				noWrongMovementFound = false;
			}
		}
		assertTrue(noWrongMovementFound && correctMovementsExpected == correctMovementsResult);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidTopLeft() {
		System.out.println("King: isMoveValid, correct move top left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 26;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidTopCenter() {
		System.out.println("King: isMoveValid, correct move top Center");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 27;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidTopRight() {
		System.out.println("King: isMoveValid, correct move top left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 28;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidCenterLeft() {
		System.out.println("King: isMoveValid, correct move center left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 34;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidCenterRight() {
		System.out.println("King: isMoveValid, correct move center right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 36;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidBottomLeft() {
		System.out.println("King: isMoveValid, correct move bottom left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 42;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidBottomCenter() {
		System.out.println("King: isMoveValid, correct move bottom center");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 43;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidBottomRight() {
		System.out.println("King: isMoveValid, correct move bottom Right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'K', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 44;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidWrongMove() {
		System.out.println("King: isMoveValid, Wrong Move");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'k', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 17;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidWrongMoveCauseCheck() {
		System.out.println("King: isMoveValid, Wrong Move, cause check");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'k', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 26;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}

	/**
	 * Test of clone method, of class Pawn.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testClone() throws Exception {
		System.out.println("King, clone");
		King instance = (King)chessboard.getSquareContents(60);
		King clone = (King)instance.clone();
		int expResult = instance.getPosition();
		int result = clone.getPosition();
		assertEquals(expResult, result);
	}
	
}
