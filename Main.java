package projetJavaS3;

import java.io.File;

/**
 * 
 * @author thief
 *
 */
public class Main {
	/**
	 * 
	 * @param args
	 */
	public static void main (String [] args) {
		if (args.length == 0) {
			Aide aide = new Aide();
			aide.affiche();
		} else { 
			if (args [0].contains("-d")) {
				if (args.length == 1) {
					System.out.println("Veuilez indiquez un fichier en paramètre !");
				} else {
					File search = new File (args[1]);
					listFilesForFolder(search);
				}
			} else if (args[0].contains("-i")) {
				if (args[1].contains(".ics") && args.length >= 2) {
					File edtf = new File ("C:\\Users\\thief\\eclipse-workspace\\S3\\src\\projetJavaS3\\" + args[1]);
					EmploiDuTemps edt = new EmploiDuTemps (edtf);
					if (args.length == 2) {
						edt.affichage();
					} else if (args.length >= 3 && args[2].contains("-o")) {
						if (args.length == 4) {
							File ser = new File("C:\\Users\\thief\\eclipse-workspace\\S3\\src\\projetJavaS3\\" + args[3]);
							edt.serialize(ser);
						}
					} else if (args.length >= 3 && args[2].contains("-h")) {
						if (args.length >= 4 && args[3].contains(".html")) {
							if (args.length == 5 && args[4].contains("-p")) {
								System.out.println(args[3]);
								File edthtml = new File("C:\\Users\\thief\\eclipse-workspace\\S3\\src\\projetJavaS3\\" + args[3]);
								edt.fileHtmlEDT(edthtml);
							} else {
								edt.fragHtmlCons();
							}
						}
					}
				} else if (args[1].contains(".vcf")) {
					File vcf1 = new File("C:\\Users\\thief\\eclipse-workspace\\S3\\src\\projetJavaS3\\" + args[1]);
					VirtualCard vcf = new VirtualCard(vcf1);
					if (args.length == 2) {
						System.out.println(vcf);
					} else if (args[2].contains("-o")) {
						if (args[3] != null) {
							File vser = new File("C:\\Users\\thief\\eclipse-workspace\\S3\\src\\projetJavaS3\\" + args[3]);
							vcf.serialize(vser);
						}
					} else if (args[2] == null) {
						System.out.println(vcf.toString());
					} else if (args[2].contains("-h")) {
						if (args[3].contains(".html")) {
							if (args[4].contains("-p")) {
								File vhtml = new File (args[3]);
								vcf.fileHtmlVCF(vhtml);
							} else {
								vcf.fragHtmlCons();
							}
						} else {
							System.out.println(vcf);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param folder
	 */
	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			String fileName = fileEntry.getName();
			if (fileEntry.isDirectory()) {
				System.out.print(fileEntry.getName() + "\\");
				listFilesForFolder(fileEntry);
			} else if (fileName.contains(".vcf") || fileName.contains(".ics")) {
            	System.out.println(fileName + "\t");
			}
    	}
	}
}
