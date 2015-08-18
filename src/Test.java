
import AI.Move;
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
	
	public static void main(String[] args) {
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', ' ', 'r'},
			{'p', 'p', ' ', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Chessboard chessboard = new Chessboard(newboard);
		//Move move = new Move(3, 17);
		Piece piece = chessboard.getSquareContents(3);
		ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
		boolean value = piece.isMoveValid(chessboard, 17);
		System.out.println("moi");
	}

}
