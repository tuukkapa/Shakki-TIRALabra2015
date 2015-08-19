
package Chessboard;

import AI.Move;
import Chessboard.pieces.Bishop;
import Chessboard.pieces.King;
import Chessboard.pieces.Knight;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Queen;
import Chessboard.pieces.Rook;
import UI.UserInterface;
import java.util.ArrayList;

public class Chessboard {
	
	private Piece[][] chessboard;
	private ArrayList<Piece> blackPieces, whitePieces;
	private Piece whiteKing, blackKing;
	
	public Chessboard() {
		chessboard = new Piece[8][8];
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		this.setBoard(newboard);
	}
	
	public Chessboard(char[][] newboard) {
		chessboard = new Piece[8][8];
		this.setBoard(newboard);
	}
	
	public Chessboard(Piece[][] newboard) {
		chessboard = newboard;
	}
	
	private void setBoardbyCloningOrig(Piece[][] newboard) throws CloneNotSupportedException {
		blackPieces = new ArrayList<>();
		whitePieces = new ArrayList<>();
		for (int i = 0; i < 64; i++) {
			if (newboard[i/8][i%8] == null) {
				chessboard[i/8][i%8] = null;
			} else {
				chessboard[i/8][i%8] = (Piece)newboard[i/8][i%8].clone();
				if (newboard[i/8][i%8].amIWhite()) {
					whitePieces.add(chessboard[i/8][i%8]);
				} else {
					blackPieces.add(chessboard[i/8][i%8]);
				}
				if (chessboard[i/8][i%8] instanceof King && chessboard[i/8][i%8].amIWhite()) {
					whiteKing = chessboard[i/8][i%8];
				}
				if (chessboard[i/8][i%8] instanceof King && !chessboard[i/8][i%8].amIWhite()) {
					blackKing = chessboard[i/8][i%8];
				}
			}
		}
	}
	
	private Piece[][] clonePieceArray() {
		Piece[][] newBoard = new Piece[8][8];
		for (int i = 0; i < chessboard.length; i++) {
			System.arraycopy(chessboard[i], 0, newBoard[i], 0, chessboard[i].length);
		}
		return newBoard;
	}
	
