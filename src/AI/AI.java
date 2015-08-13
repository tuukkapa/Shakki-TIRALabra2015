package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuukka
 */
public class AI {
	
	public Movement minimax(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		int bestValue, value;
		Movement bestMove = null;
		if (depth == 0 || chessboard.isItCheckMate()) {
			return new Movement(chessboard.getValue(), 0, 0);
		}
		bestValue = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		Piece[] pieces = chessboard.getPieces();
		for (int i = 0; i < pieces.length; i++) {
			Movement[] movements = pieces[i].getPossibleMovements(chessboard);
			for (int j = 0; j < movements.length; j++) {
				Chessboard clone = this.cloneBoardAndPieces(chessboard);
				Piece clonePiece = clone.getPieces()[i]; // clone.getPieces()[i] shoud equal to pieces[i] (??)
				clonePiece.move(clone, movements[j].getEnd());
				value = minimax(clone, depth - 1, !maximizingPlayer).getScore();
				if (maximizingPlayer) {
					if (bestValue < value) {
						bestValue = value;
						bestMove = new Movement(clone.getValue(), pieces[i].getPosition(), movements[j].getEnd());
					}
				} else {
					if (bestValue > value) {
						bestValue = value;
						bestMove = new Movement(clone.getValue(), pieces[i].getPosition(), movements[j].getEnd());
					}
				}
				//bestValue = maximizingPlayer ? Math.max(bestValue, value) : Math.min(bestValue, value);
			}
		}
		return bestMove;
	}
	
	private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Chessboard cloneBoard = new Chessboard();
		cloneBoard.setBoard(chessboard.cloneBoard());
		cloneBoard.setPieces(chessboard.clonePieces());
		return cloneBoard;
	}
	
}
