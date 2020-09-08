package projetJavaS3;

public class CreneauHoraireException extends Exception {
	
	private static final long serialVersionUID = 2886485891967963245L;

	public CreneauHoraireException () {
	System.out.println("Horaire invalide !");	
	}
	
	public CreneauHoraireException (int h) {
		System.out.println("L'horaire (heure / min) " + h + " n'est pas un horaire valide !");
	}
	
	public CreneauHoraireException (String s) {
		System.out.println(s);
	}
	
}
