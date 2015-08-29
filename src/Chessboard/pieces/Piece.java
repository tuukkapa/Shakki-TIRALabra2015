package Chessboard.pieces;

import Chessboard.*;
import DataStructures.List;
import java.util.ArrayList;

/**
 * Piece-class from which other pieces are extended.
 * 
 * Fields:
 * - Position, integer
 * - Colour, boolean (true = white, false = black)
 * - Sign, character (e.g. P = white pawn. See more info at Chessboard.java).
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public abstract class Piece implements Cloneable {
	
	protected int position;
	protected boolean white;
	protected char sign;
	protected boolean hasMoved;
	
	/**
	 * Returns the position of this Piece.
	 * @return Integer, position of the piece.
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Sets the position of this Piece.
	 * @param position Integer, position of this piece.
	 * @return True, if given position is valid, false otherwise.
	 */
	public boolean setPosition(int position) {
		if (0 <= position && position < 64) {
			this.position = position;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the piece's sign as a character.
	 * @return Character, sign of this piece.
	 */
	public char getSign() {
		return sign;
	}
	
	/**
	 * Returns boolean value, telling the colour of the piece.
	 * @return True, if piece is white, false if piece is black.
	 */
	public boolean amIWhite() {
		return white;
	}
	
	/**
	 * Sets the hasMoved value to true meaning that the piece has moved in the game.
	 */
	public void setHasMoved(boolean value) {
		this.hasMoved = value;
	}
	
	/**
	 * Returns the boolean value, whether this piece has moved yet in the game.
	 * @return Boolean, true means piece has moved, false means it hasn't.
	 */
	public boolean getHasMoved() {
		return this.hasMoved;
	}
	
	/**
	 * Returns an ArrayList of Move-objects, which are possible for the piece in question.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return ArrayList of Move-objects.
	 */
	public abstract List<Move> getPossibleMoves(Chessboard chessboard);
	
	/**
	 * Verifies if this Piece can be moved to the end position according to the chess rules.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True, if command is valid, false otherwise.
	 */
	public abstract boolean isMoveValid(Chessboard chessboard, int end);
	
	/**
	 * Objects protected helper-method. Creates Move-objects horizontally
	 * and vertically from the Piece-objects position (i.e. like Rook).
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return ArrayList of Move-objects.
	 */
	protected List<Move> createStraightMoves(Chessboard chessboard) {
		List<Move> moves = new List<>();
		int startRow = position / 8;
		int startCol = position % 8;
		
		boolean rightBlocked = false, downBlocked = false, leftBlocked = false, upBlocked = false;
		int maxMovement = Math.max(Math.max(startRow, startCol), Math.max(7-startRow, 7-startCol));
		
		// check route
		for (int i = 1; i <= maxMovement; i++) {
			// check right
			if (!rightBlocked && startCol + i < 8) {
				int end = startRow * 8 + (startCol + i);
				if (chessboard.getSquareContents(end) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
						moves.add(new Move(position, end));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, end) && !ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
					moves.add(new Move(position, end));
					rightBlocked = true;
				} else {
					rightBlocked = true;
				}
			}
			// check down
			if (!downBlocked && startRow + i < 8) {
				int end = (startRow + i) * 8 + startCol;
				if (chessboard.getSquareContents(end) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
						moves.add(new Move(position, end));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, end) && !ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
					moves.add(new Move(position, end));
					downBlocked = true;
				} else {
					downBlocked = true;
				}
			}
			// check left
			if (!leftBlocked && startCol - i >= 0) {
				int end = (startRow * 8) + startCol - i;
				if (chessboard.getSquareContents(end) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
						moves.add(new Move(position, end));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, end) && !ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
					moves.add(new Move(position, end));
					leftBlocked = true;
				} else {
					leftBlocked = true;
				}
			}
			// check up
			if (!upBlocked && startRow - i >= 0) {
				int end = (startRow - i) * 8 + startCol;
				if (chessboard.getSquareContents(end) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
						moves.add(new Move(position, end));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, end) && !ChessboardHandler.wouldItBeCheck(chessboard, this, end)) {
					moves.add(new Move(position, end));
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
	
	/**
	 * Objects protected helper-method. Creates Move-objects diagonally
	 * from the Piece-objects position (i.e. like Rook).
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return ArrayList of Move-objects.
	 */
	protected List<Move> createDiagonalMoves(Chessboard chessboard) {
		List<Move> moves = new List<>();
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
		for (int i = 1; i <= maxMovement; i++) {
			// check north-east
			nePosition = (row - i) * 8 + col + i;
			sePosition = (row + i) * 8 + col + i;
			swPosition = (row + i) * 8 + col - i;
			nwPosition = (row - i) * 8 + col - i;
			if (!neBlocked && row - i >= 0 && col + i < 8) {
				if (chessboard.getSquareContents(row-i, col+i) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, nePosition)) {
						moves.add(new Move(position, nePosition));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, nePosition) && !ChessboardHandler.wouldItBeCheck(chessboard, this, nePosition)) {
					moves.add(new Move(position, nePosition));
					neBlocked = true;
				} else {
					neBlocked = true;
				}
			}
			// check south-east
			if (!seBlocked && row + i < 8 && col + i < 8) {
				if (chessboard.getSquareContents(row+i, col+i) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, sePosition)) {
						moves.add(new Move(position, sePosition));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, sePosition) && !ChessboardHandler.wouldItBeCheck(chessboard, this, sePosition)) {
					moves.add(new Move(position, sePosition));
					seBlocked = true;
				} else {
					seBlocked = true;
				}
			}
			// check south-west
			if (!swBlocked && row + i < 8 && col - i >= 0) {
				if (chessboard.getSquareContents(row+i, col-i) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, swPosition)) {
						moves.add(new Move(position, swPosition));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, swPosition) && !ChessboardHandler.wouldItBeCheck(chessboard, this, swPosition)) {
					moves.add(new Move(position, swPosition));
					swBlocked = true;
				} else {
					swBlocked = true;
				}
			}
			// check nort-west
			if (!nwBlocked && row - i >= 0 && col - i >= 0) {
				if (chessboard.getSquareContents(row-i, col-i) == null) {
					if (!ChessboardHandler.wouldItBeCheck(chessboard, this, nwPosition)) {
						moves.add(new Move(position, nwPosition));
					}
				} else if (this.endSquareContainsEnemyOrEmpty(chessboard, nwPosition) && !ChessboardHandler.wouldItBeCheck(chessboard, this, nwPosition)) {
					moves.add(new Move(position, nwPosition));
					nwBlocked = true;
				} else {
					nwBlocked = true;
				}
			}
		}
		return moves;
	}
	
	/**
	 * Verifies, if the horizontal or vertical route until the end square is free.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True, if route is free, false otherwise.
	 */
	protected boolean checkStraightRoutes(Chessboard chessboard, int end) {
		boolean moveOk = true;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		int movement = Math.abs(startRow - endRow) == 0 ? Math.abs(startCol - endCol) : Math.abs(startRow - endRow);
		if (!(startRow == endRow || startCol == endCol)) {
			return false;
		}
		for (int i = 1; i < movement; i++) {
			if (startRow == endRow) {
				if (chessboard.getSquareContents(startRow, Math.min(startCol, endCol) + i) != null) {
					moveOk = false;
					break;
				}
			} else {
				if (chessboard.getSquareContents(Math.min(startRow, endRow) + i, startCol) != null) {
					moveOk = false;
					break;
				}
			}
		}
		return moveOk && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	/**
	 * Verifies, if the diagonal route until the end square is free.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True, if route is free, false otherwise.
	 */
	protected boolean checkDiagonalRoutes(Chessboard chessboard, int end) {
		boolean moveOk = true;
		int startRow = position / 8;
		int startCol = position % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		if (Math.abs(startRow - endRow) != Math.abs(startCol-endCol)) {
			return false;
		}
		for (int i = 1; i < Math.abs(endRow-startRow); i++) {
			// check south-east
			if (startRow < endRow && startCol < endCol) {
				if (startRow+i < 8 && startCol+i < 8 && chessboard.getSquareContents(startRow+i, startCol+i) != null) {
					moveOk = false;
					break;
				}
			// check south-west
			} else if (startRow < endRow && endCol < startCol) {
				if (startRow+i < 8 && startCol - i >= 0 && chessboard.getSquareContents(startRow+i, startCol-i) != null) {
					moveOk = false;
					break;
				}
			// check north-east
			} else if (endRow < startRow && startCol < endCol) {
				if (startRow-i >= 0 && startCol+i < 8 && chessboard.getSquareContents(startRow-i, startCol+i) != null) {
					moveOk = false;
					break;
				}
			// check north-west
			} else if (endRow < startRow && endCol < startCol) {
				if (startRow-i >= 0 && startCol-i >= 0 && chessboard.getSquareContents(startRow-i, startCol-i) != null) {
					moveOk = false;
					break;
				}
			} else {
				return false;
			}
		}
		return moveOk && this.endSquareContainsEnemyOrEmpty(chessboard, end);
	}
	
	/**
	 * Does the basic validation of given move, i.e. is any of the coordinates
	 * outside of the board.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True if coordinates are valid, false otherwise.
	 */
	protected boolean isCommandValid(int end) {
		return 0 <= end && end < 64;
	}
	
	/**
	 * Determines if end square contains enemy or piece is empty.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True, if end square contains enemy or is empty, false otherwise.
	 */
	public boolean endSquareContainsEnemyOrEmpty(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		Piece endContents = chessboard.getSquareContents(end);
		if (endContents == null) {
			return true;
		}
		return white ? !endContents.amIWhite() && !(endContents instanceof King) : endContents.amIWhite() && !(endContents instanceof King);
	}
	
	/** Determines if end square contains enemy.
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @param end Position, where the current Piece-object is to be moved.
	 * @return True, if square contains enemy, false otherwise.
	 */
	public boolean endSquareContainsEnemy(Chessboard chessboard, int end) {
		if (!this.isCommandValid(end)) {
			return false;
		}
		Piece endContents = chessboard.getSquareContents(end);
		if (endContents != null) {
			return white ? !endContents.amIWhite() : endContents.amIWhite();
		} else {
			return false;
		}
	}
	
	/**
	 * Clones this Piece-object.
	 * @return The cloned Piece-object.
	 * @throws CloneNotSupportedException 
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}