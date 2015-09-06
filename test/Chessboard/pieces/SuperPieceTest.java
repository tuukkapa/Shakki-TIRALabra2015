/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard.pieces;

import Chessboard.Chessboard;
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
public class SuperPieceTest {
	
	public SuperPieceTest() {
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
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusWhiteProtected() {
		System.out.println("SuperPiece, getProtectionStatus, white protected");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = true;
		SuperPiece instance = new SuperPiece(true, 44);
		boolean expResult = true;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusWhiteUnprotected() {
		System.out.println("SuperPiece, getProtectionStatus, white unprotected");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = true;
		SuperPiece instance = new SuperPiece(true, 36);
		boolean expResult = false;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusWhiteAttacked() {
		System.out.println("SuperPiece, getProtectionStatus, white attacked");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'r', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = false;
		SuperPiece instance = new SuperPiece(true, 36);
		boolean expResult = true;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusBlackProtected() {
		System.out.println("SuperPiece, getProtectionStatus, black protected");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = true;
		SuperPiece instance = new SuperPiece(false, 20);
		boolean expResult = true;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusBlackUnprotected() {
		System.out.println("SuperPiece, getProtectionStatus, black unprotected");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = true;
		SuperPiece instance = new SuperPiece(false, 28);
		boolean expResult = false;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of getProtectionStatus method, of class SuperPiece.
	 */
	@Test
	public void testGetProtectionStatusBlackAttacked() {
		System.out.println("SuperPiece, getProtectionStatus, black unprotected");
		Chessboard chessboard = new Chessboard();
		char[][] newBoard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', ' ', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newBoard);
		boolean own = false;
		SuperPiece instance = new SuperPiece(false, 28);
		boolean expResult = true;
		boolean result = instance.getProtectionStatus(chessboard, own);
		assertEquals(expResult, result);
	}
	
}
