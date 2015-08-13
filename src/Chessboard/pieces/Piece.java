/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chessboard.pieces;

import AI.Movement;
import Chessboard.Chessboard;

/**
 *
 * @author tuukka
 */
public abstract class Piece implements Cloneable {
	
	protected int position;
	protected boolean white;
	
	public int getPosition() {
		return position;
	}
	
	public boolean amIWhite() {
		return white;
	}
	
	public abstract Movement[] getPossibleMovements(Chessboard chessboard);
	
	public abstract boolean move(Chessboard chessboard, int end);
	
	/**
	 * Does the basic validation of given movement, i.e. is any of the coordinates
	 * outside of the board.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True if coordinates are valid, false otherwise.
	 */
	protected boolean isCommandValid(int end) {
		return 0 <= end && end < 64;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
