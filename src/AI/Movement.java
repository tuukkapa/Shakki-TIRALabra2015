package AI;

public class Movement {

	//private Piece piece;
	private int score, start, end;
	
	public Movement(int score, int start, int end) {
		this.score = score;
		this.start = start;
		this.end = end;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
}