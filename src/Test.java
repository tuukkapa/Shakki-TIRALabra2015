
import AI.Evaluate;
import Chessboard.*;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Rook;
import DataStructures.List;
import UI.UserInterface;
import java.util.ArrayList;

/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

public class Test {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
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