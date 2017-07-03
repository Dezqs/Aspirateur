package traitement;

/**
 * Classe permettant de définir l'objet case qui est la représentation d'une case du tableau de la 
 * pièce
 * @author Matthieu
 *
 */
public class Case {

	int x;
	int y;
	
	public Case(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Case) {
			if (((Case) o).getX() == x && ((Case) o).getY() == y) {
				return true;
			}else{
				return false;
			}
		} else {
			return false;
		}

	}
	
}
