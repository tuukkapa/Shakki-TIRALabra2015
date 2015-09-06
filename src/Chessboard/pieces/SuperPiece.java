/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Chessboard;
import Chessboard.Move;
import DataStructures.List;

/**
 * This class is used to create protecting or attacking patterns from a chessboard
 * square. It is used at Evaluate-class to determine, whether own piece is protecting
 * player's another piece or whether enemy's piece is attacking player's piece.
 * 
 * This should not be used in the game as an actual piece.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class SuperPiece extends Piece {

	public SuperPiece(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'S' : 's';
		this.hasMoved = false;
	}
	
	/**
	 * Determines, if a piece positioned at superpiece's position is protected or attacked.
	 * @param chessboard Chessboard-object, where the game is played.
	 * @param own Boolean, whether the positions to be checked should be protecting or attacking.
	 * @return boolean, true = protected/attacked, false otherwise.
	 */
	public boolean getProtectionStatus(Chessboard chessboard, boolean own) {
		boolean colour = own ? this.white : !this.white;
		
		boolean status = this.createStraightProtectingMoves(chessboard, colour);
		if (status) {
			return true;
		}
		status = this.createDiagonalProtectingMoves(chessboard, colour);
		if (status) {
			return true;
		}
		
		int row = position / 8;
		int col = position % 8;
		
		Pawn pawn = new Pawn(colour, 0);
		Knight knight = new Knight(colour, 0);
		
		// is pawn protecting or attacking
		int movement = white ? 1 : -1;
		if (row + movement >= 0 && row + movement < 8 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, pawn, (row + movement) * 8 + col - 1)) {
			return true;
		}
		if (row + movement >= 0 && row + movement < 8 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, pawn, (row + movement) * 8 + col + 1)) {
			return true;
		}
		
		// is knight protecting or attacking
		if (row - 1 >= 0 && col - 2 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row - 1) * 8 + (col - 2))) {
			return true;
		}
		if (row - 2 >= 0 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row - 2) * 8 + (col - 1))) {
			return true;
		}
		if (row - 2 >= 0 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, knight, (row - 2) * 8 + (col + 1))) {
			return true;
		}
		if (row - 1 >= 0 && col + 2 < 8 && this.endSquareContainsPiece(chessboard, knight, (row - 1) * 8 + (col + 2))) {
			return true;
		}
		if (row + 1 < 8 && col + 2 < 8 && this.endSquareContainsPiece(chessboard, knight, (row + 1) * 8 + (col + 2))) {
			return true;
		}
		if (row + 2 < 8 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, knight, (row + 2) * 8 + (col + 1))) {
			return true;
		}
		if (row + 2 < 8 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row + 2) * 8 + (col - 1))) {
			return true;
		}
		if (row + 1 < 8 && col - 2 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row + 1) * 8 + (col - 2))) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines, if a piece positioned at superpiece's position is protected or attacked
	 * horizontally or vertically from the Piece-objects position (i.e. like Rook).
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return boolean, true = protected/attacked, false otherwise.
	 */
	private boolean createStraightProtectingMoves(Chessboard chessboard, boolean colour) {
		int startRow = position / 8;
		int startCol = position % 8;
		
		boolean rightBlocked = false, downBlocked = false, leftBlocked = false, upBlocked = false;
		int maxMovement = Math.max(Math.max(startRow, startCol), Math.max(7-startRow, 7-startCol));
		
		// check route
		for (int i = 1; i <= maxMovement; i++) {
			// check right
			if (!rightBlocked && startCol + i < 8) {
				int end = startRow * 8 + (startCol + i);
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					rightBlocked = true;
				}
			}
			// check down
			if (!downBlocked && startRow + i < 8) {
				int end = (startRow + i) * 8 + startCol;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					downBlocked = true;
				}
			}
			// check left
			if (!leftBlocked && startCol - i >= 0) {
				int end = (startRow * 8) + startCol - i;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					leftBlocked = true;
				}
			}
			// check up
			if (!upBlocked && startRow - i >= 0) {
				int end = (startRow - i) * 8 + startCol;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					upBlocked = true;
				}
			}
			if (rightBlocked && downBlocked && leftBlocked && upBlocked) {
				break;
			}
		}

		return false;
	}
	
	/**
	 * Determines, if a piece positioned at superpiece's position is protected or attacked
	 * diagonally from the Piece-objects position (i.e. like Bishop).
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return boolean, true = protected/attacked, false otherwise.
	 */
	private boolean createDiagonalProtectingMoves(Chessboard chessboard, boolean colour) {
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
				Piece piece = chessboard.getSquareContents(nePosition);
				if (chessboard.getSquareContents(row-i, col+i) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					neBlocked = true;
				}
			}
			// check south-east
			if (!seBlocked && row + i < 8 && col + i < 8) {
				Piece piece = chessboard.getSquareContents(sePosition);
				if (chessboard.getSquareContents(row+i, col+i) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					seBlocked = true;
				}
			}
			// check south-west
			if (!swBlocked && row + i < 8 && col - i >= 0) {
				Piece piece = chessboard.getSquareContents(swPosition);
				if (chessboard.getSquareContents(row+i, col-i) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					swBlocked = true;
				}
			}
			// check nort-west
			if (!nwBlocked && row - i >= 0 && col - i >= 0) {
				Piece piece = chessboard.getSquareContents(nwPosition);
				if (chessboard.getSquareContents(row-i, col-i) == null) {
					// the route is clear, continue
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.amIWhite() == colour) {
					return true;
				} else {
					nwBlocked = true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true, if end square contains same kind of piece which is given as parameter.
	 * @param chessboard Chessboard, where the game is played.
	 * @param piece Piece-object to be compared with.
	 * @param end Integer, chessboard square (0 = top left, 63 = bottom right).
	 * @return Boolean, true if end square contains same kind of piece which is given as parameter.
	 */
	private boolean endSquareContainsPiece(Chessboard chessboard, Piece piece, int end) {
		if (!(0 <= end && end < 64)) {
			return false;
		}
		Piece endContents = chessboard.getSquareContents(end);
		if (endContents == null) {
			return false;
		}
		if (endContents.getSign() != piece.getSign()) {
			return false;
		}
		return white ? endContents.amIWhite() : !endContents.amIWhite();
	}

	/**
	 * This method isn't used with SuperPiece.
	 * @param chessboard
	 * @return 
	 */
	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method isn't used with SuperPiece.
	 * @param chessboard
	 * @param end
	 * @return 
	 */
	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
