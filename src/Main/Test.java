package Main;


import AI.Evaluate;
import AI.Tools;
import Chessboard.*;
import Chessboard.pieces.King;
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
			{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', ' ', ' ', 'p', 'p'},
			{' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'P', 'P', ' ', 'P', 'P', ' ', ' ', 'P'},
			{'R', 'N', 'B', 'K', ' ', 'q', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		King king = (King)chessboard.getSquareContents(59);
		List<Move> moves = king.getPossibleMoves(chessboard);
		int value = ChessboardHandler.isItCheckMate(chessboard);
	}

}