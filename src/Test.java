
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
		Piece[][] temppi = chessboard.cloneBoard();
		Piece[][] originaali = chessboard.getBoard();
		Chessboard clone = new Chessboard();
		clone.setBoard(temppi);
		Piece[][] klooni = clone.getBoard();
		Move move1 = new Move(48, 40);
		Move move2 = new Move(13, 29);
		chessboard.movePiece(move1);
		chessboard.movePiece(move2);
		System.out.println("Taulut");
		System.out.println("Orig: " + originaali);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (originaali[row][col] != null) {
					System.out.println("Orig: " + originaali[row][col] + " @r" + 
						originaali[row][col].getPosition()/8 + " s" + originaali[row][col].getPosition()%8);
				}
				
			}
		}
		System.out.println("Clone: " + klooni);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (klooni[row][col] != null) {
					System.out.println("Clone: " + klooni[row][col] + " @r" + 
						klooni[row][col].getPosition()/8 + " s" + klooni[row][col].getPosition()%8);
				}
				
			}
		}
		
		System.out.println("Orig");
		UserInterface.draw(chessboard.getBoardAsCharArray());
		System.out.println("Clone");
		UserInterface.draw(clone.getBoardAsCharArray());
	}

}
