/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Chessboard;
import java.util.ArrayList;

public class Rook extends Piece implements Cloneable {
	
	public Rook(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'R' : 'r';
	}
	
	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		return this.createStraightMovements(chessboard);
	}
	
	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		boolean movementOk = true;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;

		
		if (startCol != endCol && startRow != endRow) {
			return false;
		}
		
		// check route
		int movement = Math.abs(startRow - endRow) == 0 ? Math.abs(startCol - endCol) : Math.abs(startRow - endRow);
		for (int i = 1; i < movement; i++) {
			if (startRow == endRow) {
				if (chessboard.getSquareContents(startRow, Math.min(startCol, endCol) + i) != ' ') {
					movementOk = false;
					break;
				}
			} else if (startCol == endCol) {
				if (chessboard.getSquareContents(Math.min(startRow, endRow) + i, startCol) != ' ') {
					movementOk = false;
					break;
				}
			} else {
				movementOk = false;
				break;
			}
		}

		return movementOk && !chessboard.wouldItBeCheck(this, end) && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rook newRook = (Rook) super.clone();
		return newRook;
	}

}
