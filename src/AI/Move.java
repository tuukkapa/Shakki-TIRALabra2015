package AI;

/**
 * Class contains one chess move and it's score.
 * 
 * Fields:
 * - Integer: score of the move
 * - Integer: starting position
 * - Integer: ending position
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Move {

	private int score, start, end;
	
	/**
	 * Constructor of Move-object.
	 * @param score Integer, score of the move.
	 * @param start Integer, starting position of the move.
	 * @param end Integer, ending position of the move.
	 */
	public Move(int score, int start, int end) {
		this.score = score;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Returns the score of the move.
	 * @return Integer, score of the move.
	 */
	public int getScore() {
		return score;
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
	 *
	 * @param move
	 * @return
	 */
	public boolean equals(Move move) {
		boolean scoreEquals = this.score == move.score;
		boolean startEquals = this.start == move.start;
		boolean endEquals = this.end == move.end;
		return scoreEquals && startEquals && endEquals;
	}
	
}