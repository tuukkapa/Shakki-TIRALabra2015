/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard;

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