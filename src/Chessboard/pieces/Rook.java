/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Move;
import Chessboard.Chessboard;
import java.util.ArrayList;

/**
 * Class for Rook, creates Rook-objects.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Rook extends Piece implements Cloneable {
	
	public Rook(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'R' : 'r';
	}
	
	@Override
	public ArrayList<Move> getPossibleMoves(Chessboard chessboard) {
		return this.createStraightMoves(chessboard);
	}
	
	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		return this.isCommandValid(end) && this.checkStraightRoutes(chessboard, end) && !chessboard.wouldItBeCheck(this, end);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Rook newRook = (Rook) super.clone();
		return newRook;
	}

}
