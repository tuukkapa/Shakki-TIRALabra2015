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
		this.sign = white ? 'K' : 'k';
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
	public boolean isMoveValid(Chessboard chessboard, int end) {
		boolean success = false;
		if (!this.isCommandValid(end)) {
			return false;
		}
		int startRow = position/8;
		int startCol = position%8;
		int endRow = end/8;
		int endCol = end%8;
		char king = white ? 'K' : 'k';
		char endSquareBackup = chessboard.getSquareContents(end);
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && this.endSquareContainsEnemyOrEmpty(chessboard, end)) {
			chessboard.setSquare(position, ' ');
			chessboard.setSquare(end, king);
			chessboard.updateKingPosition(white);
			if (!chessboard.isItCheck(white)) {
				success = true;
			}
		}
		chessboard.setSquare(position, king);
		chessboard.setSquare(end, endSquareBackup);
		chessboard.updateKingPosition(white);
		return success;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		King newKing = (King) super.clone();
		return newKing;
	}
	
}
