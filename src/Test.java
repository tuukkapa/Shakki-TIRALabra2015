
import AI.Evaluate;
import Chessboard.Move;
import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Rook;
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
		Chessboard chessboard = new Chessboard(newboard);
		Chessboard clone = chessboard.cloneBoardAndPieces(chessboard);
		System.out.println("Moi");
	}

}