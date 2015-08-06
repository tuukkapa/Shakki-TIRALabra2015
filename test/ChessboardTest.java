/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ChessboardTest {

	
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
		Chessboard.makeTestBoard(false);
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of movePiece method, of class Chessboard.
	 */
	@Test
	public void testMovePieceCorrectInput() {
		System.out.println("movePiece correct input");
		String command = "c2c3";
		boolean expResult = true;
		boolean result = Chessboard.movePiece(command);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testMovePieceWrongInputString() {
		System.out.println("movePiece wrong input string");
		String command = "hello";
		boolean expResult = false;
		boolean result = Chessboard.movePiece(command);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testMovePieceWrongInputCoordinates() {
		System.out.println("movePiece wrong input coordinates");
		String command = "c9u9";
		boolean expResult = false;
		boolean result = Chessboard.movePiece(command);
		assertEquals(expResult, result);
	}

	/**
	 * Test of movePawn method, of class Chessboard.
	 */
	@Test
	public void testMovePawnCorrectInput1() {
		System.out.println("movePawn: move 1 square");
		int startRow = 6;
		int startCol = 2;
		int endRow = 5;
		int endCol = 2;
		boolean expResult = true;
		boolean result = Chessboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method, of class Chessboard.
	 */
	@Test
	public void testMovePawnCorrectInput2() {
		System.out.println("movePawn: move 2 squares from starting row");
		int startRow = 6;
		int startCol = 2;
		int endRow = 4;
		int endCol = 2;
		boolean expResult = true;
		boolean result = Chessboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveRook method, of class Chessboard.
	 */
	@Test
	public void testMoveRookVertically() {
		System.out.println("moveRook vertically");
		int startRow = 7;
		int startCol = 0;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = Chessboard.moveRook(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
		/**
	 * Test of moveRook method, of class Chessboard.
	 */
	@Test
	public void testMoveRookHorizontally() {
		System.out.println("moveRook horizontally");
		int startRow = 5;
		int startCol = 7;
		int endRow = 5;
		int endCol = 3;
		boolean expResult = true;
		boolean result = Chessboard.moveRook(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightCorrectNWW() {
		System.out.println("moveKnight NWW");
		int startRow = 7;
		int startCol = 1;
		int endRow = 6;
		int endCol = 3;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightNNW() {
		System.out.println("moveKnight NNW");
		int startRow = 7;
		int startCol = 1;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightNNE() {
		System.out.println("moveKnight NNE");
		int startRow = 7;
		int startCol = 6;
		int endRow = 5;
		int endCol = 5;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightNEE() {
		System.out.println("moveKnight NEE");
		int startRow = 7;
		int startCol = 6;
		int endRow = 6;
		int endCol = 4;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightSEE() {
		System.out.println("moveKnight SEE");
		int startRow = 2;
		int startCol = 4;
		int endRow = 3;
		int endCol = 6;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightSSE() {
		System.out.println("moveKnight SSE");
		int startRow = 2;
		int startCol = 4;
		int endRow = 4;
		int endCol = 5;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightSSW() {
		System.out.println("moveKnight SSW");
		int startRow = 2;
		int startCol = 4;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method, of class Chessboard.
	 */
	@Test
	public void testMoveKnightSWW() {
		System.out.println("moveKnight SWW");
		int startRow = 2;
		int startCol = 4;
		int endRow = 3;
		int endCol = 2;
		boolean expResult = true;
		boolean result = Chessboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveBishop method, of class Chessboard.
	 */
	@Test
	public void testMoveBishopNW() {
		System.out.println("moveBishop NW");
		int startRow = 7;
		int startCol = 2;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = Chessboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method, of class Chessboard.
	 */
	@Test
	public void testMoveBishopNE() {
		System.out.println("moveBishop NE");
		int startRow = 7;
		int startCol = 2;
		int endRow = 5;
		int endCol = 4;
		boolean expResult = true;
		boolean result = Chessboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method, of class Chessboard.
	 */
	@Test
	public void testMoveBishopSE() {
		System.out.println("moveBishop SE");
		int startRow = 2;
		int startCol = 2;
		int endRow = 4;
		int endCol = 4;
		boolean expResult = true;
		boolean result = Chessboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method, of class Chessboard.
	 */
	@Test
	public void testMoveBishopSW() {
		System.out.println("moveBishop SW");
		int startRow = 2;
		int startCol = 2;
		int endRow = 3;
		int endCol = 1;
		boolean expResult = true;
		boolean result = Chessboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenN() {
		System.out.println("moveQueen N");
		int startRow = 4;
		int startCol = 0;
		int endRow = 3;
		int endCol = 0;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenNE() {
		System.out.println("moveQueen NE");
		int startRow = 4;
		int startCol = 0;
		int endRow = 3;
		int endCol = 1;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenE() {
		System.out.println("moveQueen E");
		int startRow = 4;
		int startCol = 0;
		int endRow = 4;
		int endCol = 1;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenSE() {
		System.out.println("moveQueen SE");
		int startRow = 4;
		int startCol = 0;
		int endRow = 5;
		int endCol = 1;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenS() {
		System.out.println("moveQueen S");
		int startRow = 4;
		int startCol = 0;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenSW() {
		System.out.println("moveQueen SW");
		int startRow = 3;
		int startCol = 7;
		int endRow = 4;
		int endCol = 6;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenW() {
		System.out.println("moveQueen W");
		int startRow = 3;
		int startCol = 7;
		int endRow = 3;
		int endCol = 6;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method, of class Chessboard.
	 */
	@Test
	public void testMoveQueenNW() {
		System.out.println("moveQueen NW");
		int startRow = 3;
		int startCol = 7;
		int endRow = 2;
		int endCol = 6;
		boolean expResult = true;
		boolean result = Chessboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKing method, of class Chessboard.
	 */
	@Test
	public void testMoveKing() {
		System.out.println("moveKing");
		int startRow = 7;
		int startCol = 4;
		int endRow = 6;
		int endCol = 4;
		boolean expResult = true;
		boolean result = Chessboard.moveKing(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of canMoveToTargetSquare method, of class Chessboard.
	 */
	@Test
	public void testCanMoveToTargetSquareSuccessEmpty() {
		System.out.println("canMoveToTargetSquare: move to empty square");
		int endRow = 4;
		int endCol = 2;
		boolean expResult = true;
		boolean result = Chessboard.canMoveToTargetSquare(endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of canMoveToTargetSquare method, of class Chessboard.
	 */
	@Test
	public void testCanMoveToTargetSquareSuccessEnemyPiece() {
		System.out.println("canMoveToTargetSquare: capture enemy piece");
		int endRow = 1;
		int endCol = 2;
		boolean expResult = true;
		boolean result = Chessboard.canMoveToTargetSquare(endRow, endCol);
		assertEquals(expResult, result);
	}

	
	/**
	 * Test of canMoveToTargetSquare method, of class Chessboard.
	 */
	@Test
	public void testCanMoveToTargetSquareFail() {
		System.out.println("canMoveToTargetSquare: move on top of own piece");
		int endRow = 6;
		int endCol = 2;
		boolean expResult = false;
		boolean result = Chessboard.canMoveToTargetSquare(endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of updateBlackKingPosition method, of class Chessboard.
	 */
	@Test
	public void testUpdateBlackKingPosition() {
		System.out.println("updateBlackKingPosition");
		Chessboard.moveKingForTest(5, 5, false);
		Chessboard.updateBlackKingPosition();
		char expResult = 'k';
		char result = Chessboard.getSquareContents(5, 5);
		assertEquals(expResult, result);
	}

	/**
	 * Test of updateWhiteKingPosition method, of class Chessboard.
	 */
	@Test
	public void testUpdateWhiteKingPosition() {
		System.out.println("updateWhiteKingPosition");
		Chessboard.moveKingForTest(6, 6, true);
		Chessboard.updateWhiteKingPosition();
		char expResult = 'K';
		char result = Chessboard.getSquareContents(6, 6);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckWithCheckSituation() {
		System.out.println("isItCheck: game situation is check");
		boolean checkedIsWhite = true;
		boolean expResult = true;
		Chessboard.makeTestBoard(true);
		boolean result = Chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheck method, of class Chessboard.
	 */
	@Test
	public void testIsItCheckWithoutCheckSituation() {
		System.out.println("isItCheck: game situation is not check");
		boolean checkedIsWhite = true;
		boolean expResult = false;
		Chessboard.makeTestBoard(false);
		boolean result = Chessboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
}
