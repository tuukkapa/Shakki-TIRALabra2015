
package Chessboard;

import AI.Move;
import Chessboard.pieces.Bishop;
import Chessboard.pieces.King;
import Chessboard.pieces.Knight;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Queen;
import Chessboard.pieces.Rook;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

public class Chessboard {
	
	private Piece[][] chessboard;
	private ArrayList<Piece> blackPieces, whitePieces;
	private int whiteKingPosition, blackKingPosition;
	
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
	
	public void setBoard(char[][] newboard) {
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
				chessboard[i/8][i%8] = new King(false, i);
				blackPieces.add(chessboard[i/8][i%8]);
				blackKingPosition = i;
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
				chessboard[i/8][i%8] = new King(true, i);
				whitePieces.add(chessboard[i/8][i%8]);
				whiteKingPosition = i;
			} else {
				chessboard[i/8][i%8] = null;
			}
		}
	}
	
	public ArrayList getPieces(boolean white) {
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
	
	private void updateKingPosition(boolean colour) {
		char king = colour ? 'K' : 'k';
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8].getSign() == king) {
				if (colour) {
					whiteKingPosition = i;
				} else {
					blackKingPosition = i;
				}
				break;
			}
		}
	}
	
	public boolean makeMove(Move move) {
		int start = move.getStart();
		int end = move.getEnd();
		Piece piece = chessboard[start/8][start%8];
		if (piece == null) {
			System.out.println("Virhe: siirrettävä nappula ei ollut oletetuissa koordinaateissa"
					+ " tietokoneen siirtovuorolla.\n"
					+ "Nappulan oletettu alkukohta: " + move.getStart()
					+ " siirron loppukohta " + move.getEnd());
			return false;
		}
		if (piece.isMoveValid(this, end)) {
			if (piece.endSquareContainsEnemy(this, end)) {
				// save captured piece into Move for undoing
				Piece capturedPiece = chessboard[end/8][end%8];
				move.setCapturedPiece(capturedPiece);
				chessboard[end/8][end%8] = null;
			}
			chessboard[start/8][start%8] = null;
			chessboard[end/8][end%8] = piece;
			piece.setPosition(end);
			return true;
		}
		return false;
	}
	
	public boolean undoMove(Move move) {
		Piece capturedPiece = move.getCapturedPiece();
		Piece movedPiece = chessboard[move.getEnd()/8][move.getEnd()%8];
		if (movedPiece == null) {
			return false;
		}
		chessboard[move.getStart()/8][move.getStart()%8] = movedPiece;
		chessboard[move.getEnd()/8][move.getEnd()%8] = capturedPiece;
		return true;
	}
	
	public boolean isItCheck(boolean checkedIsWhite) {
		int kRow, kCol;
		
		kRow = checkedIsWhite ? whiteKingPosition/8 : blackKingPosition/8;
		kCol = checkedIsWhite ? whiteKingPosition%8 : blackKingPosition%8;
		
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
	
	public boolean wouldItBeCheck(Piece piece, int end) {
		Move move = new Move(0, piece.getPosition(), end);
		this.makeMove(move);
		boolean checkSituation = this.isItCheck(piece.amIWhite());
		this.undoMove(move);
		return checkSituation;
	}
	
	public boolean isItCheckMate(boolean checkedIsWhite) {
		Piece king = checkedIsWhite ? chessboard[whiteKingPosition/8][whiteKingPosition%8] : chessboard[blackKingPosition/8][blackKingPosition%8];
		if (king == null) {
			return false;
		}
		ArrayList<Move> moves;
		moves = king.getPossibleMoves(this);
		return this.isItCheck(checkedIsWhite) && moves.isEmpty();
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
		if (this.isItCheck(true)) {
			points += 1000;
			if (this.isItCheckMate(true)) {
				points = Integer.MAX_VALUE;
			}
		}
		if (this.isItCheck(false)) {
			points -= 1000;
			if (this.isItCheckMate(false)) {
				points = Integer.MIN_VALUE;
			}
		}
		return points;
	}
	
}
