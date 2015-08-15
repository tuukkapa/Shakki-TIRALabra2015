package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AI {
	
	public Move minimax(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		int bestValue, value;
		Move bestMove = null;
		if (depth == 0 || chessboard.isItCheckMate(!maximizingPlayer)) {
			return new Move(chessboard.getValue(), 0, 0);
		}
		bestValue = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		TreeMap<Integer, Piece> pieces = chessboard.getPieces(!maximizingPlayer);
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			ArrayList<Move> moves;
			moves = piece.getValue().getPossibleMoves(chessboard);
			for (int j = 0; j < moves.size(); j++) {
				Chessboard clone = this.cloneBoardAndPieces(chessboard);
				clone.movePiece(moves.get(j).getStart(), moves.get(j).getEnd());
				Move move = minimax(clone, depth - 1, !maximizingPlayer);
				value = move.getScore();
				if (maximizingPlayer) {
					if (bestValue < value) {
						bestValue = value;
					}
				} else {
					if (bestValue > value) {
						bestValue = value;
					}
				}
				bestMove = new Move(bestValue, piece.getValue().getPosition(), moves.get(j).getEnd());
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
