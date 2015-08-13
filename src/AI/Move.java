package AI;

public class Move {

	private int score, start, end;
	
	public Move(int score, int start, int end) {
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