package AI;

import Chessboard.pieces.Piece;

public class Movement {

	//private Piece piece;
	private int score, start, end;
	
	public Movement(/*Piece piece,*/ int score, int start, int end) {
		//this.piece = piece;
		this.score = score;
		this.start = start;
		this.end = end;
	}
	
	public int getScore() {
		return score;
	}
	
	/*public Piece getPiece() {
		return piece;
	}*/
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
}