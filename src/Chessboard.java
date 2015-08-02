
import java.util.Arrays;

public class Chessboard {
	/*
		Rook (R, r) = torni
		Knight (N, n) = hevonen
		Bishop (B, b) = lähetti
		Queen (Q, q) = kuningatar
		King (K, k) = kuningas
		Pawn (P, p) = sotilas
	
		Iso kirjain = valkoinen
		Pieni kirjain = musta
	
		Esim.
	
		R = valkoinen torni
		n = musta hevonen
	*/	
	
	private static long r = 0L, n = 0L, b = 0L, q = 0L, k = 0L, p = 0L;
	private static long R = 0L, N = 0L, B = 0L, Q = 0L, K = 0L, P = 0L;
	private static char[][] chessboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};;
	
	public static void arrayToBitBoards(char[][] chessboard) {
		String binary;
		for (int i = 0; i < 64; i++) {
			binary = "0000000000000000000000000000000000000000000000000000000000000000";
			binary = binary.substring(i+1) + "1" + binary.substring(0, i);
			switch(chessboard[i/8][i%8]) {
				case 'r': r += convertStringToBitboard(binary);
					break;
				case 'n': n += convertStringToBitboard(binary);
					break;
				case 'b': b += convertStringToBitboard(binary);
					break;
				case 'q': q += convertStringToBitboard(binary);
					break;
				case 'k': k += convertStringToBitboard(binary);
					break;
				case 'p': p += convertStringToBitboard(binary);
					break;
				case 'R': R += convertStringToBitboard(binary);
					break;
				case 'N': N += convertStringToBitboard(binary);
					break;
				case 'B': B += convertStringToBitboard(binary);
					break;
				case 'Q': Q += convertStringToBitboard(binary);
					break;
				case 'K': K += convertStringToBitboard(binary);
					break;
				case 'P': P += convertStringToBitboard(binary);
					break;
			}
		}
	}
	
	public static char[][] bitboardsToArray() {
        char[][] newboard = new char[8][8];
        for (int i=0; i < 64; i++) {
            newboard[i/8][i%8] = ' ';
        }
        for (int i=0;i<64;i++) {
            if (((P>>i)&1)==1) {newboard[i/8][i%8] = 'P';}
            if (((N>>i)&1)==1) {newboard[i/8][i%8] = 'N';}
            if (((B>>i)&1)==1) {newboard[i/8][i%8] = 'B';}
            if (((R>>i)&1)==1) {newboard[i/8][i%8] = 'R';}
            if (((Q>>i)&1)==1) {newboard[i/8][i%8] = 'Q';}
            if (((K>>i)&1)==1) {newboard[i/8][i%8] = 'K';}
            if (((p>>i)&1)==1) {newboard[i/8][i%8] = 'p';}
            if (((n>>i)&1)==1) {newboard[i/8][i%8] = 'n';}
            if (((b>>i)&1)==1) {newboard[i/8][i%8] = 'b';}
            if (((r>>i)&1)==1) {newboard[i/8][i%8] = 'r';}
            if (((q>>i)&1)==1) {newboard[i/8][i%8] = 'q';}
            if (((k>>i)&1)==1) {newboard[i/8][i%8] = 'k';}
        }
		return newboard;
    }
	
	public static long convertStringToBitboard(String binary) {
		if (binary.charAt(0) == '0') { // is the number positive
			return Long.parseLong(binary, 2);
		} else {
			return Long.parseLong("1" + binary.substring(2), 2) * 2;
		}
	}
	
	public static void draw(char[][] chessboard) {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 33; j++) {
				// Draw chess board's top and bottom edge
				if (i == 0 || i == 16) {
					if (j == 0) {
						System.out.print("  ");
					}
					System.out.print("-");
				} else {
					// Draw the horizontal lines between chess board's row of squares
					if (i%2 == 0) {
						if (j == 0 || j == 32) {
							if (j == 0) {
								System.out.print("  ");
							}
							System.out.print("|");
						} else {
							System.out.print("-");
						}
					// Draw one row of chess board squares
					} else {
						// Draw the lines between squaress
						if (j%4 == 0) {
							if (j == 0) {
								// (i-1)/2 + 1 calculates board's rownumber from i
								System.out.print((i-1)/2 + 1 + " |");
							} else {
								System.out.print("|");
							}
						// Draw the contents of the square itself
						} else {
							if ((j-2)%4 == 0) {
								/*
								    i-1/2 equals chessboard's equivalent rownumber
								    j-2/4 equals chessboard's equivalent colnumber
								*/
								System.out.print(chessboard[(i-1)/2][(j-2)/4]);
							} else {
								System.out.print(" ");
							}							
						}
					}
				}
			}
			if (i == 1) {
				System.out.print(" musta");
			}
			if (i == 15) {
				System.out.print(" VALKOINEN");
			}
			System.out.println("");
		}
		System.out.println("    A   B   C   D   E   F   G   H");
	}
	
	public static boolean movePiece(String command) {
		boolean moveResult;
		if (command.length() != 4) {
			return false;
		}
		int startCol, startRow, endCol, endRow;
		startCol = (int)command.charAt(0) - 65;
		startRow = 8 - Integer.parseInt(command.substring(1, 2));
		endCol = (int)command.charAt(2) - 65;
		endRow = 8 - Integer.parseInt(command.substring(3));
		boolean error = startCol < 0 || startCol > 7 ||
				startRow < 0 || startRow > 7 ||
				endCol < 0 || endCol > 7 ||
				endRow < 0 || endRow > 7;
		if (error) {
			return error;
		}
		switch(chessboard[startRow][startCol]) {
			case 'P': return movePawn(startRow, startCol, endRow, endCol);
			case 'R': return moveRook(startRow, startCol, endRow, endCol);
			case 'N': return moveKnight(startRow, startCol, endRow, endCol);
			case 'B': return moveBishop(startRow, startCol, endRow, endCol);
			case 'Q': return moveQueen(startRow, startCol, endRow, endCol);
			case 'K': return moveKing(startRow, startCol, endRow, endCol);
			default: return false;
		}
	}
	
	public static boolean movePawn(int startRow, int startCol, int endRow, int endCol) {
		if (startRow == 6) {
			if (endRow >= 4) {
				
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static boolean moveRook(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static boolean moveKnight(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static boolean moveBishop(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static boolean moveQueen(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static boolean moveKing(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("Ennen konversiota:");
		draw(chessboard);
		arrayToBitBoards(chessboard);
		char[][] uusi = bitboardsToArray();
		System.out.println("\nJa konversion jälkeen:");
		draw(uusi);
	}
	
}
