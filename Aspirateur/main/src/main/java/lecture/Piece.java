package lecture;

/**
 * Classe permettant de représenter la Pièce à nettoyer.
 * @author Matthieu
 *
 */
public class Piece {

	private int longueur;
	private int largeur;
	private int nbCaseVide;
	private char[][] laPiece;
	private int xDepart;
	private int yDepart;
	
	
	
	public Piece(int longueur, int largeur) {
		super();
		this.longueur = longueur;
		this.largeur = largeur;
		this.laPiece = new char[largeur][longueur];
	}
	
	public int getLongueur() {
		return longueur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public char[][] getLaPiece() {
		return laPiece;
	}
	public void setLaPiece(char[][] laPiece) {
		this.laPiece = laPiece;
	}
	public int getNbCaseVide() {
		return nbCaseVide;
	}
	public void setNbCaseVide(int nbCaseVide) {
		this.nbCaseVide = nbCaseVide;
	}

	public int getxDepart() {
		return xDepart;
	}

	public void setxDepart(int xDepart) {
		this.xDepart = xDepart;
	}

	public int getyDepart() {
		return yDepart;
	}

	public void setyDepart(int yDepart) {
		this.yDepart = yDepart;
	}
	
	
	
}
