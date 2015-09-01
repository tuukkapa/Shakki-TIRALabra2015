package Main;


import AI.Evaluate;
import AI.Tools;
import Chessboard.*;
import Chessboard.pieces.King;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Rook;
import Chessboard.pieces.SuperPiece;
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
			{'r', 'B', 'p', 'q', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', 'b', ' ', ' ', ' '},
			{' ', ' ', 'R', ' ', ' ', 'Q', ' ', ' '},
			{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
		};
		chessboard.setBoard(newboard);
		SuperPiece superPiece = new SuperPiece(true, 37);
		List<Move> moves = superPiece.getPossibleMoves(chessboard, false);
		int amountOfMoves = moves.size();
		int value = Evaluate.evaluate(chessboard, false, 0);
	}

}