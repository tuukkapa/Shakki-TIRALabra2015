/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import User.UserMovement;
import Chessboard.Chessboard;
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
public class UserMovementTest {
	
	public UserMovementTest() {
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
		//testboard.makeTestBoard(false);
		System.out.println("movePiece correct input");
		String command = "c2c3";
		boolean expResult = true;
		boolean result = UserMovement.movePiece(command, testboard);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of movePiece method with incorrect input string, of class Chessboard. 
	*/
	@Test
	public void testMovePieceWrongInputString() {
		Chessboard testboard = new Chessboard();
		//testboard.makeTestBoard(false);
		System.out.println("movePiece wrong input string");
		String command = "hello";
		boolean expResult = false;
		boolean result = UserMovement.movePiece(command, testboard);
		assertEquals(expResult, result);
	}
	
	
	/**
	 *  Test of movePiece method with incorrect input coordinates, of class Chessboard. 
	*/
	@Test
	public void testMovePieceWrongInputCoordinates() {
		Chessboard testboard = new Chessboard();
		//testboard.makeTestBoard(false);
		System.out.println("movePiece wrong input coordinates");
		String command = "c9u9";
		boolean expResult = false;
		boolean result = UserMovement.movePiece(command, testboard);
		assertEquals(expResult, result);
	}
	
}
