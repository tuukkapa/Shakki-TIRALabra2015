/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import AI.Move;
import Chessboard.Chessboard;
import java.util.ArrayList;

/**
 * Class for Rook, creates Rook-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Rook extends Piece implements Cloneable {
	
	public Rook(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'R' : 'r';
	}
	
	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		return this.createStraightMoves(chessboard);
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
		
		// check route
		int movement = startRow - endRow == 0 ? Math.abs(startCol - endCol) : Math.abs(startRow - endRow);
		for (int i = 1; i <= movement; i++) {
			if (startRow == endRow) {
				if (startCol < endCol && !this.endSquareContainsEnemyOrEmpty(chessboard, startRow * 8 + startCol + i)) {
					moveOk = false;
					break;
				}
				if (startCol > endCol && !this.endSquareContainsEnemyOrEmpty(chessboard, startRow * 8 + startCol - i)) {
					moveOk = false;
					break;
				}
			} else if (startCol == endCol) {
				if (startRow < endRow && !this.endSquareContainsEnemyOrEmpty(chessboard, (startRow + i) * 8 + startCol)) {
					moveOk = false;
					break;
				}
				if (startRow > endRow && !this.endSquareContainsEnemyOrEmpty(chessboard, (startRow - i) * 8 + startCol)) {
					moveOk = false;
					break;
				}
			} else {
				moveOk = false;
				break;
			}
		}

		return moveOk && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rook newRook = (Rook) super.clone();
		return newRook;
	}

}
