/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

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
	public ArrayList getPossibleMoves(Chessboard chessboard) {
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
			for (int i = 1; i < Math.abs(endRow-startRow); i++) {
				if (endRow>startRow && endCol>startCol) {
					if (chessboard.getSquareContents(startRow+i, startCol+i) != ' ') {
						moveOk = false;
						break;
					}
				} else if (endRow>startRow && endCol<startCol) {
					if (chessboard.getSquareContents(startRow+i, startCol-i) != ' ') {
						moveOk = false;
						break;
					}
				} else if (endRow<startRow && endCol>startCol) {
					if (chessboard.getSquareContents(startRow-i, startCol+i) != ' ') {
						moveOk = false;
						break;
					}
				} else {
					if (chessboard.getSquareContents(startRow-i, startCol-i) != ' ') {
						moveOk = false;
						break;
					}
				}
			}
		}
		return moveOk && !chessboard.wouldItBeCheck(this, end) && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Bishop newBishop = (Bishop) super.clone();
		return newBishop;
	}

}
