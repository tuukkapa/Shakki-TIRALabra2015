package Main;


import AI.Evaluate;
import AI.Tools;
import Chessboard.*;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Rook;
import DataStructures.List;
import UI.UserInterface;
import java.util.ArrayList;
import java.util.Random;

/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

public class Test {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Chessboard chessboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'R', 'Q', ' ', ' ', ' ', 'r'},
			{'p', 'p', ' ', ' ', ' ', ' ', ' ', 'p'},
			{' ', ' ', ' ', ' ', 'k', ' ', ' ', 'R'},
			{' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'q', 'r', ' ', ' '},
			{'B', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		//int rand = Tools.randInt();
		Random random = new Random();
		int ups = chessboard.getOfficersAmount(true);
		int[] testirandomit = new int[10];
		for (int i = 0; i < 10; i++) {
			testirandomit[i] = random.nextInt(5);
		}

		//Chessboard chessboard.setBoard(newboard);
		//boolean value = ChessboardHandler.isItCheck(chessboard, false);
		/*List<Piece> lista = new List<>();
		lista.add(chessboard.getSquareContents(0));
		lista.add(chessboard.getSquareContents(1));
		lista.add(chessboard.getSquareContents(2));
		int size = lista.size();
		lista.remove(chessboard.getSquareContents(1));
		int size2 = lista.size();
		Piece piece = lista.get(0);*/
	}

}