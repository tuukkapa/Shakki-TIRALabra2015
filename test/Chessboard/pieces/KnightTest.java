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
public class KnightTest {
	
	private Chessboard chessboard;
	
	public KnightTest() {
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
		System.out.println("Knight, getPossibleMoves, white");
		char[][] newboard = {
			{'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', 'P', ' ', 'P', 'P', 'P'},
			{' ', ' ', 'B', 'Q', 'K', 'B', ' ', ' '}
		};
		chessboard = new Chessboard(newboard);
		Knight knight = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
				knight = (Knight)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = knight.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, true, false, true, false, false, false},
			{false, true, false, false, false, true, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, false, false, false, true, false, false},
			{false, false, true, false, true, false, false, false},
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
	public void testGetPossibleMovesBlackSomeBlockedSomeCapture() {
		System.out.println("Knight, getPossibleMoves, black, some blocked, some captured");
		char[][] newboard = {
			{'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'n', ' ', ' ', ' '},
			{' ', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', 'P', ' ', 'P', 'P', 'P'},
			{' ', ' ', 'B', 'Q', 'K', 'B', ' ', ' '}
		};
		chessboard = new Chessboard(newboard);
		Knight knight = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'n') {
				knight = (Knight)chessboard.getSquareContents(i);
			}
		}
		ArrayList<Move> resultList = knight.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, true, false, true, false, false},
			{false, false, true, false, false, false, true, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, true, false},
			{false, false, false, true, false, true, false, false},
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
	public void testIsMoveValidCorrectInput1() {
		System.out.println("Knight, isMoveValid, correct input 1");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 20;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'n') {
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
	public void testIsMoveValidCorrectInput2() {
		System.out.println("Knight, isMoveValid, correct input 2");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 29;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidCorrectInput3() {
		System.out.println("Knight, isMoveValid, correct input 3");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 45;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'n') {
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
	public void testIsMoveValidCorrectInput4() {
		System.out.println("Knight, isMoveValid, correct input 4");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 52;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'n') {
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
	public void testIsMoveValidCorrectInput5() {
		System.out.println("Knight, isMoveValid, correct input 5");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 50;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidCorrectInput6() {
		System.out.println("Knight, isMoveValid, correct input 6");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 50;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidCorrectInput7() {
		System.out.println("Knight, isMoveValid, correct input 7");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 25;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidCorrectInput8() {
		System.out.println("Knight, isMoveValid, correct input 8");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 18;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidWrongInput() {
		System.out.println("Knight, isMoveValid, wrong input");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 37;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
	public void testIsMoveValidWrongInputCaptureOwn() {
		System.out.println("Knight, isMoveValid, wrong input, capture own");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'R', ' ', ' '},
			{' ', ' ', ' ', 'N', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'P', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 29;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
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
		System.out.println("Knight, isMoveValid, wrong input, cause check");
		char[][] newboard = {
			{' ', ' ', 'b', 'q', 'k', 'b', ' ', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', ' ', ' ', 'N', 'P', 'P', 'P'},
			{'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard = new Chessboard(newboard);
		int end = 35;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'N') {
				piece = chessboard.getSquareContents(i);
			}
		}
		boolean expResult = false;
		boolean result = piece.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
}
