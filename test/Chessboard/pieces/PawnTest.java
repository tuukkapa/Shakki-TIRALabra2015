/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
package Chessboard.pieces;

import AI.Move;
import Chessboard.Chessboard;
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
public class PawnTest {
	
	Chessboard chessboard;
	
	public PawnTest() {
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
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesWhiteNoCapture1() {
		System.out.println("getPossibleMoves, no capture 1");
		Pawn instance = (Pawn)chessboard.getPiece(48);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 48, 40);
		Move move2 = new Move(0, 48, 32);
		expResultList.add(move1);
		expResultList.add(move2);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesWhiteNoCapture2() {
		System.out.println("getPossibleMoves, no capture 2");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'R', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Pawn instance = (Pawn)chessboard.getPiece(50);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 50, 42);
		expResultList.add(move1);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture1() {
		System.out.println("getPossibleMoves, black, no capture 1");
		Pawn instance = (Pawn)chessboard.getPiece(12);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 12, 20);
		Move move2 = new Move(0, 12, 28);
		expResultList.add(move1);
		expResultList.add(move2);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesBlackNoCapture2() {
		System.out.println("getPossibleMoves, black, no capture 2");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Pawn instance = (Pawn)chessboard.getPiece(10);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 10, 18);
		expResultList.add(move1);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesWhiteCapture() {
		System.out.println("getPossibleMoves, white,  capture");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', 'r', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Pawn instance = (Pawn)chessboard.getPiece(50);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 50, 42);
		Move move2 = new Move(0, 50, 34);
		Move move3 = new Move(0, 50, 41);
		Move move4 = new Move(0, 50, 43);
		expResultList.add(move1);
		expResultList.add(move2);
		expResultList.add(move3);
		expResultList.add(move4);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}

	/**
	 * Test of getPossibleMoves method, of class Pawn.
	 */
	@Test
	public void testGetPossibleMovesBlackCapture() {
		System.out.println("getPossibleMoves, white,  capture");
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', 'R', ' ', 'R', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Pawn instance = (Pawn)chessboard.getPiece(10);
		ArrayList<Move> expResultList = new ArrayList<>();
		Move move1 = new Move(0, 10, 18);
		Move move2 = new Move(0, 10, 26);
		Move move3 = new Move(0, 10, 17);
		Move move4 = new Move(0, 10, 19);
		expResultList.add(move1);
		expResultList.add(move2);
		expResultList.add(move3);
		expResultList.add(move4);
		ArrayList<Move> resultList = instance.getPossibleMoves(chessboard);
		boolean[] results = new boolean[resultList.size()];
		boolean result = true;
		for (int i = 0; i < results.length; i++) {
			results[i] = false;
		}
		for (int i = 0; i < expResultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(j).equals(expResultList.get(i))) {
					results[i] = true;
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			if (!results[i]) {
				result = false;
			}
		}
		assertTrue(result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteTrue() {
		System.out.println("isMoveValid, true");
		int end = 44;
		Pawn instance = (Pawn)chessboard.getPiece(52);
		boolean expResult = true;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of isMoveValid method, of class Pawn.
	 */
	@Test
	public void testIsMoveValidWhiteFalse() {
		System.out.println("isMoveValid, false");
		int end = 45;
		Pawn instance = (Pawn)chessboard.getPiece(52);
		boolean expResult = false;
		boolean result = instance.isMoveValid(chessboard, end);
		assertEquals(expResult, result);
	}

	/**
	 * Test of clone method, of class Pawn.
	 */
	@Test
	public void testClone() throws Exception {
		System.out.println("clone");
		Pawn instance = (Pawn)chessboard.getPiece(48);
		Pawn clone = (Pawn)instance.clone();
		int expResult = instance.getPosition();
		int result = clone.getPosition();
		assertEquals(expResult, result);
	}
	
}
