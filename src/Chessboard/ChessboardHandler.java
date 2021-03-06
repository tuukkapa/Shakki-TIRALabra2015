
package Chessboard;

import Chessboard.pieces.*;

 /**
  * This class enforces the chess rules to the Chessboard-object. The chessboard
  * can be altered only through this class.
  * 
  * @author Tuukka Paukkunen
  */
public class ChessboardHandler {
	
	/**
	 * Method receives move commands from human user, parses those into coordinates,
	 * and relays the command to the chessboard, and outputs "true" boolean value.
	 * @param command String, move command received from the human user, such as "c4e5".
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return True if command is valid and successful, false otherwise.
	 */
	public static boolean userCommandParser(String command, Chessboard chessboard) {
		command = command.toUpperCase();
		if (command.length() != 4) {
			return false;
		}
		int start = ((8 - Integer.parseInt(command.substring(1, 2))) * 8) + (int)command.charAt(0) - 65;
		int end = ((8 - Integer.parseInt(command.substring(3))) * 8) + (int)command.charAt(2) - 65;
		Move move = new Move(start, end);
		// Are the coordinates inside the board
		if (0 > start || start > 63 || 0 > end || end > 63) {
			return false;
		}
		// Is a piece found at the starting coordinates
		Piece piece = chessboard.getSquareContents(start);
		if (piece == null) {
			return false;
		}
		// Is the piece player's own piece (i.e. white) and is the move successful
		if (piece.amIWhite() && piece.isMoveValid(chessboard, end)) {
			makeMove(chessboard, move);
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Makes one whole move at the board.
	 * @param chessboard Chessboard-object, contains two dimensional piece-array
	 * and two lists of pieces (black and white).
	 * @param move Move-object, consisting of start and end coordinates.
	 * @return Move-object.
	 */
	public static boolean makeMove(Chessboard chessboard, Move move) {
		Piece piece = chessboard.getSquareContents(move.getStart());
		if (piece == null) {
			return false;
		}
		move = addRookMoveIfCastling(chessboard, move);
		if (move.getCastlingRookStart() != -1 && move.getCastlingRookEnd() != -1) {
			chessboard.updatePiecePosition(move.getCastlingRookStart(), move.getCastlingRookEnd());
		}
		if (piece.endSquareContainsEnemy(chessboard, move.getEnd())) {
				Piece capturedPiece = chessboard.getSquareContents(move.getEnd());
				move.setCapturedPiece(capturedPiece);
		}
		chessboard.updatePiecePosition(move.getStart(), move.getEnd());
		promotePawnToQueen(chessboard, piece, move);
		return true;
	}
	
	/**
	 * Promotes pawn to queen if pawn is moved to it's end row.
	 * This method already trusts that the move command is valid.
	 * If the piece isn't pawn or the pawn isn't moved to the end row,
	 * method does nothing.
	 * @param piece Piece which was moved.
	 * @param move Move command of the piece, which was just made.
	 */
	private static void promotePawnToQueen(Chessboard chessboard, Piece piece, Move move) {
		int endRow = piece.amIWhite() ? 0 : 7; // the pawn's end row according to it's colour
		if (piece instanceof Pawn && move.getEnd()/8 == endRow) {
			chessboard.switchPiece(piece, new Queen(piece.amIWhite(), move.getEnd()));
		}
	}
	
	/**
	 * Moves one piece at the board and saves the possibly captured piece to the Move-object.
	 * This is used only with wouldItBeCheck-method.
	 * This method trusts, that the move is already checked to be a valid chess move.
	 * @param move Move-object, consisting of starting and ending coordinates and possibly a captured Piece.
	 * @return Boolean, true if command is successful, false otherwise.
	 */
	private static boolean movePiece(Chessboard chessboard, Move move) {
		Piece piece = chessboard.getSquareContents(move.getStart());
		if (piece.endSquareContainsEnemy(chessboard, move.getEnd())) {
				Piece capturedPiece = chessboard.getSquareContents(move.getEnd());
				move.setCapturedPiece(capturedPiece);
		}
		chessboard.updatePiecePosition(move.getStart(), move.getEnd());
		return true;
	}
	
	/**
	 * Undoes the move given as parameter.
	 * This is used only with wouldItBeCheck-method.
	 * @param move Move-object, to be undone.
	 * @return Boolean, true if command is successful, false otherwise.
	 */
	private static boolean undoMove(Chessboard chessboard, Move move) {
		Piece capturedPiece = move.getCapturedPiece();
		chessboard.updatePiecePosition(move.getEnd(), move.getStart());
		if (capturedPiece != null) {
			chessboard.updatePiecePosition(capturedPiece, move.getEnd());
		}
		return true;
	}
	
	/**
	 * Returns true, if a move would cause check situation for moving an own piece.
	 * @param chessboard Chessboard-object, contains two dimensional piece-array
	 * and two lists of pieces (black and white).
	 * @param piece Piece-object to be moved.
	 * @param end Integer, ending coordinates (0 = top left, 63 = bottom right).
	 * @return Boolean, true if command is successful, false otherwise.
	 */
	public static boolean wouldItBeCheck(Chessboard chessboard, Piece piece, int end) {
		int start = piece.getPosition();
		boolean movingStatus = piece.getHasMoved();
		Move move = new Move(start, end);
		move = addRookMoveIfCastling(chessboard, move);
		movePiece(chessboard, move);
		
		boolean checkSituation = isItCheck(chessboard, piece.amIWhite());
		
		undoMove(chessboard, move);
		piece.setHasMoved(movingStatus);
		
		return checkSituation;
	}
	
	/**
	 * Returns true, if game situation is check against the player colour given as parameter.
	 * @param chessboard Chessboard-object, contains two dimensional piece-array
	 * and two lists of pieces (black and white).
	 * @param checkedIsWhite True, if player is white, false otherwise.
	 * @return True if checked is white, false otherwise.
	 */
	public static boolean isItCheck(Chessboard chessboard, boolean checkedIsWhite) {
		int kingPosition = chessboard.getKingPosition(checkedIsWhite);

		int kRow = kingPosition/8;
		int kCol = kingPosition%8;
		
		for (int i = 0; i < chessboard.getListSize(!checkedIsWhite); i++) {
			Piece piece = chessboard.getFromList(!checkedIsWhite, i);
			if (piece.getPosition() == -1) {
				continue;
			}
			int pRow = piece.getPosition()/8;
			int pCol = piece.getPosition()%8;
			if (piece instanceof Pawn) {
				boolean pieceIsPastKing = checkedIsWhite ? kRow < pRow : kRow > pRow;
				if (!pieceIsPastKing && Math.abs(kRow - pRow) == 1 && Math.abs(kCol - pCol) == 1) {
					return true;
				}
			} else if (piece instanceof Knight) {
				if ((Math.abs(kRow - pRow) == 2 && Math.abs(kCol - pCol) == 1) || 
						(Math.abs(kRow - pRow) == 1 && Math.abs(kCol - pCol) == 2)) {
					return true;
				}
			} else if (piece instanceof Bishop) {
				if (Math.abs(kRow - pRow) == Math.abs(kCol - pCol)) {
					boolean routeFree = true;
					for (int j = 1; j < Math.abs(kRow - pRow); j++) {
						if (pRow < kRow && pCol < kCol && chessboard.getSquareContents(kRow-j, kCol-j) != null) {
							routeFree = false;
						}
						// Check south-west
						if (pRow > kRow && pCol < kCol && chessboard.getSquareContents(kRow+j, kCol-j) != null) {
							routeFree = false;
						}
						// Check north-east
						if (pRow < kRow && pCol > kCol && chessboard.getSquareContents(kRow-j, kCol+j) != null) {
							routeFree = false;
						}
						// Check south-east
						if (pRow > kRow && pCol > kCol && chessboard.getSquareContents(kRow+j, kCol+j) != null) {
							routeFree = false;
						}
					}
					if (routeFree) {
						return true;
					}
				}
			} else if (piece instanceof Rook) {
				boolean routeFree = true;
				if (kRow == pRow) {
					for (int j = 1; j < Math.abs(kCol - pCol); j++) {
						if (kCol < pCol) {
							if (chessboard.getSquareContents(kRow, kCol+j) != null) {
								routeFree = false;
							}
						} else {
							if (chessboard.getSquareContents(kRow, kCol-j) != null) {
								routeFree = false;
							}
						}	
					}
					if (routeFree) {
						return true;
					}
				} else	if (kCol == pCol) {
					routeFree = true;
					for (int j = 1; j < Math.abs(kRow - pRow); j++) {
						if (kRow < pRow) {
							if (chessboard.getSquareContents(kRow+j, kCol) != null) {
								routeFree = false;
							}
						} else {
							if (chessboard.getSquareContents(kRow-j, kCol) != null) {
								routeFree = false;
							}
						}	
					}
					if (routeFree) {
						return true;
					}
				}
			} else if (piece instanceof Queen) {
				boolean routeFree = true;
				if (Math.abs(kRow - pRow) == Math.abs(kCol - pCol)) {
					for (int j = 1; j < Math.abs(kRow - pRow); j++) {
						if (pRow < kRow && pCol < kCol && chessboard.getSquareContents(kRow-j, kCol-j) != null) {
							routeFree = false;
						}
						// Check south-west
						if (pRow > kRow && pCol < kCol && chessboard.getSquareContents(kRow+j, kCol-j) != null) {
							routeFree = false;
						}
						// Check north-east
						if (pRow < kRow && pCol > kCol && chessboard.getSquareContents(kRow-j, kCol+j) != null) {
							routeFree = false;
						}
						// Check south-east
						if (pRow > kRow && pCol > kCol && chessboard.getSquareContents(kRow+j, kCol+j) != null) {
							routeFree = false;
						}
					}
					if (routeFree) {
						return true;
					}
				} else if (kRow == pRow) {
					routeFree = true;
					for (int j = 1; j < Math.abs(kCol - pCol); j++) {
						if (kCol < pCol) {
							if (chessboard.getSquareContents(kRow, kCol+j) != null) {
								routeFree = false;
							}
						} else {
							if (chessboard.getSquareContents(kRow, kCol-j) != null) {
								routeFree = false;
							}
						}	
					}
					if (routeFree) {
						return true;
					}
				} else	if (kCol == pCol) {
					routeFree = true;
					for (int j = 1; j < Math.abs(kRow - pRow); j++) {
						if (kRow < pRow) {
							if (chessboard.getSquareContents(kRow+j, kCol) != null) {
								routeFree = false;
							}
						} else {
							if (chessboard.getSquareContents(kRow-j, kCol) != null) {
								routeFree = false;
							}
						}	
					}
					if (routeFree) {
						return true;
					}
				}
			} else if (piece instanceof King) {
				if (Math.abs(pRow - kRow) <= 1 && Math.abs(pCol - kCol) <= 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	 /**
	 * Returns an integer value telling if game situation is checkmate.
	 * @param chessboard Chessboard-object, contains two dimensional piece-array
	 * and two lists of pieces (black and white).
	 * @return Integer: 1 = checkmate against white, 0 = checkmate against black, -1 = no checkmate
	 */
	public static int isItCheckMate(Chessboard chessboard) {
		// check amount of possible moves for white pieces
		int possibleMovesForWhite = 0;
		for (int i = 0; i < chessboard.getListSize(true); i++) {
			Piece piece = chessboard.getFromList(true, i);
			if (piece.getPosition() == -1) {
				continue;
			}
			possibleMovesForWhite += piece.getPossibleMoves(chessboard).size();
		}	
		// are there any moves left for any white piece
		if (possibleMovesForWhite == 0) {
			return 1;
		}
		
		// check amount of possible moves for black pieces
		int possibleMovesForBlack = 0;
		for (int i = 0; i < chessboard.getListSize(false); i++) {
			Piece piece = chessboard.getFromList(false, i);
			if (piece.getPosition() == -1) {
				continue;
			}
			possibleMovesForBlack += piece.getPossibleMoves(chessboard).size();
		}
		if (possibleMovesForBlack == 0) {
			return 0;
		}
		return -1;
	}
	
	/**
	 * Returns true, if the move seems to be castling. It doesn't check,
	 * whether this castling is a valid one.
	 * @param chessboard Chessboard where the pieces are situated.
	 * @param move Move-object in question.
	 * @return True, if the move seems to be castling, false otherwise.
	 */
	private static boolean isItCastling(Chessboard chessboard, Move move) {
		int startCol = move.getStart() % 8;
		int endCol = move.getEnd() % 8;
		if (chessboard.getSquareContents(move.getStart()) instanceof King && Math.abs(endCol - startCol) > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private static Move addRookMoveIfCastling(Chessboard chessboard, Move move) {
		if (isItCastling(chessboard, move)) {
			if (move.getEnd() > move.getStart()) {
				move = new Move(move.getStart(), move.getEnd(), move.getStart() + 3, move.getStart() + 1);
			} else {
				move = new Move(move.getStart(), move.getEnd(), move.getStart() - 4, move.getStart() - 1);
			}
		}
		return move;
	}
	
}