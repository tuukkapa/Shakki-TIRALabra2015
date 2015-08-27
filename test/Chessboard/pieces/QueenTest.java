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
public class QueenTest {
	
	private Chessboard chessboard;
	
	public QueenTest() {
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
		System.out.println("Queen, getPossibleMoves, white");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', ' ', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Queen queen = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'Q') {
				queen = (Queen)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = queen.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{true, false, false, true, false, false, true, false},
			{false, true, false, true, false, true, false, false},
			{false, false, true, true, true, false, false, false},
			{true, true, true, false, true, true, true, true},
			{false, false, true, true, true, false, false, false},
			{false, true, false, false, false, false, false, false},
			{true, false, false, false, false, false, false, false}
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
	public void testGetPossibleMovesSouldntCaptureKing() {
		System.out.println("Queen, getPossibleMoves, white, shouldn't capture enemy king");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'Q', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', ' ', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Queen queen = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'Q') {
				queen = (Queen)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = queen.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false,  true, false, false,  true, false, false,  true},
			{false, false,  true, false,  true, false,  true, false},
			{false, false, false,  true,  true,  true, false, false},
			{ true,  true,  true,  true, false,  true,  true,  true},
			{false, false, false,  true,  true,  true, false, false},
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
		System.out.println("Queen, getPossibleMoves, black");
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'q', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', ' ', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Queen queen = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'q') {
				queen = (Queen)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = queen.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, true, false, true, false, true, false, false},
			{false, false, true, true, true, false, false, false},
			{true, true, true, false, true, true, true, true},
			{false, false, true, true, true, false, false, false},
			{false, true, false, true, false, true, false, false},
			{true, false, false, false, false, false, false, false}
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
	public void testGetPossibleMovesBlackCauseCheck() {
		System.out.println("Queen, getPossibleMoves, black, cause check");
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'q', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'R', 'P', 'P', 'P'},
			{' ', 'N', 'B', ' ', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Queen queen = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'q') {
				queen = (Queen)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = queen.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, true, false, false, false},
			{false, false, false, false, false, false, false, false},
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
	public void testGetPossibleMovesNoMoves() {
		System.out.println("Queen, getPossibleMoves, black");
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'n', 'n', 'r', ' ', ' ', ' '},
			{' ', ' ', 'r', 'q', 'b', ' ', ' ', ' '},
			{' ', ' ', 'p', 'p', 'b', ' ', ' ', ' '},
			{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', 'N', 'B', ' ', 'K', 'B', 'N', ' '}
		};
		chessboard.setBoard(newboard);
		Queen queen = null;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'q') {
				queen = (Queen)chessboard.getSquareContents(i);
			}
		}
		List<Move> resultList = queen.getPossibleMoves(chessboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
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
	 * Test of isMoveValid method, of class Rook.
	 */
	@Test
	public void testIsMoveValid() {
		System.out.println("Queen, isMoveValid");
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', ' '},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'R', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		boolean[][] expectedMovementMap = {
			{false, false, false, false, false, false, false, false},
			{true, false, false, true, false, false, true, false},
			{false, true, false, true, false, true, false, false},
			{false, false, true, true, true, false, false, false},
			{false, true, true, false, true, true, true, true},
			{false, false, true, true, true, false, false, false},
			{false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false}
		};
		Piece piece = null;
		boolean result = true;
		for (int i = 0; i < 64; i++) {
			if (chessboard.getSquareContents(i) != null && chessboard.getSquareContents(i).getSign() == 'Q') {
				piece = chessboard.getSquareContents(i);
			}
		}
		for (int i = 0; i < 64; i++) {
			if (piece.isMoveValid(chessboard, i) != expectedMovementMap[i/8][i%8]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of clone method, of class Pawn.
	 * @throws java.lang.Exception
	 */
	@Test
	public void testClone() throws Exception {
		System.out.println("Queen, clone");
		Queen instance = (Queen)chessboard.getSquareContents(59);
		Queen clone = (Queen)instance.clone();
		int expResult = instance.getPosition();
		int result = clone.getPosition();
		assertEquals(expResult, result);
	}
	
	
}
