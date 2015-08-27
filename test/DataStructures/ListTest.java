/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Chessboard.Move;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Queen;
import Chessboard.pieces.Rook;
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
public class ListTest {
	
	public ListTest() {
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
	 * Test of add method, of class List.
	 */
	@Test
	public void testAdd() {
		System.out.println("List, add");
		Pawn piece = new Pawn(true, 0);
		List<Piece> instance = new List<>();
		instance.add(piece);
		Piece listResult = instance.get(0);
		assertTrue(listResult instanceof Pawn);
	}

	/**
	 * Test of get method, of class List.
	 */
	@Test
	public void testGet() {
		System.out.println("List, get");
		Pawn piece = new Pawn(true, 0);
		List<Piece> instance = new List<>();
		instance.add(piece);
		Piece listResult = instance.get(0);
		assertTrue(listResult instanceof Pawn && listResult.amIWhite());
	}

	/**
	 * Test of remove method, of class List.
	 */
	@Test
	public void testRemove() {
		System.out.println("remove");
		Pawn piece = new Pawn(true, 0);
		List<Piece> instance = new List<>();
		instance.add(piece);
		instance.remove(piece);
		assertTrue(instance.size() == 0);
		
	}

	/**
	 * Test of size method, of class List.
	 */
	@Test
	public void testSize() {
		System.out.println("size");
		Pawn piece = new Pawn(true, 0);
		Rook piece2 = new Rook(true, 2);
		Queen piece3 = new Queen(true, 2);
		List<Piece> instance = new List<>();
		instance.add(piece);
		instance.add(piece2);
		instance.add(piece3);
		instance.remove(piece2);
		int amount = 0;
		for (int i = 0; i < instance.size(); i++) {
			if (instance.get(i).equals(piece2)) {
				amount++;
			}
		}
		assertTrue(amount == 0);

	}

	/**
	 * Test of addAll method, of class List.
	 */
	@Test
	public void testAddAll() {
		System.out.println("List, addAll");
		Move move1 = new Move(0, 1);
		Move move2 = new Move(2, 25);
		Move move3 = new Move(43, 34);
		List<Move> instance = new List<>();
		instance.add(move1);
		instance.add(move2);
		instance.add(move3);
		List<Move> anotherList = new List<>();
		Move newMove1 = new Move(6, 11);
		Move newMove2 = new Move(12, 13);
		Move newMove3 = new Move(63, 56);
		anotherList.add(newMove1);
		anotherList.add(newMove2);
		anotherList.add(newMove3);
		instance.addAll(anotherList);
		boolean move1Ok = false, move2Ok = false, move3Ok = false,
				newMove1Ok = false, newMove2Ok = false, newMove3Ok = false;
		for (int i = 0; i < instance.size(); i++) {
			if (instance.get(i).equals(move1)) {
				move1Ok = true;
			}
			if (instance.get(i).equals(move2)) {
				move2Ok = true;
			}
			if (instance.get(i).equals(move3)) {
				move3Ok = true;
			}
			if (instance.get(i).equals(newMove1)) {
				newMove1Ok = true;
			}
			if (instance.get(i).equals(newMove2)) {
				newMove2Ok = true;
			}
			if (instance.get(i).equals(newMove3)) {
				newMove3Ok = true;
			}
		}
		assertTrue(move1Ok && move2Ok && move3Ok && newMove1Ok && newMove2Ok && newMove3Ok && instance.size() == 6);
	}
	
}
