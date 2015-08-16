/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard;

import Chessboard.pieces.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
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
	 * Test of clonePieces method with white pieces, of class Chessboard.
	 * Tests that the result isn't null.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneWhitePiecesIsNotNull() throws Exception {
		System.out.println("clonePieces, white, result isn't null");
		boolean white = true;
		TreeMap result = chessboard.clonePieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of clonePieces method with white pieces, of class Chessboard.
	 * Tests that the result isn't null.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneBlackPiecesIsNotNull() throws Exception {
		System.out.println("clonePieces, black, result isn't null");
		boolean white = false;
		TreeMap result = chessboard.clonePieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of clonePieces method with white pieces, of class Chessboard.
	 * Tests that the resulting TreeMap is different than original.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneWhitePiecesDifferentTreeMap() throws Exception {
		System.out.println("clonePieces, white, resulting TreeMap is different");
		boolean white = true;
		TreeMap original = chessboard.getPieces(white);
		TreeMap result = chessboard.clonePieces(white);
		assertNotSame(original, result);
	}
	
	/**
	 * Test of clonePieces method with black pieces, of class Chessboard.
	 * Tests that the resulting TreeMap is different than original.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneBlackPiecesDifferentTreeMap() throws Exception {
		System.out.println("clonePieces, black, resulting TreeMap is different");
		boolean white = false;
		TreeMap original = chessboard.getPieces(white);
		TreeMap result = chessboard.clonePieces(white);
		assertNotSame(original, result);
	}
	
	/**
	 * Test of clonePieces method with white pieces, of class Chessboard.
	 * Tests with one piece, that the objects in the cloned TreeMap are different
	 * from those in the original TreeMap.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneWhitePiecesDifferentPieces() throws Exception {
		System.out.println("clonePieces, white, resulting TreeMap is different");
		boolean white = true;
		TreeMap originalTreeMap = chessboard.getPieces(white);
		Piece original = (Piece)originalTreeMap.firstEntry().getValue();
		TreeMap cloneTreeMap = chessboard.clonePieces(white);
		Piece result = (Piece)cloneTreeMap.firstEntry().getValue();
		assertNotSame(original, result);
	}
	
	/**
	 * Test of clonePieces method with black pieces, of class Chessboard.
	 * Tests with one piece, that the objects in the cloned TreeMap are different
	 * from those in the original TreeMap.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCloneBlackPiecesDifferentPieces() throws Exception {
		System.out.println("clonePieces, black, resulting TreeMap is different");
		boolean white = false;
		TreeMap originalTreeMap = chessboard.getPieces(white);
		Piece original = (Piece)originalTreeMap.firstEntry().getValue();
		TreeMap cloneTreeMap = chessboard.clonePieces(white);
		Piece result = (Piece)cloneTreeMap.firstEntry().getValue();
		assertNotSame(original, result);
	}

	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the result isn't null.
	 */
	@Test
	public void testGetWhitePiecesNotNull() {
		System.out.println("getPieces, white, not null");
		boolean white = true;
		TreeMap result = chessboard.getPieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the result isn't null.
	 */
	@Test
	public void testGetBlackPiecesNotNull() {
		System.out.println("getPieces, black, not null");
		boolean white = false;
		TreeMap result = chessboard.getPieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the resulting TreeMap includes Piece-objects.
	 */
	@Test
	public void testGetWhitePieces() {
		System.out.println("getPieces, white");
		boolean white = true;
		TreeMap pieces = chessboard.getPieces(white);
		boolean result = true;
		Collection c = pieces.values();
		Iterator itr = c.iterator();
	    while(itr.hasNext()) {
			if (!(itr.next() instanceof Piece)) {
				result = false;
			}
		}
		assertTrue(result);
	}

	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the resulting TreeMap includes Piece-objects.
	 */
	@Test
	public void testGetBlackPieces() {
		System.out.println("getPieces, black");
		boolean white = false;
		TreeMap pieces = chessboard.getPieces(white);
		boolean result = true;
		Collection c = pieces.values();
		Iterator itr = c.iterator();
	    while(itr.hasNext()) {
			if (!(itr.next() instanceof Piece)) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of setPieces method, of class Chessboard with white pieces.
	 */
	@Test
	public void testSetWhitePieces() {
		System.out.println("setPieces, white");
		boolean white = true;
		TreeMap<Integer, Piece> newPieces = new TreeMap<>();
		for (int i = 0; i < 8; i++) {
			newPieces.put(48 + i, new Pawn(white, 48 + i));
		}
		chessboard.setPieces(white, newPieces);
		boolean treeMapNotEmpty = chessboard.getPieces(white).size() > 0;
		boolean piecesExist = true;
		int counter = 0;
		Collection c = chessboard.getPieces(white).values();
		Iterator itr = c.iterator();
	    while(itr.hasNext()) {
			if (!(itr.next() instanceof Piece)) {
				piecesExist = false;
			}
			counter++;
		}
		assertTrue(piecesExist && treeMapNotEmpty && counter == 8);
	}
	
	/**
	 * Test of setPieces method, of class Chessboard with böacl pieces.
	 */
	@Test
	public void testSetBlackPieces() {
		System.out.println("setPieces, black");
		boolean white = false;
		TreeMap<Integer, Piece> newPieces = new TreeMap<>();
		for (int i = 0; i < 8; i++) {
			newPieces.put(8 + i, new Pawn(white, 8 + i));
		}
		chessboard.setPieces(white, newPieces);
		boolean treeMapNotEmpty = chessboard.getPieces(white).size() > 0;
		boolean piecesExist = true;
		int counter = 0;
		Collection c = chessboard.getPieces(white).values();
		Iterator itr = c.iterator();
	    while(itr.hasNext()) {
			if (!(itr.next() instanceof Piece)) {
				piecesExist = false;
			}
			counter++;
		}
		assertTrue(piecesExist && treeMapNotEmpty && counter == 8);
	}

	/**
	 * Test of getPiece method, of class Chessboard.
	 */
	@Test
	public void testGetPiece() {
		System.out.println("getPiece");
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
				if (chessboard.getPiece(i) == null) {
					result = false;
				}
				if (referencePieces[i/8][i%8] != chessboard.getPiece(i).getSign()) {
					result = false;
				}
				if (chessboard.getPiece(i).amIWhite()) {
					result = false;
				}
				if (chessboard.getPiece(i).getPosition() != i) {
					result = false;
				}
			} else if (i >= 16 && i < 48) {
				if (chessboard.getPiece(i) != null) {
					result = false;
				}
			} else {
				if (chessboard.getPiece(i) == null) {
					result = false;
				}
				if (referencePieces[i/8][i%8] != chessboard.getPiece(i).getSign()) {
					result = false;
				}
				if (!chessboard.getPiece(i).amIWhite()) {
					result = false;
				}
				if (chessboard.getPiece(i).getPosition() != i) {
					result = false;
				}
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWrongMove() {
		System.out.println("movePiece, wrong input");
		int start = 8;
		int end = 17;
		boolean expResult = false;
		boolean result = chessboard.movePiece(start, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceNonexistentPiece() {
		System.out.println("movePiece, nonexisting piece");
		int start = 16;
		int end = 24;
		boolean expResult = false;
		boolean result = chessboard.movePiece(start, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWrongInputIsBoardIntact() {
		System.out.println("movePiece, wrong input, is board intact");
		int start = 8;
		int end = 17;
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

		char[][] resultBoard = chessboard.getBoard();
		assertArrayEquals(referenceBoard, resultBoard);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWhiteCorrectInput() {
		System.out.println("movePiece, white, correct input");
		int start = 48;
		int end = 40;
		boolean expResult = true;
		boolean result = chessboard.movePiece(start, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWhiteCorrectInputBoardUpdated() {
		System.out.println("movePiece, white, correct input, board updated");
		int start = 48;
		int end = 40;
		char expectedStart = ' ', expectedEnd = 'P';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(start, end);
		int resultPosition = chessboard.getPiece(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start);
		char resultEnd = chessboard.getSquareContents(end);
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}

	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackCorrectInput() {
		System.out.println("movePiece, black, correct input");
		int start = 8;
		int end = 16;
		boolean expResult = true;
		boolean result = chessboard.movePiece(start, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackCorrectInputBoardUpdated() {
		System.out.println("movePiece, black, correct input, board updated");
		int start = 11;
		int end = 27;
		char expectedStart = ' ', expectedEnd = 'p';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(start, end);
		int resultPosition = chessboard.getPiece(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start);
		char resultEnd = chessboard.getSquareContents(end);
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceCapture() {
		System.out.println("movePiece, capture");
		char[][] referenceBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(referenceBoard);
		int start = 8;
		int end = 17;
		char expectedStart = ' ', expectedEnd = 'p';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(start, end);
		int resultPosition = chessboard.getPiece(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start);
		char resultEnd = chessboard.getSquareContents(end);
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}

	/**
	 * Test of cloneBoard method, of class Chessboard.
	 */
	@Test
	public void testCloneBoardNotNull() {
		System.out.println("cloneBoard, not null");
		char[][] result = chessboard.cloneBoard();
		assertNotNull(result);
	}
	
	/**
	 * Test of cloneBoard method, of class Chessboard.
	 */
	@Test
	public void testCloneBoard() {
		System.out.println("cloneBoard");
		char[][] expResult = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] result = chessboard.cloneBoard();
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of getBoard method, of class Chessboard.
	 */
	@Test
	public void testGetBoard() {
		System.out.println("getBoard");
		char[][] expResult = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] result = chessboard.getBoard();
		assertArrayEquals(expResult, result);
	}

	/**
	 * Test of setBoard method, of class Chessboard.
	 */
	@Test
	public void testSetBoard() {
		System.out.println("setBoard");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		assertArrayEquals(newboard, chessboard.getBoard());
	}

	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckForWhiteInCheckSituation() {
		System.out.println("isItCheck for white");
		boolean checkedIsWhite = true;
		boolean expResult = true;
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean result = chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckForWhiteInNonCheckSituation() {
		System.out.println("isItCheck for white");
		boolean checkedIsWhite = true;
		boolean expResult = false;
		boolean result = chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckForBlackInCheckSituation() {
		System.out.println("isItCheck for black in Check situation");
		boolean checkedIsWhite = false;
		boolean expResult = true;
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean result = chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckForBlackInNonCheckSituation() {
		System.out.println("isItCheck for black in non check situation");
		boolean checkedIsWhite = false;
		boolean expResult = false;
		boolean result = chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of wouldItBeCheck method, of class Chessboard.
	 */
	@Test
	public void testWouldItBeCheckInCheckSituationForWhite() {
		System.out.println("wouldItBeCheck in Check situation for White");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'Q', 'P', 'P', 'P'},
			{'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = chessboard.getPiece(52);
		int end = 45;
		boolean expResult = true;
		boolean result = chessboard.wouldItBeCheck(piece, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of wouldItBeCheck method, of class Chessboard.
	 */
	@Test
	public void testWouldItBeCheckInNonCheckSituationForWhite() {
		System.out.println("wouldItBeCheck in non Check situation for White");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = chessboard.getPiece(52);
		int end = 36;
		boolean expResult = false;
		boolean result = chessboard.wouldItBeCheck(piece, end);
		assertEquals(expResult, result);
	}

	/**
	 * Test of wouldItBeCheck method, of class Chessboard.
	 */
	@Test
	public void testWouldItBeCheckInCheckSituationForBlack() {
		System.out.println("wouldItBeCheck in Check situation for Black");
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = chessboard.getPiece(12);
		int end = 21;
		boolean expResult = true;
		boolean result = chessboard.wouldItBeCheck(piece, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of wouldItBeCheck method, of class Chessboard.
	 */
	@Test
	public void testWouldItBeCheckInNonCheckSituationForBlack() {
		System.out.println("wouldItBeCheck in non Check situation for Black");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = chessboard.getPiece(12);
		int end = 28;
		boolean expResult = false;
		boolean result = chessboard.wouldItBeCheck(piece, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForWhiteInCheckMateSituation() {
		System.out.println("isItCheckMate for white in checkmate situation");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'q', 'r', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean checkedIsWhite = true;
		boolean expResult = true;
		boolean result = chessboard.isItCheckMate(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForWhiteInNonCheckMateSituation() {
		System.out.println("isItCheckMate for white in checkmate situation");
		boolean checkedIsWhite = true;
		boolean expResult = false;
		boolean result = chessboard.isItCheckMate(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForBlackInCheckMateSituation() {
		System.out.println("isItCheckMate for black in checkmate situation");
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', ' ', 'n', 'r'},
			{'p', 'p', 'p', ' ', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', 'Q', 'R', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean checkedIsWhite = false;
		boolean expResult = true;
		boolean result = chessboard.isItCheckMate(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForBlackInNonCheckMateSituation() {
		System.out.println("isItCheckMate for black in checkmate situation");
		boolean checkedIsWhite = false;
		boolean expResult = false;
		boolean result = chessboard.isItCheckMate(checkedIsWhite);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getValue method, of class Chessboard.
	 */
	@Test
	public void testGetValue() {
		System.out.println("getValue");
		int expResult = 0;
		int result = chessboard.getValue();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int() {
		System.out.println("getSquareContents");
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
			if (referenceBoard[i/8][i%8] != chessboard.getSquareContents(i)) {
				result = false;
				break;
			}
		}
		assertTrue(result);
	}

	/**
	 * Test of getSquareContents method, of class Chessboard.
	 */
	@Test
	public void testGetSquareContents_int_int() {
		System.out.println("getSquareContents");
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
			if (referenceBoard[i/8][i%8] != chessboard.getSquareContents(i/8, i%8)) {
				result = false;
				break;
			}
		}
		assertTrue(result);
	}
	
}
