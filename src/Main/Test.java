package Main;


import AI.Evaluate;
import AI.Tools;
import Chessboard.*;
import Chessboard.pieces.Pawn;
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
			{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
			{' ', ' ', ' ', ' ', 'k', ' ', ' ', ' '},
			{'P', 'p', ' ', ' ', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', 'P', 'p', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'P', 'P', ' ', ' ', ' ', 'P', 'P'},
			{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		chessboard.setBoard(newboard);
		UserInterface.drawBoard(chessboard.getBoardAsCharArray(), null);
		Pawn pawn = (Pawn)chessboard.getSquareContents(35);
		pawn.setEnPassant(true);
		Move move = new Move(36, 43);
		ChessboardHandler.movePiece(chessboard, move);
		UserInterface.drawBoard(chessboard.getBoardAsCharArray(), null);
	}

}