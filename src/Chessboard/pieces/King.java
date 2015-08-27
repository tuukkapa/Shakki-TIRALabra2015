package Chessboard.pieces;

import Chessboard.*;
import DataStructures.List;

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
	}
	
	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		List<Move> moves = new List<>();
		int kRow = position / 8;
		int kCol = position % 8;
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
