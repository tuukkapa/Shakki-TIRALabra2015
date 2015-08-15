/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard.pieces;

import Chessboard.Chessboard;
import java.util.ArrayList;

public class Bishop extends Piece implements Cloneable {
	
	public Bishop(boolean white, int position) {
		this.position = position;
		this.white = white;
		this.sign = white ? 'B' : 'b';
	}

	@Override
	public ArrayList getPossibleMovements(Chessboard chessboard) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean isMoveValid(Chessboard chessboard, int end) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
