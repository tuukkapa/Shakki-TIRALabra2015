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
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of movePiece method with correct input, of class Chessboard.
	 */
	@Test
	public void testMovePieceCorrectInput() {
		Chessboard testboard = new Chessboard();
		testboard.makeTestBoard(false);
		System.out.println("movePiece correct input");
		String command = "c2c3";
		boolean expResult = true;
		boolean result = UserMovement.movePiece(command);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method with incorrect input string, of class Chessboard. 
	*/
	@Test
	public void testMovePieceWrongInputString() {
		Chessboard testboard = new Chessboard();
		testboard.makeTestBoard(false);
		System.out.println("movePiece wrong input string");
		String command = "hello";
		boolean expResult = false;
		boolean result = UserMovement.movePiece(command);
		assertEquals(expResult, result);
	}
	
	
	/**
	 *  Test of movePiece method with incorrect input coordinates, of class Chessboard. 
	*/
	@Test
	public void testMovePieceWrongInputCoordinates() {
		Chessboard testboard = new Chessboard();
		testboard.makeTestBoard(false);
		System.out.println("movePiece wrong input coordinates");
		String command = "c9u9";
		boolean expResult = false;
		boolean result = UserMovement.movePiece(command);
		assertEquals(expResult, result);
	}

	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move piece two squares forward.
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
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move piece one square forward.
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
	 * Move rook vertically.
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
	 * Move rook horizontally.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-west-west.
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
	 * Move knight north-north-west.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-north-east.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-east-east.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-east-east.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-south-east.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-south-west.
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
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-west-west.
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
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop north-west.
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
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop north-east.
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
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop south-east.
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
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop south-west.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north-east.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen east.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south-east.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south-west.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen west.
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
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north-west.
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
	 * Test of moveKing method with correct input, of class Chessboard.
	 * Move king north.
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
	 * Tests when the target square is empty.
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
	 * Tests when the target square contains an enemy piece.
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
	 * Tests when the target square contains player's own piece.
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
		Chessboard.moveKingForTest(false, 5, 5);
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
		Chessboard.moveKingForTest(true, 6, 6);
		Chessboard.updateWhiteKingPosition();
		char expResult = 'K';
		char result = Chessboard.getSquareContents(6, 6);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isItCheck method, of class Chessboard.
	 * Tests when the game situation is check against white.
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
	 * Tests when the game situation isn't check against white.
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
