/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import AI.Movement;
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
		ArrayList<Movement> movements = new ArrayList<>();
		int rookRow = position / 8;
		int rookCol = position % 8;
		for (int i = 0; i < 8; i++) {
			// check Rook's row
			if (rookCol != i && this.isMoveValid(chessboard, rookRow * 8 + i)) {
				movements.add(new Movement(0, position, rookRow * 8 + i));
			}
			// check Rook's col
			if (rookRow != i && this.isMoveValid(chessboard, i * 8 + rookCol)) {
				movements.add(new Movement(0, position, i * 8 + rookCol));
			}
		}
		return movements;
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

		// Check route vertically
		if (startCol == endCol) {
			for (int i = 0; i < Math.abs(endRow - startRow); i++) {
				// is the route clear and the checked square isn't where the Rook is
				if (Math.min(startRow, endRow) + i != startRow && 
						chessboard.getSquareContents(Math.min(startRow, endRow) + i, startCol) != ' ') {
					movementOk = false;
				}
			}	
		// Check route horizontally
		} else if (startRow == endRow) {
			for (int i = 0; i < Math.abs(endCol - startCol); i++) {
				// is the route clear and the checked square isn't where the Rook is
				if (Math.min(startCol, endCol) + i != startCol &&
						chessboard.getSquareContents(startRow, Math.min(startCol, endCol) + i) != ' ') {
					movementOk = false;
				}
			}
		} else if (startRow != endRow && startCol != endCol) {
			return false;
		}

		return movementOk && !chessboard.wouldItBeCheck(this, end) && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rook newRook = (Rook) super.clone();
		return newRook;
	}

}
