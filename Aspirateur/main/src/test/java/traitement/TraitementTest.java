package traitement;

import static org.junit.Assert.*;

import org.junit.Test;

import lecture.Piece;

public class TraitementTest {

	@Test
	public void setDepartPiece2Cases() {
		char[][] tab = new char[1][2];
		tab[0][0] = 'M';
		tab[0][1] = 'M';
		Piece mPiece = new Piece(tab.length - 1, tab.length - 1);
		mPiece.setLaPiece(tab);
		Case caseDepart = Traitement.setDepart(mPiece);
		assertEquals(caseDepart, new Case(0, 0));
	}

	@Test
	public void setDepartPieceQueMurs() {
		char[][] tab = new char[2][2];
		tab[0][0] = 'M';
		tab[0][1] = 'M';
		tab[1][0] = 'M';
		tab[1][1] = 'M';
		Piece mPiece = new Piece(tab.length - 1, tab.length - 1);
		Case caseDepart = Traitement.setDepart(mPiece);
		assertEquals(caseDepart, new Case(0, 0));
	}

	@Test(expected = NullPointerException.class)
	public void setDepartPieceNull() {
		Piece maPiece = null;
		Traitement.setDepart(maPiece);
	}
	
}
