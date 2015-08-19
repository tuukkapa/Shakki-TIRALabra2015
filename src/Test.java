
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
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Chessboard chessboard = new Chessboard();
		Move move1 = new Move(4, 3);
		boolean siirtoOnnistui = chessboard.movePiece(move1);
		Move move2 = new Move(4, 5);
		Chessboard clone = chessboard.cloneBoardAndPieces(chessboard);
		boolean klooninSiirtoonnistui = chessboard.movePiece(move2);
		System.out.println("moi");
	}

}