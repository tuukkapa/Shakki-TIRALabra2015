
import AI.Evaluate;
import Chessboard.Move;
import Chessboard.Chessboard;
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
			{'r', 'n', 'R', ' ', 'k', ' ', ' ', 'r'},
			{'p', 'p', ' ', ' ', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', 'R', 'Q', 'R', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
	//	Chessboard chessboard = new Chessboard(newboard);
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