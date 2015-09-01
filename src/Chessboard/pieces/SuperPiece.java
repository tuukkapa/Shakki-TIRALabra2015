/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Chessboard;
import Chessboard.ChessboardHandler;
import Chessboard.Move;
import DataStructures.List;

public class SuperPiece extends Piece {

	public SuperPiece(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'S' : 's';
		this.hasMoved = false;
	}
	

	public List<Move> getPossibleMoves(Chessboard chessboard, boolean own) {
		List<Move> moves = new List<>();
		boolean colour = own ? this.white : !this.white;
		
		moves.addAll(this.createStraightProtectingMoves(chessboard, colour));
		moves.addAll(this.createDiagonalProtectingMoves(chessboard, colour));
		
		int row = position / 8;
		int col = position % 8;
		
		Pawn pawn = new Pawn(colour, 0);
		Knight knight = new Knight(colour, 0);
		
		// is pawn protecting
		int movement = white ? 1 : -1;
		if (row + movement >= 0 && row + movement < 8 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, pawn, (row + movement) * 8 + col - 1)) {
			moves.add(new Move(position, (row + movement) * 8 + col - 1));
		}
		if (row + movement >= 0 && row + movement < 8 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, pawn, (row + movement) * 8 + col + 1)) {
			moves.add(new Move(position, (row + movement) * 8 + col + 1));
		}
		
		// is knight protecting
		if (row - 1 >= 0 && col - 2 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row - 1) * 8 + (col - 2))) {
			moves.add(new Move(position, (row - 1) * 8 + (col - 2)));
		}
		if (row - 2 >= 0 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row - 2) * 8 + (col - 1))) {
			moves.add(new Move(position, (row - 2) * 8 + (col - 1)));
		}
		if (row - 2 >= 0 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, knight, (row - 2) * 8 + (col + 1))) {
			moves.add(new Move(position, (row - 2) * 8 + (col + 1)));
		}
		if (row - 1 >= 0 && col + 2 < 8 && this.endSquareContainsPiece(chessboard, knight, (row - 1) * 8 + (col + 2))) {
			moves.add(new Move(position, (row - 1) * 8 + (col + 2)));
		}
		if (row + 1 < 8 && col + 2 < 8 && this.endSquareContainsPiece(chessboard, knight, (row + 1) * 8 + (col + 2))) {
			moves.add(new Move(position, (row + 1) * 8 + (col + 2)));
		}
		if (row + 2 < 8 && col + 1 < 8 && this.endSquareContainsPiece(chessboard, knight, (row + 2) * 8 + (col + 1))) {
			moves.add(new Move(position, (row + 2) * 8 + (col + 1)));
		}
		if (row + 2 < 8 && col - 1 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row + 2) * 8 + (col - 1))) {
			moves.add(new Move(position, (row + 2) * 8 + (col - 1)));
		}
		if (row + 1 < 8 && col - 2 >= 0 && this.endSquareContainsPiece(chessboard, knight, (row + 1) * 8 + (col - 2))) {
			moves.add(new Move(position, (row + 1) * 8 + (col - 2)));
		}
		
		return moves;
	}
	
	/**
	 * Objects protected helper-method. Creates Move-objects horizontally
	 * and vertically from the Piece-objects position (i.e. like Rook).
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return ArrayList of Move-objects.
	 */
	private List<Move> createStraightProtectingMoves(Chessboard chessboard, boolean colour) {
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
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					//
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, end));
					rightBlocked = true;
				} else {
					rightBlocked = true;
				}
			}
			// check down
			if (!downBlocked && startRow + i < 8) {
				int end = (startRow + i) * 8 + startCol;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					//
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, end));
					downBlocked = true;
				} else {
					downBlocked = true;
				}
			}
			// check left
			if (!leftBlocked && startCol - i >= 0) {
				int end = (startRow * 8) + startCol - i;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					//
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, end));
					leftBlocked = true;
				} else {
					leftBlocked = true;
				}
			}
			// check up
			if (!upBlocked && startRow - i >= 0) {
				int end = (startRow - i) * 8 + startCol;
				Piece piece = chessboard.getSquareContents(end);
				if (chessboard.getSquareContents(end) == null) {
					//
				} else if ((piece instanceof Rook || piece instanceof Queen) && piece.white == colour) {
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
	protected List<Move> createDiagonalProtectingMoves(Chessboard chessboard, boolean colour) {
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
				Piece piece = chessboard.getSquareContents(nePosition);
				if (chessboard.getSquareContents(row-i, col+i) == null) {
					//
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, nePosition));
					neBlocked = true;
				} else {
					neBlocked = true;
				}
			}
			// check south-east
			if (!seBlocked && row + i < 8 && col + i < 8) {
				Piece piece = chessboard.getSquareContents(sePosition);
				if (chessboard.getSquareContents(row+i, col+i) == null) {
					//
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, sePosition));
					seBlocked = true;
				} else {
					seBlocked = true;
				}
			}
			// check south-west
			if (!swBlocked && row + i < 8 && col - i >= 0) {
				Piece piece = chessboard.getSquareContents(swPosition);
				if (chessboard.getSquareContents(row+i, col-i) == null) {
					//
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, swPosition));
					swBlocked = true;
				} else {
					swBlocked = true;
				}
			}
			// check nort-west
			if (!nwBlocked && row - i >= 0 && col - i >= 0) {
				Piece piece = chessboard.getSquareContents(nwPosition);
				if (chessboard.getSquareContents(row-i, col-i) == null) {
					//
				} else if ((piece instanceof Bishop || piece instanceof Queen) && piece.white == colour) {
					moves.add(new Move(position, nwPosition));
					nwBlocked = true;
				} else {
					nwBlocked = true;
				}
			}
		}
		return moves;
	}
	
	public boolean endSquareContainsPiece(Chessboard chessboard, Piece piece, int end) {
		if (!this.isCommandValid(end)) {
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

	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
