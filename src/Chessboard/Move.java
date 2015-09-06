package Chessboard;

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

	private int start, end, castlingRookStart, castlingRookEnd;
	private Piece capturedPiece;
	
	/**
	 * Constructor of Move-object.
	 * @param start Integer, starting position of the move.
	 * @param end Integer, ending position of the move.
	 */
	public Move(int start, int end) {
		this.start = start;
		this.end = end;
		this.castlingRookStart = -1;
		this.castlingRookEnd = -1;
		this.capturedPiece = null;
	}
	
	/**
	 * This constructor sets also the castling piece's start and end position.
	 * @param start Integer, starting position of the move.
	 * @param end Integer, ending position of the move.
	 * @param castlingRookStart Integer, starting position of the move of the castling piece.
	 * @param castlingRookEnd Integer, ending position of the move of the castling piece.
	 */
	public Move(int start, int end, int castlingRookStart, int castlingRookEnd) {
		this.start = start;
		this.end = end;
		this.castlingRookStart = castlingRookStart;
		this.castlingRookEnd = castlingRookEnd;
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
	 * Returns the castling piece's starting position.
	 * @return Integer, castling piece's position on the board. 0 is top left, 63 is bottom right.
	 */
	public int getCastlingRookStart() {
		return castlingRookStart;
	}
	
	/**
	 * Returns the castling piece's starting position.
	 * @return Integer, castling piece's position on the board. 0 is top left, 63 is bottom right.
	 */
	public int getCastlingRookEnd() {
		return castlingRookEnd;
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