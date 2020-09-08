package projetJavaS3;

/**
 * 
 * @author thief
 *
 */
public class Aide {
	/**
	 * 
	 */
	public Aide () {}
	
	/**
	 * Display help
	 */
	public void affiche() {
		System.out.println("Aide : Diff�rents cas d'�xecution\n"
				+ "Si il n'y a rien : affiche l'aide\n"
				+ "-d 'file' : Affiche les diff�rents fichiers Vcf et Ics � partir du fichier 'file'\n"
				+ "-i : Plusieurs cas :\n"
				+ "\t'.vcf' / '.ics' : Affiche le contenu du fichier\n"
				+ "\t'.vcf' / '.ics' -o 'file.ser' : Sauvegarde le contenu du fichier dans file.ser\n"
				+ "\t'.vcf' / '.ics' -h 'file.html' : G�n�re un fragment html � partir du fichier\n"
				+ "\t'.vcf' / '.ics' -h 'file.html' -p : G�n�re une page html � partir du fichier\n"
				+ "Fin\n");
	}
}