	public Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Piece[][] cloneArray = chessboard.clonePieceArray();
		Chessboard clonedBoard = new Chessboard();
		clonedBoard.setBoardbyCloningOrig(cloneArray);
		return clonedBoard;
	}
	
	private void setBoard(char[][] newboard) {
		blackPieces = new ArrayList<>();
		whitePieces = new ArrayList<>();
		for (int i = 0; i < 64; i++) {
			if (newboard[i/8][i%8] == 'p') {
				chessboard[i/8][i%8] = new Pawn(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'r') {
				chessboard[i/8][i%8] = new Rook(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'n') {
				chessboard[i/8][i%8] = new Knight(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'b') {
				chessboard[i/8][i%8] = new Bishop(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'q') {
				chessboard[i/8][i%8] = new Queen(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'k') {
				blackKing = new King(false, i);
				chessboard[i/8][i%8] = blackKing;
				blackPieces.add(blackKing);
			} else if (newboard[i/8][i%8] == 'P') {
				chessboard[i/8][i%8] = new Pawn(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'R') {
				chessboard[i/8][i%8] = new Rook(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'N') {
				chessboard[i/8][i%8] = new Knight(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'B') {
				chessboard[i/8][i%8] = new Bishop(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'Q') {
				chessboard[i/8][i%8] = new Queen(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
			} else if (newboard[i/8][i%8] == 'K') {
				whiteKing = new King(true, i);
				chessboard[i/8][i%8] = whiteKing;
				whitePieces.add(whiteKing);
			} else {
				chessboard[i/8][i%8] = null;
			}
		}
	}
	
	public ArrayList<Piece> getPieces(boolean white) {
		return white ? whitePieces : blackPieces;
	}
	
	public Piece getSquareContents(int position) {
		return chessboard[position/8][position%8];
	}
	
	public Piece getSquareContents(int row, int col) {
		return chessboard[row][col];
	}
	
	public char[][] getBoardAsCharArray() {
		char[][] newboard = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j] == null) {
					newboard[i][j] = ' ';
				} else {
					newboard[i][j] = chessboard[i][j].getSign();
				}
			}
		}
		return newboard;
	}
	
	public boolean movePiece(Move move) {
		if (move == null) {
			return false;
		}
		int start = move.getStart();
		int end = move.getEnd();
		Piece piece = chessboard[start/8][start%8];
		if (piece == null) {
			//this.errorToScreen(piece, move, end);
			return false;
		}
		if (piece.isMoveValid(this, end)) {
			if (!this.performMove(move)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	private boolean performMove(Move move) {
		Piece piece = this.getSquareContents(move.getStart());
		if (piece == null) {
			return false;
		}
		int start = move.getStart();
		int end = move.getEnd();
		if (piece.endSquareContainsEnemy(this, end)) {
				Piece capturedPiece = chessboard[end/8][end%8];
				move.setCapturedPiece(capturedPiece);
				//System.out.println("\nSyötiin: " + capturedPiece + " " + capturedPiece.getSign());
				//System.out.println("sijainti: r" + capturedPiece.getPosition()/8 + " s" + capturedPiece.getPosition()%8);
		}
		chessboard[start/8][start%8] = null;
		chessboard[end/8][end%8] = piece;
		piece.setPosition(end);
		return true;
	}
	
	private boolean undoMove(Move move) {
		Piece capturedPiece = move.getCapturedPiece();
		Piece movedPiece = chessboard[move.getEnd()/8][move.getEnd()%8];
		if (movedPiece == null) {
			return false;
		}
		chessboard[move.getStart()/8][move.getStart()%8] = movedPiece;
		if (capturedPiece == null) {
			chessboard[move.getEnd()/8][move.getEnd()%8] = null;
		} else {
			chessboard[move.getEnd()/8][move.getEnd()%8] = capturedPiece;
		}
		movedPiece.setPosition(move.getStart());
		return true;
	}
	
	public boolean wouldItBeCheck(Piece piece, int end) {
		Move move = new Move(piece.getPosition(), end);
		if (!this.performMove(move)) {
			this.errorToScreen(piece, move, end);
		}
		boolean checkSituation = this.isItCheck(piece.amIWhite());
		if (!this.undoMove(move)) {
			this.errorToScreen(piece, move, end);
		}
		return checkSituation;
	}
	
	private void errorToScreen(Piece piece, Move move, int end) {
		System.out.println("\n=====================================");
		System.out.println("Siirtoa tehtäessä tapahtui virhe");
		System.out.println("Nappulaa " + piece + " ei löytynyt laudalta");
		if (piece != null) {
			System.out.println("Nappula on omasta mielestään: r" + piece.getPosition()/8 + " s" + piece.getPosition()%8);
			System.out.println("Loppukoordinaatit: r" + end/8 + " s" + end%8);
		}
		System.out.println("Lauta oli:");
		UserInterface.draw(this.getBoardAsCharArray());
		System.out.println("\nMustat nappulat:");
		ArrayList<Piece> pieces = this.getPieces(false);
		for (Piece onePiece : pieces) {
			System.out.println(onePiece.getSign() + " r" + (onePiece.getPosition()/8) + " s" + (onePiece.getPosition()%8));
		}
		System.out.println("\nValkoiset nappulat:");
		ArrayList<Piece> wpieces = this.getPieces(true);
		for (Piece onePiece : wpieces) {
			System.out.println(onePiece.getSign() + " r" + (onePiece.getPosition()/8) + " s" + (onePiece.getPosition()%8));
		}
		System.out.println("\nSiirtokäsky sisälsi");
		System.out.println("Nappula: " + move.getPiece() + " ja väri " + move.getPiece().amIWhite());
		System.out.println("Alku: r" + move.getStart()/8 + " s" + move.getStart()%8 + " loppu: r" + move.getEnd()/8 + " s" + move.getEnd()%8);
		System.out.println("=====================================\n");
	}
	
	public boolean isItCheck(boolean checkedIsWhite) {
		int kRow, kCol;
		
		kRow = checkedIsWhite ? whiteKing.getPosition()/8 : blackKing.getPosition()%8;
		kCol = checkedIsWhite ? whiteKing.getPosition()%8 : blackKing.getPosition()%8;
		
		boolean pieceIsEnemy;
		
		// is piece past enemy's king, e.g. pawn
		boolean pieceIsPastKing;
		
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] != null) {
				pieceIsEnemy = checkedIsWhite ? !chessboard[i/8][i%8].amIWhite() : chessboard[i/8][i%8].amIWhite();
				
				// is Pawn threatening
				if (Math.abs(kRow - (i/8)) == 1 && Math.abs(kCol - (i%8)) == 1) {
					pieceIsPastKing = checkedIsWhite ? kRow < i/8 : kRow > i/8;
					if (pieceIsEnemy && chessboard[i/8][i%8] instanceof Pawn && !pieceIsPastKing) {
						return true;
					}
				}

				// is Knight threatening
				if ((Math.abs(kRow - (i/8)) == 2 && Math.abs(kCol - (i%8)) == 1) || 
						(Math.abs(kRow - (i/8)) == 1 && Math.abs(kCol - (i%8)) == 2)) {
					if (pieceIsEnemy && chessboard[i/8][i%8] instanceof Knight) {
						return true;
					}
				}

				// is Bishop threatening or Queen threatening diagonally
				if (Math.abs(kRow - (i/8)) == Math.abs(kCol - (i%8)) && pieceIsEnemy && (chessboard[i/8][i%8] instanceof Bishop || chessboard[i/8][i%8] instanceof Queen)) {
					boolean routeFree = true;
					for (int j = 1; j < Math.abs(kRow -(i/8)); j++) {
						/*	Index out of bounds doesn't happen because when either of the two first expressions are false
							then the third one isn't checked. In other words, the first two prevent checking outside of
							the array.
						*/
						/*	Check north-west:
							current row is less than king's row
							current column is less than stking's column
						*/
						if (i/8 < kRow && i%8 < kCol && chessboard[kRow-j][kCol-j] != null) {
							routeFree = false;
						}
						// Check south-west
						if (i/8 > kRow && i%8 < kCol && chessboard[kRow+j][kCol-j] != null) {
							routeFree = false;
						}
						// Check north-east
						if (i/8 < kRow && i%8 > kCol && chessboard[kRow-j][kCol+j] != null) {
							routeFree = false;
						}
						// Check south-east
						if (i/8 > kRow && i%8 > kCol && chessboard[kRow+j][kCol+j] != null) {
							routeFree = false;
						}
					}
					if (routeFree) {
						return true;
					}
				}

				// is Rook or Queen threatening horizontally
				if (kRow == i/8 && pieceIsEnemy && (chessboard[i/8][i%8] instanceof Rook || chessboard[i/8][i%8] instanceof Queen)) {
					boolean routeFree = true;
					for (int j = 1; j < Math.abs(kCol - (i%8)); j++) {
						if (kCol < i%8) {
							if (chessboard[kRow][kCol+j] != null) {
								routeFree = false;
							}
						} else {
							if (chessboard[kRow][kCol-j] != null) {
								routeFree = false;
							}
						}	
					}
					if (routeFree) {
						return true;
					}
				}

				// is Rook or Queen threatening vertically
				if (kCol == i%8 && pieceIsEnemy &&  (chessboard[i/8][i%8] instanceof Rook || chessboard[i/8][i%8] instanceof Queen)) {
					boolean routeFree = true;
					for (int j = 1; j < Math.abs(kRow - (i/8)); j++) {
						if (kRow < i/8) {
							if (chessboard[kRow+j][kCol] != null) {
								routeFree = false;
							}
						} else {
							if (chessboard[kRow-j][kCol] != null) {
								routeFree = false;
							}
						}
					}
					if (routeFree) {
						return true;
					}
				}

				// is opponent's King threatening
				if (pieceIsEnemy && chessboard[i/8][i%8] instanceof King && (Math.abs((i/8) - kRow) <= 1 && Math.abs((i%8) - kCol) <= 1)) {
					return true;
				}
			}
				
		}
		return false;
	}
	 /**
	 * 
	 * @return Integer: 1 = checkmate against white, 0 = checkmate against black, -1 = no checkmate
	 */
	public int isItCheckMate() {
		// check from white's POW
		ArrayList<Move> moves = whiteKing.getPossibleMoves(this);
		if (this.isItCheck(true) && (moves == null || moves.isEmpty())) {
			return 1;
		}
		// check from black's POW
		moves = blackKing.getPossibleMoves(this);
		if (this.isItCheck(false) && moves.isEmpty()) {
			return 0;
		}
		return -1;
	}
	
	public int evaluate() {
		int points = 0;
		int kingPoints = 200;
		int queenPoints = 9;
		int rookPoints = 5;
		int knightPoints = 3;
		int bishopPoints = 3;
		int pawnPoints = 1;
		boolean pieceIsWhite = false;
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] != null) {
				pieceIsWhite = chessboard[i/8][i%8].amIWhite();
			}
			if (chessboard[i/8][i%8] instanceof King && !pieceIsWhite) points += kingPoints;
			if (chessboard[i/8][i%8] instanceof Queen && !pieceIsWhite) points += queenPoints;
			if (chessboard[i/8][i%8] instanceof Rook && !pieceIsWhite) points += rookPoints;
			if (chessboard[i/8][i%8] instanceof Knight && !pieceIsWhite) points += knightPoints;
			if (chessboard[i/8][i%8] instanceof Bishop && !pieceIsWhite) points += bishopPoints;
			if (chessboard[i/8][i%8] instanceof Pawn && !pieceIsWhite) points += pawnPoints;
			if (chessboard[i/8][i%8] instanceof King && pieceIsWhite) points -= kingPoints;
			if (chessboard[i/8][i%8] instanceof Queen && pieceIsWhite) points -= queenPoints;
			if (chessboard[i/8][i%8] instanceof Rook && pieceIsWhite) points -= rookPoints;
			if (chessboard[i/8][i%8] instanceof Knight && pieceIsWhite) points -= knightPoints;
			if (chessboard[i/8][i%8] instanceof Bishop && pieceIsWhite) points -= bishopPoints;
			if (chessboard[i/8][i%8] instanceof Pawn && pieceIsWhite) points -= pawnPoints;
		}
		int checkMate = this.isItCheckMate();
		if (this.isItCheck(true)) {
			points += 1000;
			if (checkMate == 1) {
				points = Integer.MAX_VALUE;
			}
		}
		if (this.isItCheck(false)) {
			points -= 1000;
			if (checkMate == 0) {
				points = Integer.MIN_VALUE;
			}
		}
		return points;
	}
	
}
