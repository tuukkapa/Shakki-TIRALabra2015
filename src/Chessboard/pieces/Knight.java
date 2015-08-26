/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.*;
import java.util.ArrayList;

/**
 * Class for Knight, creates Knight-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Knight extends Piece implements Cloneable {
	
	public Knight(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'N' : 'n';
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		ArrayList<Move> moves = new ArrayList<>();
		int row = position / 8;
		int col = position % 8;
		if (row - 1 >= 0 && col - 2 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 1) * 8 + (col - 2)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row - 1) * 8 + (col - 2))) {
			moves.add(new Move(position, (row - 1) * 8 + (col - 2)));
		}
		if (row - 2 >= 0 && col - 1 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 2) * 8 + (col - 1)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row - 2) * 8 + (col - 1))) {
			moves.add(new Move(position, (row - 2) * 8 + (col - 1)));
		}
		if (row - 2 >= 0 && col + 1 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 2) * 8 + (col + 1)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row - 2) * 8 + (col + 1))) {
			moves.add(new Move(position, (row - 2) * 8 + (col + 1)));
		}
		if (row - 1 >= 0 && col + 2 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row - 1) * 8 + (col + 2)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row - 1) * 8 + (col + 2))) {
			moves.add(new Move(position, (row - 1) * 8 + (col + 2)));
		}
		if (row + 1 < 8 && col + 2 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 1) * 8 + (col + 2)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row + 1) * 8 + (col + 2))) {
			moves.add(new Move(position, (row + 1) * 8 + (col + 2)));
		}
		if (row + 2 < 8 && col + 1 < 8 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 2) * 8 + (col + 1)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row + 2) * 8 + (col + 1))) {
			moves.add(new Move(position, (row + 2) * 8 + (col + 1)));
		}
		if (row + 2 < 8 && col - 1 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 2) * 8 + (col - 1)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row + 2) * 8 + (col - 1))) {
			moves.add(new Move(position, (row + 2) * 8 + (col - 1)));
		}
		if (row + 1 < 8 && col - 2 >= 0 && this.endSquareContainsEnemyOrEmpty(chessboard, (row + 1) * 8 + (col - 2)) && !ChessboardHandler.wouldItBeCheck(chessboard, this, (row + 1) * 8 + (col - 2))) {
			moves.add(new Move(position, (row + 1) * 8 + (col - 2)));
		}

		return moves;
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

		return movementOk && !ChessboardHandler.wouldItBeCheck(chessboard, this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Knight newKnight = (Knight) super.clone();
		return newKnight;
	}

}
