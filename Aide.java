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
		System.out.println("Aide : Différents cas d'éxecution\n"
				+ "Si il n'y a rien : affiche l'aide\n"
				+ "-d 'file' : Affiche les différents fichiers Vcf et Ics à partir du fichier 'file'\n"
				+ "-i : Plusieurs cas :\n"
				+ "\t'.vcf' / '.ics' : Affiche le contenu du fichier\n"
				+ "\t'.vcf' / '.ics' -o 'file.ser' : Sauvegarde le contenu du fichier dans file.ser\n"
				+ "\t'.vcf' / '.ics' -h 'file.html' : Génère un fragment html à partir du fichier\n"
				+ "\t'.vcf' / '.ics' -h 'file.html' -p : Génère une page html à partir du fichier\n"
				+ "Fin\n");
	}
}
