package traitement;

import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import lecture.Lecture;
import lecture.Piece;

public class Traitement {

	public static void main(String[] args) {

		// Récupération du fichier d'entrée (la pièce)
		String path = null;
		FileFilter filtre = new FileNameExtensionFilter("Texte", "txt");
		JFileChooser selecteur = new JFileChooser();
		selecteur.setFileFilter(filtre);
		selecteur.setDialogTitle("Importer le fichier définissant la pièce");
		int result = selecteur.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			path = selecteur.getSelectedFile() + "";
		}
		
		Piece laPiece = Lecture.LectureFichier(path);
		// Si la pièce existe et n'est pas vide
		if (null != laPiece && laPiece.getLaPiece().length > 0) {
			
			// Définition du point de départ
			Case depart = setDepart(laPiece);
			laPiece.setxDepart(depart.getX());
			laPiece.setyDepart(depart.getY());
			
			// Si le point de départ est correctement défini, on parcours la pièce
			if(laPiece.getxDepart()!= 0 && laPiece.getyDepart()!=0){
				parcours(laPiece);
			}else{
				System.out.println("Impossible de définir un point de départ");
			}
			

		} else {
			System.out.println("La pièce n'est pas nettoyable");
		}

	}

	/**
	 * Méthode permettant de définir le départ aléatoirement dans la pièce
	 * @param laPiece
	 * @return Case, la case de départ, elle contient [0],[0] si elle n'a pas pu être définie
	 */
	public static Case setDepart(Piece laPiece) {
		
		int x = 0;
		int y = 0;
		Case caseDepart = new Case(x, y);
		
		//Si la pièce comporte plus d'une case
		if (laPiece.getLargeur() > 1 && laPiece.getLongueur() > 1) {
			Random r = new Random();
			x = 1 + r.nextInt(laPiece.getLargeur() - 1);
			x = x == laPiece.getLargeur() - 1 ? x = x - 1 : x;
			y = 1 + r.nextInt(laPiece.getLongueur() - 1);
			y = y == laPiece.getLongueur() - 1 ? y = y - 1 : y;

			// Gestion d'une "cloison"
			while (laPiece.getLaPiece()[x][y] == 'M') {
				if (x > 1) {
					x--;
				} else {
					// Si x = 1, on modifie y
					if (y > 1) {
						y--;
					} else {
						// Si x=1 et y=1
						x++;
					}
				}
			}
			caseDepart.setX(x);
			caseDepart.setY(y);
		}
		//Si [0],[0] message d'erreur
		return caseDepart;
	}
	
	/**
	 * Méthode permettant de parcourir toutes les cases de la pièce
	 * Le parcours réalisé est ecrit dans la sortie std
	 * @param laPiece
	 */
	private static void parcours(final Piece laPiece){
		
		//Pile pour stocker les cases déjà visitées
		Stack<Case> casesVisitees = new Stack<>();
		
		int nbCaseRestante = laPiece.getNbCaseVide()-1;
		int x = laPiece.getxDepart();
		int y = laPiece.getyDepart();
		Case previousCase = new Case(laPiece.getxDepart(), laPiece.getyDepart());
		
		if(laPiece.getLaPiece()[x][y] != 'M'){
			//Affichage du point de départ 
			System.out.println("["+ (x+1) + "," + (y+1) + "]");
			while (nbCaseRestante > 0){
				
				int currentX = x;
				int currentY = y;
				
				//On commence par vérifier la case au dessus
				if(laPiece.getLaPiece()[x-1][y] != 'M' && x-1 != previousCase.getX() && !casesVisitees.contains(new Case(x-1,y))){
					previousCase.setX(x);
					previousCase.setY(y);
					casesVisitees.push(previousCase);
					x = x-1;
				//Puis la case à gauche
				}else if(laPiece.getLaPiece()[x][y-1] != 'M' && y-1 != previousCase.getY() && !casesVisitees.contains(new Case(x,y-1))){
					previousCase.setX(x);
					previousCase.setY(y);
					casesVisitees.push(previousCase);
					y = y-1;
				//Puis la case du dessous
				}else if(laPiece.getLaPiece()[x+1][y] != 'M' && x+1 != previousCase.getX() && !casesVisitees.contains(new Case(x+1,y))){
					previousCase.setX(x);
					previousCase.setY(y);
					casesVisitees.push(previousCase);
					x = x+1;
				//Puis la case de droite
				}else if(laPiece.getLaPiece()[x][y+1] != 'M' && y+1 != previousCase.getY() && !casesVisitees.contains(new Case(x,y+1))){
					previousCase.setX(x);
					previousCase.setY(y);
					casesVisitees.push(previousCase);
					y = y+1;
				}
				
				//Si on a pas changé de case
				if((x == currentX && y == currentY)){
					//On prend la dernière case et on regarde si aucune case non visitée à coté.
					for (Iterator<Case> iterator = casesVisitees.iterator(); iterator.hasNext();) {
						Case currentCase = casesVisitees.pop();
						x = currentCase.getX();
						y = currentCase.getY();
						System.out.println("["+ (x+1) + "," + (y+1) + "]"); 
						//On commence par vérifier la case au dessus
						if(laPiece.getLaPiece()[x-1][y] != 'M' && !casesVisitees.contains(new Case(x-1,y)) && x-1 != currentX){
							x = x-1;
							casesVisitees.push(currentCase);
							break;
						//Puis la case à gauche
						}else if(laPiece.getLaPiece()[x][y-1] != 'M' && !casesVisitees.contains(new Case(x,y-1)) && y-1 != currentY){
							y = y-1;
							casesVisitees.push(currentCase);
							break;
						//Puis la case du dessous
						}else if(laPiece.getLaPiece()[x+1][y] != 'M' && !casesVisitees.contains(new Case(x+1,y)) && x+1 != currentX){
							x = x+1;
							break;
						//Puis la case de droite
						}else if(laPiece.getLaPiece()[x][y+1] != 'M' && !casesVisitees.contains(new Case(x,y+1)) && y+1 != currentY){
							y = y+1;
							casesVisitees.push(currentCase);
							break;
						}
						
						//Si on est ici on a encore dépilé, on mets la valeur dans la pile mais pas en dernier pour
						//Ne pas faire de boucle infinie
						casesVisitees.insertElementAt(currentCase, 0);
						
					}
				}

				nbCaseRestante = nbCaseRestante -1;
				System.out.println("["+ (x+1) + "," + (y+1) + "]");
				previousCase = new Case(casesVisitees.peek().getX(), casesVisitees.peek().getY());
				
				
			}
		}
		
	}

}
