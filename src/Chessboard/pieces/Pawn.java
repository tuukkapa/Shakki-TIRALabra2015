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
		boolean success = false;
		if (!this.isCommandValid(end)) {
			return false;
		}
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		char pawn = white ? 'P' : 'p';
		int homeRow = white ? 6 : 1;
		char endSquareBackup = chessboard.getSquareContents(end);
		
		if (white) {
			if (startRow <= endRow) {
				return false;
			}
		} else {
			if (startRow >= endRow) {
				return false;
			}
		}
		
		// move forward
		if (startCol == endCol) {
			if (((Math.abs(startRow - endRow) == 2 && startRow == homeRow) || Math.abs(startRow - endRow) == 1) && chessboard.getSquareContents(end) == ' ') {
				chessboard.setSquare(position, ' ');
				chessboard.setSquare(end, pawn);
				if (!chessboard.isItCheck(white)) {
					success = true;
				}
			}
		
		// capture
		} else {
			if ((Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) && this.endSquareContainsEnemy(chessboard, end)) {
				chessboard.setSquare(position, ' ');
				chessboard.setSquare(end, pawn);
				if (!chessboard.isItCheck(white)) {
					success = true;
				}
			}
		}
		chessboard.setSquare(position, pawn);
		chessboard.setSquare(end, endSquareBackup);
		return success;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Pawn newPawn = (Pawn) super.clone();
		//… take care of any deep copies to be made here
		return newPawn;
	}
}
