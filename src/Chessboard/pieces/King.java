package Chessboard.pieces;

import AI.Move;
import Chessboard.Chessboard;
import java.util.ArrayList;

/**
 * Class for King, creates King-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class King extends Piece implements Cloneable {
	
	public King(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'K' : 'k';
	}
	
	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		ArrayList<Move> moves = new ArrayList<>();
		int kRow = position / 8;
		int kCol = position % 8;
		for (int row = kRow - 1; row < kRow+3; row++) {
			for (int col = kCol - 1; col < kCol+3; col++) {
				if (row >= 0 && row < 8 && col >= 0 && col < 8 && this.isMoveValid(chessboard, row*8 + col)) {
					moves.add(new Move(position, row*8 + col));
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
		
		return moveOk && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		King newKing = (King) super.clone();
		return newKing;
	}
	
}
