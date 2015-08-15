/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;
import java.util.ArrayList;

public class Knight extends Piece implements Cloneable {
	
	public Knight(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'N' : 'n';
	}

	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		ArrayList<Movement> movements = new ArrayList<>();
		int row = position / 8;
		int col = position % 8;
		if (row - 1 >= 0 && col - 2 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 1) * 8 + (col - 2))) {
			movements.add(new Movement(0, position, (row - 1) * 8 + (col - 2)));
		}
		if (row - 2 >= 0 && col - 1 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 2) * 8 + (col - 1))) {
			movements.add(new Movement(0, position, (row - 2) * 8 + (col - 1)));
		}
		if (row - 2 >= 0 && col + 1 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 2) * 8 + (col + 1))) {
			movements.add(new Movement(0, position, (row - 2) * 8 + (col + 1)));
		}
		if (row - 1 >= 0 && col + 2 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 1) * 8 + (col + 2))) {
			movements.add(new Movement(0, position, (row - 1) * 8 + (col + 2)));
		}
		if (row + 1 < 8 && col + 2 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 1) * 8 + (col + 2))) {
			movements.add(new Movement(0, position, (row + 1) * 8 + (col + 2)));
		}
		if (row + 2 < 8 && col + 1 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 2) * 8 + (col + 1))) {
			movements.add(new Movement(0, position, (row + 2) * 8 + (col + 1)));
		}
		if (row + 2 < 8 && col - 1 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 2) * 8 + (col - 1))) {
			movements.add(new Movement(0, position, (row + 2) * 8 + (col - 1)));
		}
		if (row + 1 < 8 && col - 2 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 1) * 8 + (col - 2))) {
			movements.add(new Movement(0, position, (row + 1) * 8 + (col - 2)));
		}

		return movements;
	}

	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		boolean movementOk = false;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		
		if ((Math.abs(startCol-endCol) == 1 && Math.abs(startRow-endRow) == 2) || 
				Math.abs(startCol-endCol) == 2 && Math.abs(startRow-endRow) == 1) {
			if (this.endSquareContainsEnemyOrEmpty(chessboard, end)) {
				movementOk = true;
			}
		}

		return movementOk && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Knight newKight = (Knight) super.clone();
		return newKight;
	}

}