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
	 * Test of isCommandValid method, of class Chessboard.
	 */
	@Test
	public void testIsCommandValidWrongInput1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		int startRow = -1;
		int startCol = 2;
		int endRow = 5;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isCommandValid method, of class Chessboard.
	 */
	@Test
	public void testIsCommandValidWrongInput2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		int startRow = 6;
		int startCol = -1;
		int endRow = 5;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isCommandValid method, of class Chessboard.
	 */
	@Test
	public void testIsCommandValidWrongInput3() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		int startRow = 6;
		int startCol = 2;
		int endRow = -1;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isCommandValid method, of class Chessboard.
	 */
	@Test
	public void testIsCommandValidWrongInput4() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		int startRow = 6;
		int startCol = 2;
		int endRow = 5;
		int endCol = -1;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move white piece two squares forward.
	 */
	@Test
	public void testMovePawnCorrectInputWhite1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: move white 1 square");
		int startRow = 6;
		int startCol = 2;
		int endRow = 5;
		int endCol = 2;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move white piece one square forward.
	 */
	@Test
	public void testMovePawnCorrectInputWhite2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: move white 2 squares from starting row");
		int startRow = 6;
		int startCol = 2;
		int endRow = 4;
		int endCol = 2;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Capture piece to the left.
	 */
	@Test
	public void testMovePawnCorrectInputWhiteCapture1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: capture black piece with white pawn to the left");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 1;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Capture piece to the right.
	 */
	@Test
	public void testMovePawnCorrectInputWhiteCapture2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: capture black piece with white pawn to the right");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece three squares forward.
	 */
	@Test
	public void testMovePawnWrongInputWhite1() {
		Chessboard testboard = new Chessboard();
		System.out.println("movePawn: try to move white 3 squares from starting row");
		int startRow = 6;
		int startCol = 2;
		int endRow = 3;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece straight backwards.
	 */
	@Test
	public void testMovePawnWrongInputWhite2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white straight backwards");
		int startRow = 5;
		int startCol = 2;
		int endRow = 6;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece left backwards.
	 */
	@Test
	public void testMovePawnWrongInputWhite3() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', 'r', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white left backwards");
		int startRow = 5;
		int startCol = 2;
		int endRow = 6;
		int endCol = 1;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece right backwards.
	 */
	@Test
	public void testMovePawnWrongInputWhite4() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white right backwards");
		int startRow = 5;
		int startCol = 2;
		int endRow = 6;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to capture black piece straight ahead.
	 */
	@Test
	public void testMovePawnWrongInputWhite5() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture enemy piece straight ahead");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move piece 2 rows ahead without being at starting row.
	 */
	@Test
	public void testMovePawnWrongInputWhite6() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move piece 2 rows ahead without being at starting row");
		int startRow = 5;
		int startCol = 2;
		int endRow = 3;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}


	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move white piece one square forward.
	 */
	@Test
	public void testMovePawnWrongInputWhiteCapture1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture white piece with white pawn to the left");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 1;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move white piece one square forward.
	 */
	@Test
	public void testMovePawnWrongInputWhiteCapture2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture white piece with white pawn to the right");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with wrong input, of class Chessboard.
	 * Try to move pawn 1 rows and 2 columns.
	 */
	@Test
	public void testMovePawnWrongInputWhiteCapture3() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'r', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move pawn more than 1 columns");
		int startRow = 5;
		int startCol = 2;
		int endRow = 4;
		int endCol = 0;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with wrong input, of class Chessboard.
	 * Try to move pawn 2 rows and 1 column.
	 */
	@Test
	public void testMovePawnWrongInputWhiteCapture4() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', 'r', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move pawn 2 rows and 1 column");
		int startRow = 5;
		int startCol = 2;
		int endRow = 3;
		int endCol = 1;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move piece two squares forward.
	 */
	@Test
	public void testMovePawnCorrectInputBlack1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: move black 1 square");
		int startRow = 1;
		int startCol = 3;
		int endRow = 2;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move piece one square forward.
	 */
	@Test
	public void testMovePawnCorrectInputBlack2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: move black 2 squares from starting row");
		int startRow = 1;
		int startCol = 3;
		int endRow = 3;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Capture piece to the left.
	 */
	@Test
	public void testMovePawnCorrectInputBlackCapture1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: capture black piece with white pawn to the left");
		int startRow = 1;
		int startCol = 3;
		int endRow = 2;
		int endCol = 2;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Capture piece to the right.
	 */
	@Test
	public void testMovePawnCorrectInputBlackCapture2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: capture black piece with white pawn to the right");
		int startRow = 1;
		int startCol = 3;
		int endRow = 2;
		int endCol = 4;
		boolean expResult = true;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move piece three squares forward.
	 */
	@Test
	public void testMovePawnWrongInputBlack1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white 3 squares from starting row");
		int startRow = 1;
		int startCol = 3;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move piece straight backwards.
	 */
	@Test
	public void testMovePawnWrongInputBlack2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', ' ', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', 'p', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white straight backwards");
		int startRow = 2;
		int startCol = 3;
		int endRow = 1;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece left backwards.
	 */
	@Test
	public void testMovePawnWrongInputBlack3() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', ' ', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', 'p', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', 'r', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white left backwards");
		int startRow = 3;
		int startCol = 3;
		int endRow = 2;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move white piece right backwards.
	 */
	@Test
	public void testMovePawnWrongInputBlack4() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', ' ', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', 'p', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move white right backwards");
		int startRow = 3;
		int startCol = 3;
		int endRow = 2;
		int endCol = 4;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to capture piece straight ahead.
	 */
	@Test
	public void testMovePawnWrongInputBlack5() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', 'r', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture black piece straight ahead");
		int startRow = 2;
		int startCol = 3;
		int endRow = 3;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to move piece 2 rows ahead without being at starting row.
	 */
	@Test
	public void testMovePawnWrongInputBlack6() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', ' ', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', 'p', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', 'r', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to move piece 2 rows ahead without being at starting row");
		int startRow = 2;
		int startCol = 3;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Try to capture own piece with own piece to the left.
	 */
	@Test
	public void testMovePawnWrongInputBlackCapture1() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'b', ' ', 'n', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', 'R', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture white piece with white pawn to the left");
		int startRow = 1;
		int startCol = 3;
		int endRow = 2;
		int endCol = 2;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePawn method with correct input, of class Chessboard.
	 * Move white piece one square forward.
	 */
	@Test
	public void testMovePawnWrongInputBlackCapture2() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'b', ' ', 'n', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("movePawn: try to capture white piece with white pawn to the right");
		int startRow = 1;
		int startCol = 2;
		int endRow = 2;
		int endCol = 4;
		boolean expResult = false;
		boolean result = testboard.movePawn(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	////////////////////////////////
	// TESTS MADE SO FAR
	// Branches still missing above
	////////////////////////////////
	
	/**
	 * Test of moveRook method, of class Chessboard.
	 * Move rook vertically.
	 */
	@Test
	public void testMoveRookVertically() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveRook vertically");
		int startRow = 7;
		int startCol = 0;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = testboard.moveRook(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveRook method, of class Chessboard.
	 * Move rook horizontally.
	 */
	@Test
	public void testMoveRookHorizontally() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveRook horizontally");
		int startRow = 5;
		int startCol = 7;
		int endRow = 5;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.moveRook(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-west-west.
	 */
	@Test
	public void testMoveKnightCorrectNWW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight NWW");
		int startRow = 7;
		int startCol = 1;
		int endRow = 6;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method, of class Chessboard.
	 * Move knight north-north-west.
	 */
	@Test
	public void testMoveKnightNNW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight NNW");
		int startRow = 7;
		int startCol = 1;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-north-east.
	 */
	@Test
	public void testMoveKnightNNE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight NNE");
		int startRow = 7;
		int startCol = 6;
		int endRow = 5;
		int endCol = 5;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight north-east-east.
	 */
	@Test
	public void testMoveKnightNEE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight NEE");
		int startRow = 7;
		int startCol = 6;
		int endRow = 6;
		int endCol = 4;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-east-east.
	 */
	@Test
	public void testMoveKnightSEE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight SEE");
		int startRow = 2;
		int startCol = 4;
		int endRow = 3;
		int endCol = 6;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-south-east.
	 */
	@Test
	public void testMoveKnightSSE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight SSE");
		int startRow = 2;
		int startCol = 4;
		int endRow = 4;
		int endCol = 5;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-south-west.
	 */
	@Test
	public void testMoveKnightSSW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight SSW");
		int startRow = 2;
		int startCol = 4;
		int endRow = 4;
		int endCol = 3;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveKnight method with correct input, of class Chessboard.
	 * Move knight south-west-west.
	 */
	@Test
	public void testMoveKnightSWW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKnight SWW");
		int startRow = 2;
		int startCol = 4;
		int endRow = 3;
		int endCol = 2;
		boolean expResult = true;
		boolean result = testboard.moveKnight(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop north-west.
	 */
	@Test
	public void testMoveBishopNW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveBishop NW");
		int startRow = 7;
		int startCol = 2;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = testboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop north-east.
	 */
	@Test
	public void testMoveBishopNE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveBishop NE");
		int startRow = 7;
		int startCol = 2;
		int endRow = 5;
		int endCol = 4;
		boolean expResult = true;
		boolean result = testboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop south-east.
	 */
	@Test
	public void testMoveBishopSE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveBishop SE");
		int startRow = 2;
		int startCol = 2;
		int endRow = 4;
		int endCol = 4;
		boolean expResult = true;
		boolean result = testboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveBishop method with correct input, of class Chessboard.
	 * Move bishop south-west.
	 */
	@Test
	public void testMoveBishopSW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveBishop SW");
		int startRow = 2;
		int startCol = 2;
		int endRow = 3;
		int endCol = 1;
		boolean expResult = true;
		boolean result = testboard.moveBishop(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north.
	 */
	@Test
	public void testMoveQueenN() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen N");
		int startRow = 4;
		int startCol = 0;
		int endRow = 3;
		int endCol = 0;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}

	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north-east.
	 */
	@Test
	public void testMoveQueenNE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen NE");
		int startRow = 4;
		int startCol = 0;
		int endRow = 3;
		int endCol = 1;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen east.
	 */
	@Test
	public void testMoveQueenE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen E");
		int startRow = 4;
		int startCol = 0;
		int endRow = 4;
		int endCol = 1;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south-east.
	 */
	@Test
	public void testMoveQueenSE() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen SE");
		int startRow = 4;
		int startCol = 0;
		int endRow = 5;
		int endCol = 1;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south.
	 */
	@Test
	public void testMoveQueenS() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen S");
		int startRow = 4;
		int startCol = 0;
		int endRow = 5;
		int endCol = 0;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen south-west.
	 */
	@Test
	public void testMoveQueenSW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen SW");
		int startRow = 3;
		int startCol = 7;
		int endRow = 4;
		int endCol = 6;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen west.
	 */
	@Test
	public void testMoveQueenW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen W");
		int startRow = 3;
		int startCol = 7;
		int endRow = 3;
		int endCol = 6;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveQueen method with correct input, of class Chessboard.
	 * Move queen north-west.
	 */
	@Test
	public void testMoveQueenNW() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveQueen NW");
		int startRow = 3;
		int startCol = 7;
		int endRow = 2;
		int endCol = 6;
		boolean expResult = true;
		boolean result = testboard.moveQueen(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of moveKing method with correct input, of class Chessboard.
	 * Move king north.
	 */
	@Test
	public void testMoveKing() {
		Chessboard testboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		testboard.setBoard(newboard);
		System.out.println("moveKing");
		int startRow = 7;
		int startCol = 4;
		int endRow = 6;
		int endCol = 4;
		boolean expResult = true;
		boolean result = testboard.moveKing(startRow, startCol, endRow, endCol);
		assertEquals(expResult, result);
	}
		
	/**
	 * Test of updateBlackKingPosition method, of class Chessboard.
	 */
	@Test
	public void testUpdateBlackKingPosition() {
		Chessboard testboard = new Chessboard();
		System.out.println("updateBlackKingPosition");
		testboard.moveKingForTest(false, 5, 5);
		testboard.updateKingPosition(false);
		char expResult = 'k';
		char result = testboard.getSquareContents(5, 5);
		assertEquals(expResult, result);
	}

	/**
	 * Test of updateWhiteKingPosition method, of class Chessboard.
	 */
	@Test
	public void testUpdateWhiteKingPosition() {
		Chessboard testboard = new Chessboard();
		System.out.println("updateWhiteKingPosition");
		testboard.moveKingForTest(true, 6, 6);
		testboard.updateKingPosition(true);
		char expResult = 'K';
		char result = testboard.getSquareContents(6, 6);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isItCheck method, of class Chessboard.
	 * Tests when the game situation is check against white.
	 */
	@Test
	public void testIsItCheckWithCheckSituation() {
		Chessboard testboard = new Chessboard();
		System.out.println("isItCheck: game situation is check");
		boolean checkedIsWhite = true;
		boolean expResult = true;
		testboard.makeTestBoard(true);
		boolean result = testboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isItCheck method, of class Chessboard.
	 * Tests when the game situation isn't check against white.
	 */
	@Test
	public void testIsItCheckWithoutCheckSituation() {
		Chessboard testboard = new Chessboard();
		System.out.println("isItCheck: game situation is not check");
		boolean checkedIsWhite = true;
		boolean expResult = false;
		testboard.makeTestBoard(false);
		boolean result = testboard.isItCheck(checkedIsWhite);
		assertEquals(expResult, result);
	}
	
}
