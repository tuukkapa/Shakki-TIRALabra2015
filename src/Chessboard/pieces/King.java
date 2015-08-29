package Chessboard.pieces;

import Chessboard.*;
import DataStructures.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for King, creates King-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class King extends Piece implements Cloneable {
	
	/**
	 * Constructor of object King.
	 * @param white Boolean, colour of the piece. True is white, false is black.
	 * @param position Integer, position of the piece on the board. 0 = top left,
	 * 63 = bottom right.
	 */
	public King(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'K' : 'k';
		this.hasMoved = false;
	}
	
	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		List<Move> moves = new List<>();
		int kRow = position / 8;
		int kCol = position % 8;
		Piece rookRight = chessboard.getSquareContents(this.getPosition() + 3);
		if (rookRight != null) {
			if (this.isCastlingPossible(chessboard, rookRight)) {
				moves.add(new Move(position, position + 2));
			}
		}
		Piece rookLeft = chessboard.getSquareContents(this.getPosition() - 4);
		if (rookLeft != null) {
			if (this.isCastlingPossible(chessboard, rookLeft)) {
				moves.add(new Move(position, position - 2));
			}
		}
		for (int row = -1; row <= 1; row++) {
			for (int col = -1; col <= 1; col++) {
				if (kRow + row >= 0 && kRow + row < 8 && kCol + col >= 0 && kCol + col < 8 && this.isMoveValid(chessboard, (kRow + row) * 8 + kCol + col)) {
					moves.add(new Move(position, (kRow + row) * 8 + kCol + col));
				}
			}
		}
		return moves;
	}
	
	/**
	 * Returns true, if castling with this king and rook given as parameter is possible.
	 * @param chessboard Chessboard, where the pieces are situated.
	 * @param rook Rook, with which the castling is to be done.
	 * @return True, if castling is possible, false otherwise.
	 */
	private boolean isCastlingPossible(Chessboard chessboard, Piece rook) {
		if (!(rook instanceof Rook)) {
			return false;
		}
		if (this.amIWhite() != rook.amIWhite()) {
			return false;
		}
		if (!this.getHasMoved() && !rook.getHasMoved()) {
			int direction = rook.getPosition() > this.getPosition() ? 1 : -1;
			for (int i = 1; i < Math.abs(rook.getPosition() - this.getPosition()); i++) {
				if (chessboard.getSquareContents(this.getPosition() + (direction * i)) != null) {
					return false;
				}
			}
			if (ChessboardHandler.wouldItBeCheck(chessboard, this, this.getPosition() + (direction * 1)) || ChessboardHandler.wouldItBeCheck(chessboard, this, this.getPosition() + (direction * 2))) {
				return false;
			}
			return true;
		}
		return false;
	}

	
	/**
	 *
	 * @param chessboard
	 * @param end
	 * @return
	 */
	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		if (!this.getHasMoved() && (end == this.getPosition() + 2 || end == this.getPosition() - 2)) {
			Piece rook = null;
			if (end > this.getPosition()) {
				if (chessboard.getSquareContents(this.getPosition() + 3) != null) {
					rook = chessboard.getSquareContents(this.getPosition() + 3);
				}
			} else {
				if (chessboard.getSquareContents(this.getPosition() - 4) != null) {
					rook = chessboard.getSquareContents(this.getPosition() - 4);
				}
			}
			if (this.isCastlingPossible(chessboard, rook)) {
				return true;
			}
		}
		boolean moveOk = false;
		int startRow = position/8;
		int startCol = position%8;
		int endRow = end/8;
		int endCol = end%8;
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && this.endSquareContainsEnemyOrEmpty(chessboard, end)) {
			moveOk = true;
		}
		return moveOk && !ChessboardHandler.wouldItBeCheck(chessboard, this, end);
	}
	
	/**
	 * Clones this piece-object.
	 * @return This object cloned.
	 * @throws CloneNotSupportedException 
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		King newKing = (King) super.clone();
		return newKing;
	}
	
}
