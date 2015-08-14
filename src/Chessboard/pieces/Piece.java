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
public abstract class Piece implements Cloneable {
	
	protected int position;
	protected boolean white;
	protected char sign;
	
	public int getPosition() {
		return position;
	}
	
	public boolean setPosition(int position) {
		if (0 <= position && position < 64) {
			this.position = position;
			return true;
		} else {
			return false;
		}
	}
	
	public char getSign() {
		return sign;
	}
	
	public boolean amIWhite() {
		return white;
	}
	
	public abstract ArrayList getPossibleMovements(Chessboard chessboard);
	
	public abstract boolean isMoveValid(Chessboard chessboard, int end);
	
	/**
	 * Does the basic validation of given movement, i.e. is any of the coordinates
	 * outside of the board.
	 * @param end
	 * @return True if coordinates are valid, false otherwise.
	 */
	protected boolean isCommandValid(int end) {
		return 0 <= end && end < 64;
	}
	
	/**
	 * Determines if end square contains enemy or is empty.
	 * @param chessboard
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if end square contains enemy or is empty, false otherwise.
	 */
	public boolean endSquareContainsEnemyOrEmpty(Chessboard chessboard, int end) {
		if (end < 0 || end > 63) {
			return false;
		}
		char[][] tempboard = chessboard.getBoard();
		boolean onTargetSquareIsEnemyPiece = white ? Character.isLowerCase(tempboard[end/8][end%8]) : Character.isUpperCase(tempboard[end/8][end%8]);	
		return tempboard[end/8][end%8] == ' ' || onTargetSquareIsEnemyPiece;
	}
	
	 /** Determines if end square contains enemy.
	 * @param chessboard
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if square contains enemy, false otherwise.
	 */
	public boolean endSquareContainsEnemy(Chessboard chessboard, int end) {
		if (end < 0 || end > 63) {
			return false;
		}
		char[][] tempboard = chessboard.getBoard();
		return white ? Character.isLowerCase(tempboard[end/8][end%8]) : Character.isUpperCase(tempboard[end/8][end%8]);	
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
