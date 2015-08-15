/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chessboard.pieces;

import AI.Move;
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
	
	public abstract ArrayList getPossibleMoves(Chessboard chessboard);
	
	public abstract boolean isMoveValid(Chessboard chessboard, int end);
	
	protected ArrayList createStraightMoves(Chessboard chessboard) {
		ArrayList<Move> moves = new ArrayList<>();
		int startRow = position / 8;
		int startCol = position % 8;
		boolean rightBlocked = false, downBlocked = false, leftBlocked = false, upBlocked = false;
		// check route
		for (int i = 1; i <= Math.max(startRow, startCol); i++) {
			// check right
			if (!rightBlocked && startCol + i < 8) {
				if (chessboard.getSquareContents(startRow, startCol + i) == ' ') {
					moves.add(new Move(0, position, startRow * 8 + (startCol + i)));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, startRow * 8 + (startCol + i))) {
					moves.add(new Move(0, position, startRow * 8 + (startCol + i)));
					rightBlocked = true;
				} else {
					rightBlocked = true;
				}
			}
			// check down
			if (!downBlocked && startRow + i < 8) {
				if (chessboard.getSquareContents(startRow + i, startCol) == ' ') {
					moves.add(new Move(0, position, (startRow + i) * 8 + startCol));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, (startRow + i) * 8 + startCol)) {
					moves.add(new Move(0, position, (startRow + i) * 8 + startCol));
					downBlocked = true;
				} else {
					downBlocked = true;
				}
			}
			// check left
			if (!leftBlocked && startCol - i >= 0) {
				if (chessboard.getSquareContents(startRow, startCol - 1) == ' ') {
					moves.add(new Move(0, position, (startRow * 8) + startCol - i));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, (startRow * 8) + startCol - i)) {
					moves.add(new Move(0, position, (startRow * 8) + startCol - i));
					leftBlocked = true;
				} else {
					leftBlocked = true;
				}
			}
			// check up
			if (!upBlocked && startRow - i >= 0) {
				if (chessboard.getSquareContents(startRow - i, startCol) == ' ') {
					moves.add(new Move(0, position, (startRow - i) * 8 + startCol));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, (startRow - i) * 8 + startCol)) {
					moves.add(new Move(0, position, (startRow - i) * 8 + startCol));
					upBlocked = true;
				} else {
					upBlocked = true;
				}
			}
			if (rightBlocked && downBlocked && leftBlocked && upBlocked) {
				break;
			}
		}
		return moves;
	}
	
	protected ArrayList createDiagonalMoves(Chessboard chessboard) {
		ArrayList<Move> moves = new ArrayList<>();
		int row = position / 8;
		int col = position % 8;
		int maxMovement = 0;
		if (row < 4 && col < 4) {
			maxMovement = 7 - Math.max(row, col);
		} else if (row < 4 && col > 3) {
			maxMovement = Math.min(col, 7 - row);
		} else if (row > 3 && col < 4) {
			maxMovement = Math.min(row, 7 - col);
		} else {
			maxMovement = Math.min(row, col);
		}
		boolean neBlocked = false, seBlocked = false, swBlocked = false, nwBlocked = false;
		int nePosition, sePosition, swPosition, nwPosition;
		for (int i = 1; i < maxMovement; i++) {
			// check north-east
			nePosition = (row - i) * 8 + col + i;
			sePosition = (row + i) * 8 + col + i;
			swPosition = (row + i) * 8 + col - i;
			nwPosition = (row - i) * 8 + col - i;
			if (!neBlocked && row - i >= 0 && col + i < 8) {
				if (chessboard.getSquareContents(row-i, col+i) == ' ') {
					moves.add(new Move(0, position, nePosition));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, nePosition)) {
					moves.add(new Move(0, position, nePosition));
					neBlocked = true;
				} else {
					neBlocked = true;
				}
			}
			// check south-east
			if (!seBlocked && row + i < 8 && col + i < 8) {
				if (chessboard.getSquareContents(row+i, col+i) == ' ') {
					moves.add(new Move(0, position, sePosition));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, sePosition)) {
					moves.add(new Move(0, position, sePosition));
					seBlocked = true;
				} else {
					seBlocked = true;
				}
			}
			// check south-west
			if (!swBlocked && row + i < 8 && col - i >= 0) {
				if (chessboard.getSquareContents(row+i, col-i) == ' ') {
					moves.add(new Move(0, position, swPosition));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, swPosition)) {
					moves.add(new Move(0, position, swPosition));
					swBlocked = true;
				} else {
					swBlocked = true;
				}
			}
			// check nort-west
			if (!nwBlocked && row - i >= 0 && col - i >= 0) {
				if (chessboard.getSquareContents(row-i, col-i) == ' ') {
					moves.add(new Move(0, position, nwPosition));
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, nwPosition)) {
					moves.add(new Move(0, position, nwPosition));
					nwBlocked = true;
				} else {
					nwBlocked = true;
				}
			}
		}
		return moves;
	}
	
	protected boolean checkStraightRoutes(Chessboard chessboard, int end) {
		boolean moveOk = true;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		int movement = Math.abs(startRow - endRow) == 0 ? Math.abs(startCol - endCol) : Math.abs(startRow - endRow);
		for (int i = 1; i < movement; i++) {
			if (startRow == endRow) {
				if (chessboard.getSquareContents(startRow, Math.min(startCol, endCol) + i) != ' ') {
					moveOk = false;
					break;
				}
			} else if (startCol == endCol) {
				if (chessboard.getSquareContents(Math.min(startRow, endRow) + i, startCol) != ' ') {
					moveOk = false;
					break;
				}
			} else {
				moveOk = false;
				break;
			}
		}
		return moveOk;
	}
	
	/**
	 * Does the basic validation of given move, i.e. is any of the coordinates
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
	 * @param end
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
	 * @param end
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
