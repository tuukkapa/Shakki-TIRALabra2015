package AI;

import Chessboard.pieces.Piece;

/**
 * Class contains one chess move and it's score.
 * 
 * Fields:
 * - Integer: starting position
 * - Integer: ending position
 * - Piece: possibly captured piece during the move.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Move {

	private int start, end;
	private Piece capturedPiece;
	
	/**
	 * Constructor of Move-object.
	 * @param start Integer, starting position of the move.
	 * @param end Integer, ending position of the move.
	 */
	public Move(int start, int end) {
		this.start = start;
		this.end = end;
		this.capturedPiece = null;
	}
	
	/**
	 * Returns the starting position of the move.
	 * @return Integer, starting position of the move.
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Returns the starting position of the move.
	 * @return Integer, ending position of the move.
	 */
	public int getEnd() {
		return end;
	}
	
	/**
	 * Sets the captured Piece-field.
	 * @param piece Piece-object, which is saved into this Move-object.
	 */
	public void setCapturedPiece(Piece piece) {
		this.capturedPiece = piece;
	}
	
	/**
	 * Returns the Piece-field's contents.
	 * @return Piece-object or null if no piece is captured.
	 */
	public Piece getCapturedPiece() {
		return capturedPiece;
	}
	
}