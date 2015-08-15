package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;
import java.util.ArrayList;

public class Pawn extends Piece implements Cloneable {
	
	public Pawn(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'P' : 'p';
	}
	
	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		int amountToMove = white ? -8 : 8;
		ArrayList<Movement> movements = new ArrayList<>();
		int pawnRow = white ? (position / 8) - 1 : (position / 8) + 1;
		int pawnCol = position % 8;
		for (int col = pawnCol - 1; col <= pawnCol + 1; col++) {
			if ((col >= 0 && col < 8) && this.isMoveValid(chessboard, pawnRow * 8 + col)) {
				movements.add(new Movement(0, position, pawnRow * 8 + col));
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
		int homeRow = white ? 6 : 1;
		
		// prevent moving backwards
		if (white) {
			if (startRow <= endRow) {
				return false;
			}
		} else {
			if (startRow >= endRow) {
				return false;
			}
		}
		
		// check moving forward
		int movement = white ? -1 : 1;
		if (startCol == endCol) {
				// check moving 2 squares
				if (startRow == homeRow && Math.abs(startRow - endRow) == 2) {
					for (int i = startRow + movement; i <= Math.abs(startRow - endRow); i++) {
						if (chessboard.getSquareContents(startRow + (i * movement) , startCol) != ' ') {
							movementOk = false;
						}
					}
				// check moving 1 square
				} else if (Math.abs(startRow - endRow) != 1 || chessboard.getSquareContents(endRow, endCol) != ' ') {
					movementOk = false;
				}
			
		// check capture
		} else {
			movementOk = false;
			if (Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1 && this.endSquareContainsEnemy(chessboard, end)) {
				movementOk = true;
			}
		}
		
		return movementOk && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Pawn newPawn = (Pawn) super.clone();
		return newPawn;
	}

}
