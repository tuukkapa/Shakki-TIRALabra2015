/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
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
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class PawnTest {
	
	Chessboard chessboard;
	
	public PawnTest() {
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
	public void testGetPossibleMovesWhiteNoCapture1() {
		System.out.println("Pawn, getPossibleMoves, white, no capture 1");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'P') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesWhiteNoCapture2() {
		System.out.println("Pawn, getPossibleMoves, white, no capture 2");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'P') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesWhiteNoCapture3() {
		System.out.println("Pawn, getPossibleMoves, white, no capture 3");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'P') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture1() {
		System.out.println("Pawn, getPossibleMoves, black, no capture 1");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'p') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture2() {
		System.out.println("Pawn, getPossibleMoves, black, no capture 2");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'p') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture3() {
		System.out.println("Pawn, getPossibleMoves, black, no capture 3");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'p') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of getPossibleMoves method, of class Rook.
	 */
	@Test
	public void testGetPossibleMovesWhiteCapture() {
		System.out.println("Pawn, getPossibleMoves, white, capture");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'P') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, true, true, true, false, false, false, false},
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
	public void testGetPossibleMovesBlackCapture() {
		System.out.println("Pawn, getPossibleMoves, black, capture");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) == 'p') {
				piece = chessboard.getPiece(i);
			}
		}
		ArrayList<Move> resultList = piece.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, true, true, false, false, false, false},
			{false, false, true, false, false, false, false, false},
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
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteTrue() {
		System.out.println("Pawn, isMoveValid, true move forwards");
		int end = 44;
		Pawn instance = (Pawn)chessboard.getPiece(52);
		boolean expResult = true;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteFalse() {
		System.out.println("Pawn, isMoveValid, false move diagonally without capture");
		int end = 45;
		Pawn instance = (Pawn)chessboard.getPiece(52);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteFalse2() {
		System.out.println("Pawn, isMoveValid, try to move backwards");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 45;
		Pawn instance = (Pawn)chessboard.getPiece(37);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteFalse3() {
		System.out.println("Pawn, isMoveValid, false");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 43;
		Pawn instance = (Pawn)chessboard.getPiece(53);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteFalse4() {
		System.out.println("Pawn, isMoveValid, false");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 36;
		Pawn instance = (Pawn)chessboard.getPiece(53);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidBlackFalse() {
		System.out.println("Pawn, isMoveValid, false, moving backwards");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 18;
		Pawn instance = (Pawn)chessboard.getPiece(26);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidBlackWrongCapture() {
		System.out.println("Pawn, isMoveValid, false, wrong capture");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 17;
		Pawn instance = (Pawn)chessboard.getPiece(10);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidFalseOutsideBoard1() {
		System.out.println("Pawn, isMoveValid, false, outside board 1");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 87;
		Pawn instance = (Pawn)chessboard.getPiece(37);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidFalseOutsideBoard2() {
		System.out.println("Pawn, isMoveValid, false, outside board 2");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = -5;
		Pawn instance = (Pawn)chessboard.getPiece(37);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of clone method, of class Pawn.
	 */
	@Test
	public void testClone() throws Exception {
		System.out.println("Pawn, clone");
		Pawn instance = (Pawn)chessboard.getPiece(48);
		Pawn clone = (Pawn)instance.clone();
		int expResult = instance.getPosition();
		int result = clone.getPosition();
		assertEquals(expResult, result);
	}
	
}
