package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AI {
	
	public Movement minimax(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		int bestValue, value;
		Movement bestMove = null;
		if (depth == 0 || chessboard.isItCheckMate(!maximizingPlayer)) {
			return new Movement(chessboard.getValue(), 0, 0);
		}
		bestValue = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		TreeMap<Integer, Piece> pieces = chessboard.getPieces(!maximizingPlayer);
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			ArrayList<Movement> movements = piece.getValue().getPossibleMovements(chessboard);
			for (int j = 0; j < movements.size(); j++) {
				Chessboard clone = this.cloneBoardAndPieces(chessboard);
				clone.movePiece(movements.get(j).getStart(), movements.get(j).getEnd());
				value = minimax(clone, depth - 1, !maximizingPlayer).getScore();
				if (maximizingPlayer) {
					if (bestValue < value) {
						bestValue = value;
						bestMove = new Movement(clone.getValue(), piece.getValue().getPosition(), movements.get(j).getEnd());
					}
				} else {
					if (bestValue > value) {
						bestValue = value;
						bestMove = new Movement(clone.getValue(), piece.getValue().getPosition(), movements.get(j).getEnd());
					}
				}
			}
		}
		return bestMove;
	}
	
	private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Chessboard cloneBoard = new Chessboard();
		cloneBoard.setBoard(chessboard.cloneBoard());
		cloneBoard.setPieces(true, chessboard.clonePieces(true));
		cloneBoard.setPieces(false, chessboard.clonePieces(false));
		return cloneBoard;
	}
	
}
