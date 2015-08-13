package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;

public class Pawn extends Piece implements Cloneable {
	
	public Pawn(boolean white, int position) {
		this.position = position;
		this.white = white;
	}
	
	@Override
	public Movement[] getPossibleMovements(Chessboard chessboard) {
		int amountToMove = white ? 1 : -1;
		Movement[] movements = new Movement[3];
		if (chessboard.getSquareContents(position/8 + amountToMove, position%8) == ' ') {
			movements[0] = new Movement(0, position, position + amountToMove*8);
		}
		// TODO captures
		return movements;
	}
	
	/**
	 * Moves pawn and possibly captures an enemy piece, if allowed by chess rules.
	 * @param chessboard
	 * @param start Start position on the board between 0 and 63 (0 is top left, 63 is bottom right).
	 * @param end End position on the board between 0 and 63 (0 is top left, 63 is bottom right).
	 * @return boolean value, whether the move is successfully done.
	 */
	@Override
	public boolean move(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		int start = position;
		int startRow = start/8;
		int startCol = start%8;
		int endRow = end/8;
		int endCol = end%8;
		char pawn = white ? 'P' : 'p';
		int homeRow = white ? 6 : 1;
		char[][] tempBoard = chessboard.getBoard();
		
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
			if (((Math.abs(startRow - endRow) == 2 && startRow == homeRow) || Math.abs(startRow - endRow) == 1) && chessboard.getSquareContents(endRow, endCol) == ' ') {
				chessboard.setSquare(start, ' ');
				chessboard.setSquare(end, pawn);
				if (!chessboard.isItCheck(white)) {
					this.position = end;
					return true;
				}
			}
		// capture
		} else {
			if ((Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) && chessboard.endSquareContainsEnemy(white, endRow, endCol)) {
				chessboard.setSquare(start, ' ');
				chessboard.setSquare(end, pawn);
				if (!chessboard.isItCheck(white)) {
					this.position = end;
					return true;
				}
			}
		}
		chessboard.setBoard(tempBoard);
		return false;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Pawn newPawn = (Pawn) super.clone();
		//… take care of any deep copies to be made here
		return newPawn;
	}
}
