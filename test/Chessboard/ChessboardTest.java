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
		ArrayList<Piece> result = chessboard.getPieces(white);
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
		ArrayList<Piece> result = chessboard.getPieces(white);
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
	public void testMovePieceMoveWhitePawnIllegally1() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 1");
		Move move = new Move(48, 48);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally2() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 2");
		Move move = new Move(48, 39);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally3() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 3");
		Move move = new Move(48, 41);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally4() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 4");
		Move move = new Move(48, 49);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally5() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 5");
		Move move = new Move(48, 55);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally6() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 6");
		Move move = new Move(48, 56);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally7() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 7");
		Move move = new Move(48, 57);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally8() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 8");
		Move move = new Move(48, 31);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveWhitePawnIllegally9() {
		System.out.println("Chessboard, movePiece, move white Pawn illegally 9");
		Move move = new Move(48, 33);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally1() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 1");
		Move move = new Move(10, 17);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally2() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 2");
		Move move = new Move(10, 9);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally3() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 3");
		Move move = new Move(10, 1);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally4() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 4");
		Move move = new Move(10, 2);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally5() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 5");
		Move move = new Move(10, 3);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally6() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 6");
		Move move = new Move(10, 11);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally7() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 7");
		Move move = new Move(10, 19);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally8() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 8");
		Move move = new Move(10, 25);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceMoveBlackPawnIllegally9() {
		System.out.println("Chessboard, movePiece, move black Pawn illegally 9");
		Move move = new Move(10, 27);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceNonexistentPiece() {
		System.out.println("Chessboard, movePiece, nonexisting piece");
		Move move = new Move(16, 24);
		boolean expResult = false;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWrongInputIsBoardIntact() {
		System.out.println("Chessboard, movePiece, wrong input, is board intact");
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
	public void testMovePieceWhitePawn1Up() {
		System.out.println("Chessboard, movePiece, white, 1 up");
		Move move = new Move(50, 42);
		boolean expResult = true;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceWhitePawn2Up() {
		System.out.println("Chessboard, movePiece, white, 2 up");
		Move move = new Move(51, 35);
		boolean expResult = true;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackPawn1Up() {
		System.out.println("Chessboard, movePiece, black, 1 up");
		Move move = new Move(14, 22);
		boolean expResult = true;
		boolean result = chessboard.movePiece(move);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceBlackPawn2Up() {
		System.out.println("Chessboard, movePiece, black, 2 up");
		Move move = new Move(13, 29);
		boolean expResult = true;
		boolean result = chessboard.movePiece(move);
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
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
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
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMoveBlackPawnCaptureRight() {
		System.out.println("Chessboard, movePiece, black Pawn capture right");
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
		chessboard = new Chessboard(referenceBoard);
		int start = 8;
		int end = 17;
		Move move = new Move(8, 17);
		char expectedStart = ' ', expectedEnd = 'p';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMoveBlackPawnCaptureLeft() {
		System.out.println("Chessboard, movePiece, black Pawn capture left");
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
		chessboard = new Chessboard(referenceBoard);
		int start = 10;
		int end = 17;
		Move move = new Move(10, 17);
		char expectedStart = ' ', expectedEnd = 'p';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMoveWhitePawnCaptureRight() {
		System.out.println("Chessboard, movePiece, black Pawn capture right");
		char[][] referenceBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(referenceBoard);
		int start = 50;
		int end = 43;
		Move move = new Move(start, end);
		char expectedStart = ' ', expectedEnd = 'P';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
	}
	
	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMoveWhitePawnCaptureLeft() {
		System.out.println("Chessboard, movePiece, black Pawn capture right");
		char[][] referenceBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = new Chessboard(referenceBoard);
		int start = 50;
		int end = 41;
		Move move = new Move(start, end);
		char expectedStart = ' ', expectedEnd = 'P';
		int expectedPosition = end;
		boolean moveResult = chessboard.movePiece(move);
		int resultPosition = chessboard.getSquareContents(expectedPosition).getPosition();
		char resultStart = chessboard.getSquareContents(start) == null ? ' ' : 'x';
		char resultEnd = chessboard.getSquareContents(end).getSign();
		assertTrue(moveResult && expectedStart == resultStart && expectedEnd == resultEnd && expectedPosition == resultPosition);
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
		chessboard = new Chessboard(newboard);
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
		chessboard = new Chessboard(newboard);
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
		chessboard = new Chessboard(newboard);
		Piece piece = chessboard.getSquareContents(52);
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
		chessboard = new Chessboard(newboard);
		Piece piece = chessboard.getSquareContents(52);
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
		chessboard = new Chessboard(newboard);
		Piece piece = chessboard.getSquareContents(12);
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
		chessboard = new Chessboard(newboard);
		Piece piece = chessboard.getSquareContents(12);
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
		chessboard = new Chessboard(newboard);
		int expResult = 1;
		int result = chessboard.isItCheckMate();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForWhiteInNonCheckMateSituation() {
		System.out.println("Chessboard, isItCheckMate for white in checkmate situation");
		int expResult = -1;
		int result = chessboard.isItCheckMate();
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
		chessboard = new Chessboard(newboard);
		int expResult = 0;
		int result = chessboard.isItCheckMate();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheckMate method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckMateForBlackInNonCheckMateSituation() {
		System.out.println("Chessboard, isItCheckMate for black in checkmate situation");
		int expResult = -1;
		int result = chessboard.isItCheckMate();
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
	
}