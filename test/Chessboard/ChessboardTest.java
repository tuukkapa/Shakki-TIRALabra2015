/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard;

import AI.Move;
import Chessboard.pieces.*;
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
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the result isn't null.
	 */
	@Test
	public void testGetWhitePiecesNotNull() {
		System.out.println("Chessboard, getPieces, white, not null");
		boolean white = true;
		ArrayList<Move> result = chessboard.getPieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the result isn't null.
	 */
	@Test
	public void testGetBlackPiecesNotNull() {
		System.out.println("Chessboard, getPieces, black, not null");
		boolean white = false;
		ArrayList<Move> result = chessboard.getPieces(white);
		assertNotNull(result);
	}
	
	/**
	 * Test of getPieces method, of class Chessboard.
	 * Tests that the resulting TreeMap includes Piece-objects.
	 */
	@Test
	public void testGetWhitePieces() {
		System.out.println("Chessboard, getPieces, white");
		boolean white = true;
		ArrayList<Piece> pieces = chessboard.getPieces(white);
		boolean result = true;
	    for(Piece piece : pieces) {
			if (!(piece instanceof Piece)) {
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
		System.out.println("Chessboard, getPieces, black");
		boolean white = false;
		ArrayList<Piece> pieces = chessboard.getPieces(white);
		boolean result = true;
	    for(Piece piece : pieces) {
			if (!(piece instanceof Piece)) {
				result = false;
			}
		}
		assertTrue(result);
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
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWrongMove() {
		System.out.println("Chessboard, movePiece, wrong input");
		Move move = new Move(8, 17);
		int start = 8;
		int end = 17;
		boolean expResult = false;
		boolean result = chessboard.makeMove(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceNonexistentPiece() {
		System.out.println("Chessboard, movePiece, nonexisting piece");
		int start = 16;
		int end = 24;
		Move move = new Move(16, 24);
		boolean expResult = false;
		boolean result = chessboard.makeMove(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWrongInputIsBoardIntact() {
		System.out.println("Chessboard, movePiece, wrong input, is board intact");
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
		char[][] resultBoard = chessboard.getBoardAsCharArray();
		assertArrayEquals(referenceBoard, resultBoard);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWhiteCorrectInput() {
		System.out.println("Chessboard, movePiece, white, correct input");
		int start = 48;
		int end = 40;
		Move move = new Move(48, 40);
		boolean expResult = true;
		boolean result = chessboard.makeMove(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWhiteCorrectInputBoardUpdated() {
		System.out.println("Chessboard, movePiece, white, correct input, board updated");
		int start = 48;
		int end = 40;
		Move move = new Move(48, 40);
		char expectedStart = ' ', expectedEnd = 'P';
		int expectedPosition = end;
		boolean moveResult = chessboard.makeMove(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start).getSign();
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}

	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackCorrectInput() {
		System.out.println("Chessboard, movePiece, black, correct input");
		int start = 8;
		int end = 16;
		Move move = new Move(8, 16);
		boolean expResult = true;
		boolean result = chessboard.makeMove(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackCorrectInputBoardUpdated() {
		System.out.println("Chessboard, movePiece, black, correct input, board updated");
		int start = 11;
		int end = 27;
		Move move = new Move(11, 27);
		char expectedStart = ' ', expectedEnd = 'p';
		int expectedPosition = end;
		boolean moveResult = chessboard.makeMove(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start).getSign();
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceCapture() {
		System.out.println("Chessboard, movePiece, capture");
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
		Move move = new Move(8, 17);
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
		System.out.println("Chessboard, cloneBoard, not null");
		char[][] result = chessboard.cloneBoard();
		assertNotNull(result);
	}
	
	/**
	 * Test of cloneBoard method, of class Chessboard.
	 */
	@Test
	public void testCloneBoard() {
		System.out.println("Chessboard, cloneBoard");
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
		System.out.println("Chessboard, getBoard");
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
		System.out.println("Chessboard, setBoard");
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
		System.out.println("Chessboard, isItCheck for white");
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
		System.out.println("Chessboard, isItCheck for white");
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
		System.out.println("Chessboard, isItCheck for black in Check situation");
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
		System.out.println("Chessboard, isItCheck for black in non check situation");
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
		System.out.println("Chessboard, wouldItBeCheck in Check situation for White");
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
		System.out.println("Chessboard, wouldItBeCheck in Check situation for Black");
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
		System.out.println("Chessboard, wouldItBeCheck in non Check situation for Black");
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
		System.out.println("Chessboard, isItCheckMate for white in checkmate situation");
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
		System.out.println("Chessboard, isItCheckMate for white in checkmate situation");
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
		System.out.println("Chessboard, isItCheckMate for black in checkmate situation");
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
		System.out.println("Chessboard, isItCheckMate for black in checkmate situation");
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
		System.out.println("Chessboard, getValue");
		int expResult = 0;
		int result = chessboard.getValue();
		assertEquals(expResult, result);
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
			if (referenceBoard[i/8][i%8] != chessboard.getSquareContents(i/8, i%8)) {
				result = false;
				break;
			}
		}
		assertTrue(result);
	}
	
}
