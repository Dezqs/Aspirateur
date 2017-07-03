package lecture;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LectureTest {

	@Test
	public void LectureFichierNonPresent() {
		Piece maPiece = Lecture.LectureFichier("");
		assertNull(maPiece);
	}
	
	@Test
	public void LectureFichierNull(){
		Piece maPiece = Lecture.LectureFichier(null);
		assertNull(maPiece);
	}
	
	@Test
	public void LectureFichierMauvaisFormat(){
		Piece maPiece = Lecture.LectureFichier("/main/src/test/resources/tests.PNG");
		assertNull(maPiece);
	}
	
	@Test
	public void LectureFichierVide(){
		Piece maPiece = Lecture.LectureFichier("/main/src/test/resources/piece vide.txt");
		assertNull(maPiece);
	}
	
}
