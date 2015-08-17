
import AI.Move;
import Chessboard.Chessboard;
import UI.UserInterface;

/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

public class Test {
	
	public static void main(String[] args) {
		Chessboard chessboard = new Chessboard();
		char[][] newboard = {
			{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard.setBoard(newboard);
		Move move = new Move(0, 6, 21);
		System.out.println("Lauta alussa");
		UserInterface.draw(chessboard.getBoard());
		boolean move1 = chessboard.makeMove(move);
		System.out.println("Siirto tehty");
		UserInterface.draw(chessboard.getBoard());
		chessboard.undoMove(chessboard, move);
		System.out.println("Siirto peruttu");
		UserInterface.draw(chessboard.getBoard());
	}

}
