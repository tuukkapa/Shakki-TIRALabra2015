/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Move;
import Chessboard.Chessboard;
import java.util.ArrayList;

/**
 * Class for Queen, creates Queen-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Queen extends Piece implements Cloneable {
	
	public Queen(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'Q' : 'q';
	}

	/**
	 *
	 * @param chessboard
	 * @return
	 */
	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		ArrayList<Move> moves = this.createStraightMoves(chessboard);
		ArrayList<Move> diagonalMoves = this.createDiagonalMoves(chessboard);
		moves.removeAll(diagonalMoves); // this should be redundant?
		moves.addAll(diagonalMoves);
		return moves;
	}

	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		
		boolean moveOk = true;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			moveOk = this.checkDiagonalRoutes(chessboard, end);
		} else if (startRow == endRow || startCol == endCol) {
			moveOk = this.checkStraightRoutes(chessboard, end);
		} else {
			return false;
		}
		
		return moveOk && this.endSquareContainsEnemyOrEmpty(chessboard, end) && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Queen newQueen = (Queen) super.clone();
		return newQueen;
	}

}
