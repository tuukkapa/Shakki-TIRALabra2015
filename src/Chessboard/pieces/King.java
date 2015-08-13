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
		ArrayList<Movement> movements = new ArrayList<Movement>();
		// TODO
		return movements;
	}
	
	/**
	 * Moves king and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
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
		char king = white ? 'K' : 'k';

		char[][] tempBoard = chessboard.cloneBoard();
		
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && chessboard.endSquareContainsEnemyOrEmpty(white, endRow, endCol)) {
			chessboard.setSquare(start, ' ');
			chessboard.setSquare(end, king);
			chessboard.updateKingPosition(white);
			if (!chessboard.isItCheck(white)) {
				return true;
			}
		}
		chessboard.setBoard(tempBoard);
		chessboard.updateKingPosition(white);
		return false;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		King newKing = (King) super.clone();
		return newKing;
	}
	
}
