
import User.UserMovement;
import AI.Movement;
import UI.UserInterface;
import Chessboard.Chessboard;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuukka
 */
public class Test {
	
	public static void main(String[] args) throws CloneNotSupportedException {	
		Chessboard chessboard = new Chessboard();
		Pawn pawn = (Pawn) chessboard.getPiece(48);
		ArrayList<Movement> testi = pawn.getPossibleMovements(chessboard);
		System.out.println(UserMovement.movePiece("a2a3", chessboard));
		UserInterface.draw(chessboard.getBoard());
	}
	
}
