
import UI.UserInterface;
import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
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
		Chessboard lauta1 = new Chessboard();
		Piece[] lauta1nappulat = lauta1.getPieces();
		System.out.println("Laudan 1 Sotilas 1 ennen siirtoa: " + lauta1nappulat[0].getPosition());
		System.out.println("Siirretään laudan 1 sotilasta 1: " + lauta1nappulat[0].move(lauta1, 48, 40));
		System.out.println("Lauta1 siirron jälkeen:");
		UserInterface.draw(lauta1.getBoard());
		System.out.println("Laudan 1 Sotilas 1 siirron jälkeen: " + lauta1nappulat[0]);
		
		System.out.println("\n=============================\n");
		
		Chessboard lauta2 = new Chessboard();
		Piece[] lauta2nappulat = lauta2.getPieces();
		System.out.println("Laudan 2 uudet nappulat " + lauta2.getPieces() + " ja taulukko " + lauta2nappulat);
		System.out.println("Laudan 2 Sotilas 1 ennen kloonaamista: " + lauta2nappulat[0]);
		lauta2.setPieces(lauta1.clonePieces());
		lauta2nappulat = lauta2.getPieces();
		System.out.println("Laudan 2 nappulat kloonaamisen jälkeen: " + lauta2.getPieces());
		System.out.println("Laudan 2 Sotilas 1 kloonaamisen jälkeen: " + lauta2nappulat[0]);	
		Piece[] uusi = new Piece[16];
		lauta2.setPieces(uusi);
		lauta2nappulat = lauta2.getPieces();
		System.out.println("Laudan 2 tuliterät nappulat: " + lauta2.getPieces());
		System.out.println("Laudan 2 Sotilas 1 tuliterä: " + lauta2nappulat[0]);	
		
	}
	
}
