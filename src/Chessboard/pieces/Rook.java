/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.*;
import DataStructures.List;

/**
 * Class for Rook, creates Rook-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Rook extends Piece implements Cloneable {
	
	/**
	 * Constructor of object Rook.
	 * @param white Boolean, colour of the piece. True is white, false is black.
	 * @param position Integer, position of the piece on the board. 0 = top left,
	 * 63 = bottom right.
	 */
	public Rook(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'R' : 'r';
	}
	
	@Override
	public List<Move> getPossibleMoves(Chessboard chessboard) {
		return this.createStraightMoves(chessboard);
	}
	
	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		return this.isCommandValid(end) && this.checkStraightRoutes(chessboard, end) && !ChessboardHandler.wouldItBeCheck(chessboard, this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rook newRook = (Rook) super.clone();
		return newRook;
	}

}
