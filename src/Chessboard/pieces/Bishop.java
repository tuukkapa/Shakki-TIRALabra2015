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
 * Class for Bishop. Creates Bishop-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Bishop extends Piece implements Cloneable {
	
	public Bishop(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'B' : 'b';
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		return this.createDiagonalMoves(chessboard);
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
		} else {
			return false;
		}
		
		return moveOk && this.endSquareContainsEnemyOrEmpty(chessboard, end) && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Bishop newBishop = (Bishop) super.clone();
		return newBishop;
	}

}
