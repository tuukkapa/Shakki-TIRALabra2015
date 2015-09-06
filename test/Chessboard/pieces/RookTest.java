/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard.pieces;

import Chessboard.Move;
import Chessboard.Chessboard;
import DataStructures.List;
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
public class RookTest {
	
	private Chessboard chessboard;
	
	public RookTest() {
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesWhite() {
		System.out.println("Rook, getPossibleMoves, white");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Rook rook = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				rook = (Rook)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = rook.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{true, true, false, true, true, true, true, true},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesBlack() {
		System.out.println("Rook, getPossibleMoves, white");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'n', 'R', 'r', ' ', ' ', ' ', 'R', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Rook rook = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				rook = (Rook)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = rook.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, true, false, true, true, true, true, false},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputHorizontalLeft() {
		System.out.println("Rook, isMoveValid, correct input horizontal left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 32;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputHorizontalRight() {
		System.out.println("Rook, isMoveValid, correct input horizontal right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 39;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputVerticalUp() {
		System.out.println("Rook, isMoveValid, correct input vertical up");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 19;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputVerticalDown() {
		System.out.println("Rook, isMoveValid, correct input vertical down");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 51;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputCaptureHorizontalRight() {
		System.out.println("Rook, isMoveValid, correct input capture horizontal right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 39;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputCaptureHorizontalLeft() {
		System.out.println("Rook, isMoveValid, correct input capture horizontal left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'N', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 32;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputCaptureVerticalUp() {
		System.out.println("Rook, isMoveValid, correct input capture vertical up");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'R', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 11;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidCorrectInputCaptureVerticalDown() {
		System.out.println("Rook, isMoveValid, correct input capture vertical down");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'R', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'p', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 51;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputDiagonal() {
		System.out.println("Rook, isMoveValid, wrong input diagonal");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 42;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCaptureOwnVerticalUp() {
		System.out.println("Rook, isMoveValid, wrong input, capture own vertical up");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 11;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCaptureOwnVerticalDown() {
		System.out.println("Rook, isMoveValid, wrong input, capture own vertical down");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 51;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCaptureOwnHorizontalRight() {
		System.out.println("Rook, isMoveValid, wrong input, capture own horizontal right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 39;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCaptureOwnHorizontalLeft() {
		System.out.println("Rook, isMoveValid, wrong input, capture own horizontal left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'N', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 32;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputRouteBlockedVerticalUp() {
		System.out.println("Rook, isMoveValid, wrong input, route blocked vertical up");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 11;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputRouteBlockedVerticalDown() {
		System.out.println("Rook, isMoveValid, wrong input, route blocked vertical down");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 51;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputRouteBlockedHorizontalRight() {
		System.out.println("Rook, isMoveValid, wrong input, route blocked horizontal right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', 'r', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 39;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputRouteBlockedHorizontalLeft() {
		System.out.println("Rook, isMoveValid, wrong input, routeblocked horizontal left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'N', 'r', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 32;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputMoveOutsideBoard() {
		System.out.println("Rook, isMoveValid, wrong input, move outside board");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', 'n'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = -1;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'r') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCauseCheck() {
		System.out.println("Rook, isMoveValid, wrong input, cause check");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 40;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCauseCheck2() {
		System.out.println("Rook, isMoveValid, wrong input, cause check");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'b', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 43;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValidWrongInputCauseCheck3() {
		System.out.println("Rook, isMoveValid, wrong input, wrong move, cause check");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'b', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 35;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'R') {
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
		System.out.println("Rook, clone");
		Rook instance = (Rook)chessboard.getSquareContents(56);
		Rook clone = (Rook)instance.clone();
		int expResult = instance.getPosition();
		int result = clone.getPosition();
		assertEquals(expResult, result);
	}
	
}
