/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;
import java.util.ArrayList;

public class Queen extends Piece implements Cloneable {
	
	public Queen(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'Q' : 'q';
	}

	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		ArrayList<Movement> moves = this.createStraightMovements(chessboard);
		ArrayList<Movement> diagonalMoves = this.createDiagonalMovements(chessboard);
		moves.removeAll(diagonalMoves); // this should be redundant?
		moves.addAll(diagonalMoves);
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
		
		// Check route diagonally
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			for (int i = 1; i < Math.abs(endRow-startRow); i++) {
				// check south-east
				if (startRow+i < 8 && startCol+i < 8) {
					if (chessboard.getSquareContents(startRow+i, startCol+i) != ' ') {
						movementOk = false;
						break;
					}
				// check south-west
				} else if (startRow+i < 8 && startCol - i >= 0) {
					if (chessboard.getSquareContents(startRow+i, startCol-i) != ' ') {
						movementOk = false;
						break;
					}
				// check north-east
				} else if (startRow-i >= 0 && startCol+i < 8) {
					if (chessboard.getSquareContents(startRow-i, startCol+i) != ' ') {
						movementOk = false;
						break;
					}
				// check north-west
				} else if (startRow-i >= 0 && startCol-i >= 0) {
					if (chessboard.getSquareContents(startRow-i, endRow-i) != ' ') {
						movementOk = false;
						break;
					}
				} else {
					return false;
				}
			}
		}
		if (startRow == endRow || startCol == endCol) {
			movementOk = this.checkStraightRoutes(chessboard, end);
		}
		
		return movementOk && !chessboard.wouldItBeCheck(this, end) && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Queen newQueen = (Queen) super.clone();
		return newQueen;
	}

}
