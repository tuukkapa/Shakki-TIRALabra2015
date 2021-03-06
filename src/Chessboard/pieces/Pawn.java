package Chessboard.pieces;

import Chessboard.*;
import DataStructures.List;

/**
 * Class for Pawn, creates Pawn-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Pawn extends Piece implements Cloneable {
	
	/**
	 * Constructor of object Pawn.
	 * @param white Boolean, colour of the piece. True is white, false is black.
	 * @param position Integer, position of the piece on the board. 0 = top left,
	 * 63 = bottom right.
	 */
	public Pawn(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'P' : 'p';
		this.hasMoved = false;
	}
	
	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		List<Move> moves = new List<>();
		int pawnRow = position / 8;
		int pawnCol = position % 8;
		int movement = white ? -1 : 1;
		for (int col = pawnCol - 1; col <= pawnCol + 1; col++) {
			for (int row = 1; row <= 2; row++) {
				if ((col >= 0 && col < 8) && this.isMoveValid(chessboard, (pawnRow + (movement * row)) * 8 + col)) {
					moves.add(new Move(position, (pawnRow + (movement * row)) * 8 + col));
				}
			}
		}
		return moves;
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
					for (int i = 1; i <= Math.abs(startRow - endRow); i++) {
						if (chessboard.getSquareContents(startRow + (movement * i) , startCol) != null) {
							movementOk = false;
						}
					}
				// check moving 1 square
				} else if (Math.abs(startRow - endRow) != 1 || chessboard.getSquareContents(endRow, endCol) != null) {
					movementOk = false;
				}
			
		// check capture
		} else {
			movementOk = false;
			if (Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1 && this.endSquareContainsEnemy(chessboard, end)) {
				movementOk = true;
			}
		}
		
		return movementOk && !ChessboardHandler.wouldItBeCheck(chessboard, this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Pawn newPawn = (Pawn) super.clone();
		return newPawn;
	}

}