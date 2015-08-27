/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chessboard.pieces;

import Chessboard.Move;
import Chessboard.Chessboard;
import Chessboard.ChessboardHandler;
import DataStructures.List;
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
public class BishopTest {
	
	private Chessboard chessboard;
	
	public BishopTest() {
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
	public void testGetPossibleMovesBlackBottomLeft() {
		System.out.println("Bishop, getPossibleMoves, black bottom left");
		char[][] newboard = {
			{' ', 'n', ' ', 'q', 'k', ' ', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'b', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Bishop bishop = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				bishop = (Bishop)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = bishop.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, false, false, false, true, false, false},
			{false, false, true, false, true, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, true, false, false, false},
			{false, true, false, false, false, true, false, false},
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
	public void testGetPossibleMovesBlackTopLeft() {
		System.out.println("Bishop, getPossibleMoves, black top left");
		char[][] newboard = {
			{' ', 'n', ' ', 'q', 'k', ' ', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Bishop bishop = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				bishop = (Bishop)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = bishop.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, true, false, true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, false, true, false, false, false, false},
			{true, false, false, false, true, false, false, false},
			{false, false, false, false, false, true, false, false},
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
	public void testGetPossibleMovesBlackTopRight() {
		System.out.println("Bishop, getPossibleMoves, black top right");
		char[][] newboard = {
			{' ', 'n', ' ', 'q', 'k', ' ', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', 'b', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Bishop bishop = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				bishop = (Bishop)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = bishop.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, true, false},
			{false, false, false, true, false, false, false, true},
			{false, false, true, false, false, false, false, false},
			{false, true, false, false, false, false, false, false},
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
	public void testGetPossibleMovesBlackBottomRight() {
		System.out.println("Bishop, getPossibleMoves, black bottom right");
		char[][] newboard = {
			{' ', 'n', ' ', 'q', 'k', ' ', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', 'b', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Bishop bishop = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				bishop = (Bishop)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = bishop.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, false, false, false, false},
			{false, false, false, true, false, false, false, true},
			{false, false, false, false, true, false, true, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, true, false},
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
	
	@Test
	public void testIsMoveValidCorrectInputCaptureNE() {
		System.out.println("Bishop, isMoveValid, correct input capture NE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'b', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 14;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputCaptureSE() {
		System.out.println("Bishop, isMoveValid, correct input capture SE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'p', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 53;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputCaptureSW() {
		System.out.println("Bishop, isMoveValid, correct input capture SW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 49;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputCaptureNW() {
		System.out.println("Bishop, isMoveValid, correct input capture NW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 8;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputNE() {
		System.out.println("Bishop, isMoveValid, correct input NE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'b', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 22;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputSE() {
		System.out.println("Bishop, isMoveValid, correct input SE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 53;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputSW() {
		System.out.println("Bishop, isMoveValid, correct input SW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 49;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidCorrectInputNW() {
		System.out.println("Bishop, isMoveValid, correct input NW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{' ', ' ', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 8;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = true;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}

	@Test
	public void testIsMoveValidWrongInputTryCaptureNE() {
		System.out.println("Bishop, isMoveValid, wrong input try capture NE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'b', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 15;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputTryCaptureSE() {
		System.out.println("Bishop, isMoveValid, wrong input try capture SE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 53;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputTryCaptureSW() {
		System.out.println("Bishop, isMoveValid, wrong input try capture SW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 49;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputTryCaptureNW() {
		System.out.println("Bishop, isMoveValid, correct input NW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'R', ' ', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 8;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputNoRouteNE() {
		System.out.println("Bishop, isMoveValid, wrong input no route NE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', 'r', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'b', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 22;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'b') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputNoRouteSE() {
		System.out.println("Bishop, isMoveValid, wrong input no route SE");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 53;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputNoRouteSW() {
		System.out.println("Bishop, isMoveValid, wrong input no route SW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 49;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputNoRouteNW() {
		System.out.println("Bishop, isMoveValid, correct input NW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'R', ' ', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 8;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputTryCauseCheck() {
		System.out.println("Bishop, isMoveValid, correct input NW");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'R', ' ', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'b', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'B', 'P', 'P', 'P', 'P'},
			{' ', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 44;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testIsMoveValidWrongInputMoveHorizontally() {
		System.out.println("Bishop, isMoveValid, correct input move horizontally");
		char[][] newboard = {
			{'r', 'n', ' ', 'q', 'k', ' ', 'n', 'r'},
			{'R', ' ', ' ', 'p', 'p', 'p', 'Q', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'b', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'p', 'P', 'B', 'P', 'P', 'P', 'P'},
			{' ', 'N', ' ', 'Q', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 25;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'B') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
}
