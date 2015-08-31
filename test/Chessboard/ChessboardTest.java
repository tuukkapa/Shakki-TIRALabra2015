/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard;

import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Queen;
import Chessboard.pieces.Rook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class ChessboardTest {
	
	private Chessboard chessboard;
	
	public ChessboardTest() {
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
	 * Test of method getBoardAsCharArray, from Chessboard.
	 */
	@Test
	public void testGetBoardAsCharArray() {
		System.out.println("Chessboard, getBoardAsCharArray");
		char[][] referenceArray = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] result = chessboard.getBoardAsCharArray();
		Assert.assertArrayEquals(referenceArray, result);
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceWhite() {
		System.out.println("Chessboard, switchPiece, switch white piece");
		Piece originalPiece = chessboard.getSquareContents(48);
		Piece newPiece = new Queen(true, 48);
		chessboard.switchPiece(originalPiece, newPiece);
		Piece resultPiece = chessboard.getSquareContents(48);
		assertEquals(resultPiece, newPiece);
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceBlack() {
		System.out.println("Chessboard, switchPiece, switch black piece");
		Piece originalPiece = chessboard.getSquareContents(12);
		Piece newPiece = new Queen(false, 12);
		chessboard.switchPiece(originalPiece, newPiece);
		Piece resultPiece = chessboard.getSquareContents(12);
		assertEquals(resultPiece, newPiece);
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceNewIsNull() {
		System.out.println("Chessboard, switchPiece, switch piece into null piece");
		Piece originalPiece = chessboard.getSquareContents(12);
		Piece newPiece = null;
		boolean result = chessboard.switchPiece(originalPiece, newPiece);
		Piece resultPiece = chessboard.getSquareContents(12);
		assertTrue(!result && resultPiece.equals(originalPiece));
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceOrigIsNull() {
		System.out.println("Chessboard, switchPiece, switch null into new piece");
		Piece squareContents = chessboard.getSquareContents(12);
		Piece originalPiece = null;
		Piece newPiece = new Pawn(false, 12);
		boolean result = chessboard.switchPiece(originalPiece, newPiece);
		Piece resultPiece = chessboard.getSquareContents(12);
		assertTrue(!result && resultPiece.equals(squareContents));
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceDifferentPositions() {
		System.out.println("Chessboard, switchPiece, different positions");
		Piece originalPieceAt12 = chessboard.getSquareContents(12);
		Piece originalPieceAt14 = chessboard.getSquareContents(14);
		Piece newPiece = new Pawn(false, 22);
		boolean result = chessboard.switchPiece(originalPieceAt12, newPiece);
		Piece resultPieceAt12 = chessboard.getSquareContents(12);
		Piece resultPieceAt14 = chessboard.getSquareContents(14);
		assertTrue(!result && originalPieceAt12.equals(resultPieceAt12) && originalPieceAt14.equals(resultPieceAt14));
	}
	
	/**
	 * Test of method switchPiece, from Chessboard.
	 */
	@Test
	public void testSwitchPieceOldPieceIsntOnBoard() {
		System.out.println("Chessboard, switchPiece, old piece isn't on board");
		Piece originalPiece = chessboard.getSquareContents(12);
		Piece wrongOriginalPiece = new Rook(false, 12);
		Piece newPiece = new Pawn(false, 12);
		boolean result = chessboard.switchPiece(wrongOriginalPiece, newPiece);
		Piece resultPiece = chessboard.getSquareContents(12);
		assertTrue(!result && originalPiece.equals(resultPiece));
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWrong1() {
		System.out.println("Chessboard, updatePiecePosition, wrong position 1");
		boolean expected = false;
		boolean result = chessboard.updatePiecePosition(-1, 0);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWrong2() {
		System.out.println("Chessboard, updatePiecePosition, wrong position 2");
		boolean expected = false;
		boolean result = chessboard.updatePiecePosition(64, 0);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWrong3() {
		System.out.println("Chessboard, updatePiecePosition, wrong position 3");
		boolean expected = false;
		boolean result = chessboard.updatePiecePosition(0, -1);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWrong4() {
		System.out.println("Chessboard, updatePiecePosition, wrong position 4");
		boolean expected = false;
		boolean result = chessboard.updatePiecePosition(0, 64);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionStartIsNull() {
		System.out.println("Chessboard, updatePiecePosition, start coordinates contains null");
		boolean expected = false;
		boolean result = chessboard.updatePiecePosition(16, 24);
		assertEquals(expected, result);
		assertNull(chessboard.getSquareContents(24));
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWithPieceParameterWrong1() {
		System.out.println("Chessboard, updatePiecePosition with piece parameter, wrong position 1");
		boolean expected = false;
		Piece piece = chessboard.getSquareContents(8);
		boolean result = chessboard.updatePiecePosition(piece, -1);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWithPieceParameterWrong2() {
		System.out.println("Chessboard, updatePiecePosition with piece parameter, wrong position 2");
		boolean expected = false;
		Piece piece = chessboard.getSquareContents(8);
		boolean result = chessboard.updatePiecePosition(piece, 64);
		assertEquals(expected, result);
	}
	
	/**
	 * Test of method updatePiecePosition, from Chessboard.
	 */
	@Test
	public void testUpdatePiecePositionWithPieceParameterPieceIsNull() {
		System.out.println("Chessboard, updatePiecePosition with piece parameter, piece is null");
		boolean expected = false;
		Piece piece = null;
		boolean result = chessboard.updatePiecePosition(piece, 17);
		assertEquals(expected, result);
		assertNull(chessboard.getSquareContents(17));
	}
	
	/**
	 * Test of getPiece method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents() {
		System.out.println("Chessboard, getSquareContents");
		char[][] referencePieces = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		boolean result = true;
		for (int i = 0; i < 64; i++) {
			if (i < 16) {
				if (chessboard.getSquareContents(i) == null) {
					result = false;
				}
				if (referencePieces[i/8][i%8] != chessboard.getSquareContents(i).getSign()) {
					result = false;
				}
				if (chessboard.getSquareContents(i).amIWhite()) {
					result = false;
				}
				if (chessboard.getSquareContents(i).getPosition() != i) {
					result = false;
				}
			} else if (i >= 16 && i < 48) {
				if (chessboard.getSquareContents(i) != null) {
					result = false;
				}
			} else {
				if (chessboard.getSquareContents(i) == null) {
					result = false;
				}
				if (referencePieces[i/8][i%8] != chessboard.getSquareContents(i).getSign()) {
					result = false;
				}
				if (!chessboard.getSquareContents(i).amIWhite()) {
					result = false;
				}
				if (chessboard.getSquareContents(i).getPosition() != i) {
					result = false;
				}
			}
		}
		assertTrue(result);
	}

	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int() {
		System.out.println("Chessboard, getSquareContents");
		char[][] referenceBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		boolean result = true;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null) {
				if (referenceBoard[i/8][i%8] != chessboard.getSquareContents(i).getSign()) {
					result = false;
					break;
				}
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_intWrongPosition1() {
		System.out.println("Chessboard, getSquareContents int, wrong position 1");
		Piece result = chessboard.getSquareContents(-1);
		assertNull(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_intWrongPosition2() {
		System.out.println("Chessboard, getSquareContents int, wrong position 2");
		Piece result = chessboard.getSquareContents(64);
		assertNull(result);
	}

	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_int() {
		System.out.println("Chessboard, getSquareContents");
		char[][] referenceBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		boolean result = true;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i/8, i%8) != null) {
				if (referenceBoard[i/8][i%8] != chessboard.getSquareContents(i/8, i%8).getSign()) {
					result = false;
					break;
				}
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_intWrongPosition1() {
		System.out.println("Chessboard, getSquareContents int int, wrong position 1");
		Piece result = chessboard.getSquareContents(-1, 0);
		assertNull(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_intWrongPosition2() {
		System.out.println("Chessboard, getSquareContents int int, wrong position 2");
		Piece result = chessboard.getSquareContents(8, 0);
		assertNull(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_intWrongPosition3() {
		System.out.println("Chessboard, getSquareContents int int, wrong position 3");
		Piece result = chessboard.getSquareContents(0, -1);
		assertNull(result);
	}
	
	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_intWrongPosition4() {
		System.out.println("Chessboard, getSquareContents int int, wrong position 4");
		Piece result = chessboard.getSquareContents(0, 8);
		assertNull(result);
	}
	
}