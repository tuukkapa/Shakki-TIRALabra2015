
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
			{'r', 'n', 'R', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'B', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', 'N', 'R'}
		};
		Chessboard chessboard = new Chessboard(newboard);
		Piece bishop = chessboard.getSquareContents(3, 1);
		ArrayList<Move> moves = bishop.getPossibleMoves(chessboard);
		System.out.println("Moi");
	}

}