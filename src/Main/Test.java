package Main;


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
		Chessboard chessboard = new Chessboard();
		char[][] newboard = {
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', ' ', 'p', 'p', ' ', 'B', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'p', ' ', ' ', 'p', 'p', ' ', ' '},
			{' ', ' ', ' ', 'r', 'P', ' ', ' ', 'q'},
			{' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
			{'P', 'P', 'P', 'P', ' ', 'P', ' ', 'P'},
			{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		Piece piece = chessboard.getSquareContents(4);
		ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
		System.out.println("Moi");
		
	}

}