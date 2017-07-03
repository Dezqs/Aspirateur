package lecture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lecture {
	
	public static Piece LectureFichier(String pathFile){
		
		Piece pieceToReturn = null;
		try {
			File f = new File(pathFile);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				// Lecture du fichier et cr�ation de la Pi�ce
				int nbCol = 0;
				int nbLig = 0;
				ArrayList<String> lignes = new ArrayList<String>();

				String ligne = br.readLine();
				while (ligne != null) {
					if (nbLig == 0) {
						nbCol = ligne.length();
					}
					lignes.add(ligne);
					ligne = br.readLine();
					nbLig++;
				}

				// On remplit le tableau repr�sentant la pi�ce :
				int nbCaseAParcourir = 0;
				char[][] tabPiece = new char[nbLig][nbCol];
				int cptLigne = 0;
				for (String string : lignes) {
					int cptCol = 0;
					for (char aChar : string.toCharArray()) {
						if (aChar == ' ') {
							nbCaseAParcourir++;
						}
						tabPiece[cptLigne][cptCol] = aChar;
						cptCol++;
					}
					cptLigne++;
				}

				// Cr�ation de la Pi�ce
				pieceToReturn = new Piece(nbCol, nbLig);
				pieceToReturn.setLaPiece(tabPiece);
				pieceToReturn.setNbCaseVide(nbCaseAParcourir);

				br.close();
				fr.close();
				System.out.println("Nombre de case � parcourir : " + pieceToReturn.getNbCaseVide() );
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas �t� trouv�");
		} catch (NullPointerException exception){
			System.out.println("Aucun fichier de s�lectionn�");
		}
		return pieceToReturn;
	}
	
}
