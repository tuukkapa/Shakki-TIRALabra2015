package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import UI.UserInterface;
import java.util.ArrayList;
import java.util.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class does everything related to the game tree.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class AI {
	
	private Move bestMove;
	
	public AI() {
		bestMove = null;
	}
	
	public Move getMove(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		bestMove = null;
		//minimax(chessboard, depth, maximizingPlayer);
		max(chessboard, depth);
		return bestMove;
	}
	
	/**
	 * Constructs the game tree, which determines the next move for the computer.
	 * @param chessboard Chessboard-object with the game situation.
	 * @param depth Integer, how many moves ahead the game tree is constructed.
	 * @param maximizingPlayer True, if it is the turn of maximizingPlayer (i.e. computer), false otherwise.
	 * @return Move-object including starting and ending coordinates for one move, and value of the move.
	 * @throws CloneNotSupportedException 
	 */
	/*public int minimax(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		int bestValue, value;
		if (depth == 0 || chessboard.isItCheckMate(!maximizingPlayer)) {
			return chessboard.getValue();
		}
		bestValue = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		TreeMap<Integer, Piece> pieces = chessboard.getPieces(!maximizingPlayer);
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			ArrayList<Move> moves;
			moves = piece.getValue().getPossibleMoves(chessboard);
			for (int j = 0; j < moves.size(); j++) {
				Move move = moves.get(j);
				Chessboard clone = this.cloneBoardAndPieces(chessboard);
				clone.movePiece(move.getStart(), move.getEnd());
				value = minimax(clone, depth - 1, !maximizingPlayer);
				if (maximizingPlayer) {
					if (bestValue < value) {
						bestValue = value;
						bestMove = move;
					}
				} else {
					if (bestValue > value) {
						bestValue = value;
					}
				}
			}
		}
		
		return bestValue;
	}*/
	
	private int max(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate(false))
			return chessboard.getValue();
		int best = Integer.MIN_VALUE;
		Move[] moves = this.treeMapToArray(chessboard, chessboard.getPieces(false), false);
		for (int i = 0; i < moves.length; i++) {
			Move mv = moves[i];
			chessboard.makeMove(mv);
			int val = -min(chessboard, depth-1);
			if (val>best) {
				best = val;
				bestMove = mv; // Current choice of move
			}
			chessboard.undoMove(chessboard, mv);
		}
		return best;
	}
	
	private int min(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate(true))
			return chessboard.getValue();
		int best = Integer.MIN_VALUE;
		Move[] moves = this.treeMapToArray(chessboard, chessboard.getPieces(true), true);
		for (int i = 0; i < moves.length; i++) {
			Move mv = moves[i];
			chessboard.makeMove(mv);
			int val = -max(chessboard, depth-1);
			if (val>best) {
				best = val;
			}
			chessboard.undoMove(chessboard, mv);
		}
		return best;
	}
	
	/**
	 * Uses chessboard-objects methods to clone it's board and also pieces into new objects.
	 * @param chessboard Chessboard-object to be cloned.
	 * @return New Chessboard-object, clone of the object and other objects inside it.
	 * @throws CloneNotSupportedException 
	 */
	private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Chessboard cloneBoard = new Chessboard();
		cloneBoard.setBoard(chessboard.cloneBoard());
		cloneBoard.setPieces(true, chessboard.clonePieces(true));
		cloneBoard.setPieces(false, chessboard.clonePieces(false));
		return cloneBoard;
	}
	
	private Move[] treeMapToArray(Chessboard chessboard, TreeMap<Integer, Piece> pieces, boolean white) {
		int size = 0;
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			size += piece.getValue().getPossibleMoves(chessboard).size();
		}
		Move[] moves = new Move[size];
		int counter = 0;
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			ArrayList<Move> movelist = piece.getValue().getPossibleMoves(chessboard);
			for (int i = 0; i < movelist.size(); i++) {
				moves[counter] = movelist.get(i);
				counter++;
			}
		}
		return moves;
	}
	
}
