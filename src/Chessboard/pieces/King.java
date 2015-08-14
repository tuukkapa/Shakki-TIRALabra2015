/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;
import java.util.ArrayList;

/**
 *
 * @author tuukka
 */
public class King extends Piece implements Cloneable {
	
	public King(boolean white, int position) {
		this.position = position;
		this.white = white;
	}
	
	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		ArrayList<Movement> movements = new ArrayList<>();
		int kRow = position / 8;
		int kCol = position % 8;
		for (int row = kRow - 1; row < kRow+3; row++) {
			for (int col = kCol - 1; col < kCol+3; col++) {
				if (row >= 0 && row < 8 && col >= 0 && col < 8 && this.isMoveValid(chessboard, row*8 + col)) {
					movements.add(new Movement(0, position, row*8 + col));
				}
			}
		}
		return movements;
	}
	
	/**
	 *
	 * @param chessboard
	 * @param end
	 * @return
	 */
	@Override
	protected boolean isMoveValid(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		int startRow = position/8;
		int startCol = position%8;
		int endRow = end/8;
		int endCol = end%8;
		char king = white ? 'K' : 'k';
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && this.endSquareContainsEnemyOrEmpty(chessboard, end)) {
			char endSquareBackup = chessboard.getSquareContents(end);
			chessboard.setSquare(position, ' ');
			chessboard.setSquare(end, king);
			chessboard.updateKingPosition(white);
			if (!chessboard.isItCheck(white)) {
				chessboard.setSquare(position, king);
				chessboard.setSquare(end, endSquareBackup);
				chessboard.updateKingPosition(white);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves king and possibly captures an enemy piece, if allowed by chess rules.
	 * @param chessboard
	 * @param end
	 * @return True, if move is successfully done, false otherwise.
	 */
	@Override
	public boolean move(Chessboard chessboard, int end) {
		char king = white ? 'K' : 'k';
		if (this.isMoveValid(chessboard, end)) {
			if (this.endSquareContainsEnemy(chessboard, end)) {
				chessboard.getPieces(white).remove(end);
			}
			chessboard.setSquare(position, ' ');
			chessboard.setSquare(end, king);
			if (chessboard.movePiece(position, end)) {
				this.position = end;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		King newKing = (King) super.clone();
		return newKing;
	}
	
}
