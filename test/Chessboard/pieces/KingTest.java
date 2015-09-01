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
		chessboard.setBoard(newboard);
		Piece king = chessboard.getSquareContents(60);

		List<Move> result = king.getPossibleMoves(chessboard);
		assertTrue(result.size() == 0);
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
		chessboard.setBoard(newboard);
		Piece king = chessboard.getSquareContents(4);
		List<Move> result = king.getPossibleMoves(chessboard);
		assertTrue(result.size() == 0);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
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
	 * Test of getPossibleMoves method, of class King.
	 */
	@Test
	public void testGetPossibleMovesWhiteKingCastleRight() {
		System.out.println("King: getPossibleMoves, white, Castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false, false, false, false,  true,  true, false}
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
	public void testGetPossibleMovesWhiteKingCastleRightKingHasMoved() {
		System.out.println("King: getPossibleMoves, white, Castle right, King has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		king.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false, false, false, false,  true, false, false}
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
	public void testGetPossibleMovesWhiteKingCastleRightRookHasMoved() {
		System.out.println("King: getPossibleMoves, white, Castle right, Rook has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		Rook rook = (Rook)chessboard.getSquareContents(63);
		rook.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false, false, false, false,  true, false, false}
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
	public void testGetPossibleMovesWhiteKingCastleLeft() {
		System.out.println("King: getPossibleMoves, white, Castle left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false,  true,  true, false, false, false, false}
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
	public void testGetPossibleMovesWhiteKingCastleLeftKingHasMoved() {
		System.out.println("King: getPossibleMoves, white, Castle left, King has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		king.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false, false,  true, false, false, false, false}
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
	public void testGetPossibleMovesWhiteKingCastleLeftRookHasMoved() {
		System.out.println("King: getPossibleMoves, white, Castle left, Rook has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		Rook rook = (Rook)chessboard.getSquareContents(56);
		rook.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
			{false, false, false,  true, false, false, false, false}
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
	public void testGetPossibleMovesBlackKingCastleRight() {
		System.out.println("King: getPossibleMoves, black, Castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false,  true,  true, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesBlackKingCastleRightKingHasMoved() {
		System.out.println("King: getPossibleMoves, black, Castle right, King has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		king.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false,  true, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesBlackKingCastleRightRookHasMoved() {
		System.out.println("King: getPossibleMoves, black, Castle right, Rook has moved");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		Rook rook = (Rook)chessboard.getSquareContents(7);
		rook.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false,  true, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesBlackKingCastleLeft() {
		System.out.println("King: getPossibleMoves, black, Castle left");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false,  true,  true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMoveBlackKingCastleLeftKingHasMoved() {
		System.out.println("King: getPossibleMoves, black, Castle left, King has moved");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		king.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false,  true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesBlackKingCastleLeftRookHasMoved() {
		System.out.println("King: getPossibleMoves, black, Castle left, Rook has moved");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		Rook rook = (Rook)chessboard.getSquareContents(0);
		rook.setHasMoved(true);
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false,  true, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesWhiteKingCastleRightWrongRook() {
		System.out.println("King: getPossibleMoves, black, Castle left, wrong rook");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'N'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', ' ', ' ', 'r'}
		};
		chessboard.setBoard(newboard);
		King king = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
				king = (King)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = king.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false,  true, false, false},
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
		chessboard.setBoard(newboard);
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
	 * Test of isMoveValid method, of class King.
	 */
	@Test
	public void testIsMoveValidWhiteCastleRight() {
		System.out.println("King: isMoveValid, white, castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 62;
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
	public void testIsMoveValidWhiteCastleRightNoRook() {
		System.out.println("King: isMoveValid, white, castle right, no rook");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 62;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidWhiteCastleRightRouteBlocked() {
		System.out.println("King: isMoveValid, white, castle right, route blocked");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 62;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidWhiteCastleRightCheckOnRoute() {
		System.out.println("King: isMoveValid, white, castle right, check on route");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 62;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidWhiteCastleLeft() {
		System.out.println("King: isMoveValid, white, castle left");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 58;
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
	public void testIsMoveValidWhiteCastleLeftNoRook() {
		System.out.println("King: isMoveValid, white, castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 58;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidWhiteCastleLeftRouteBlocked() {
		System.out.println("King: isMoveValid, white, castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', ' ', ' ', 'B', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 58;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidWhiteCastleLeftCheckOnRoute() {
		System.out.println("King: isMoveValid, white, check on route");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'b', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', ' ', ' ', 'B', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 58;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'K') {
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
	public void testIsMoveValidBlackCastleRight() {
		System.out.println("King: isMoveValid, black, castle right");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 6;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
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
	public void testIsMoveValidBlackCastleRightNoRook() {
		System.out.println("King: isMoveValid, black, castle right no rook");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 6;
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
	public void testIsMoveValidBlackCastleRightRouteBlocked() {
		System.out.println("King: isMoveValid, black, castle right, route blocked");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', ' ', 'r'},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 6;
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
	public void testIsMoveValidBlackCastleRightCheckOnRoute() {
		System.out.println("King: isMoveValid, black, check on route");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
			{' ', ' ', ' ', 'p', 'p', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', 'R', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		int end = 6;
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
	public void testIsMoveValidBlackCastleLeft() {
		System.out.println("King: isMoveValid, black, castle left");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 2;
		Piece piece = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'k') {
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
	public void testIsMoveValidBlackCastleLeftNoRook() {
		System.out.println("King: isMoveValid, black, castle left, no rook");
		char[][] newboard = {
			{' ', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{'r', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 2;
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
	public void testIsMoveValidBlackCastleLeftRouteBlocked() {
		System.out.println("King: isMoveValid, black, castle left, route blocked");
		char[][] newboard = {
			{'r', ' ', ' ', 'q', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 2;
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
	public void testIsMoveValidBlackCastleLeftCheckOnRoute() {
		System.out.println("King: isMoveValid, black, castle left, check on route");
		char[][] newboard = {
			{'r', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
			{' ', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		int end = 2;
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
