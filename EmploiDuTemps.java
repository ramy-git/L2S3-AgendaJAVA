package projetJavaS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * @author thief
 *
 */
public class EmploiDuTemps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6640641512917641987L;
	/**
	 * 
	 */
	private HashMap <String,Journee> edt;
	
	/**
	 * 
	 */
	public EmploiDuTemps () {
		edt = new HashMap <String, Journee>();
	}
	
	/**
	 * 
	 * @param f
	 */
	public EmploiDuTemps (File f) {
		edt = new HashMap <String, Journee>();
		BufferedReader br;
		String currentLine, str, tache = null, date = null;
		String [] tab;
		VirtualCard vc = null;
		int hd, mind, hf, minf;
		hd = hf = mind = minf = -1;
		try {
			br = new BufferedReader (new FileReader(f));
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.startsWith("BEGIN:VEVENT")) {
					date = null;
					tache = null;
					vc = null;	
				}
				
				if (currentLine.startsWith("DTSTART;")) {
					tab = currentLine.split(":");
					str = tab[1];
					tab = str.split("T");
					date = (tab[0].substring(6, 8) + "/" + tab[0].substring(4, 6) + "/" + tab[0].substring(0, 4));
					str = tab[1];
					hd = Integer.parseInt(str.substring(0, 2));
					mind = Integer.parseInt(str.substring(2, 4));
				}
				
				if (currentLine.startsWith("DTEND;")) {
					tab = currentLine.split(":");
					str = tab[1];
					tab = str.split("T");
					str = tab[1];
					hf = Integer.parseInt(str.substring(0, 2));
					minf = Integer.parseInt(str.substring(2,4));
				}

				if (currentLine.startsWith("BEGIN:VCARD")) {
					vc = new VirtualCard(br);
				}
				
				if (currentLine.startsWith("DESCRIPTION:")) {
					tab = currentLine.split(":");
					if (tab.length > 1) {
						tache = tab[1];
					}
				}
				
				if (currentLine.startsWith("END:VEVENT")) {
					if (vc != null) {
						CreneauRDV cr = new CreneauRDV(hd,mind,hf,minf,vc);
						if (edt.containsKey(date)) {
							Journee j = edt.get(date);
							j.ajouter(cr);
						} else {
							Journee j = new Journee (date);
							j.ajouter(cr);
							edt.put(date, j);
						}
					} else if (tache != null) {
						CreneauTache cr = new CreneauTache (hd,mind,hf,minf,tache);
						if (edt.containsKey(date)) {
							Journee j = edt.get(date);
							j.ajouter(cr);
						} else {
							Journee j = new Journee (date);
							j.ajouter(cr);
							edt.put(date, j);
						}
					}
				}
			}
			if (edt.isEmpty()) {
				System.out.println("Vide");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CreneauHoraireException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param j
	 */
	public void ajouter (Journee j) {
		edt.put(j.getDate(), j);
	}
	
	/**
	 * 
	 * @param f
	 */
	public void serialize (File f) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.getStackTrace();
		}			
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public EmploiDuTemps deserialize (File f) {
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			EmploiDuTemps edt = (EmploiDuTemps) ois.readObject();
			ois.close();
			return edt;
		} catch (IOException e) {
			e.getStackTrace();
			System.out.println("Le fichier n'existe pas !");
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
		return null; 
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	public Journee searchByDay (String day) {
		return edt.get(day);
	}
	
	/**
	 * 
	 */
	public void fragHtmlCons () {
		System.out.println("<!doctype html>\r\n" +
				"<html lang =\"fr\">\r\n" +
				"<head>\r\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"  <title>Emploi du temps </title>\r\n" +
				"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" +
				"</head>\r\n" +
				"Emploi du temps : <br>");
		for(Entry<String, Journee> entry : edt.entrySet()) {
		    Journee j = entry.getValue();
		    j.fragHtmlCons();
		}
		System.out.println("</body>\r\n" +
				"</html>");
	}
	
	/**
	 * 
	 * @param f
	 */
	public void fileHtmlEDT (File f) {
		String str = ("<!doctype html>\r\n" +
				"<html lang =\"fr\">\r\n" +
				"<head>\r\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" +
				"  <title>Emploi du Temps </title>\r\n" +
				"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" +
				"  <script src=\"script.js\"></script>\r\n" +
				"</head>\r\n" +
				"<head>\r\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" +
				"  <title>Emploi du temps </title>\r\n" +
				"</head>\r\n" +
				"Emploi du temps :<br>");
		BufferedWriter bw;
		try {
			bw = new BufferedWriter (new FileWriter(f));
			bw.write(str);
			for(Entry<String, Journee> entry : edt.entrySet()) {
				Journee j = entry.getValue();
				j.fileHtmlFrag(f, bw);
			}
			bw.write("</body>\r\n" +
			"</html>");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Override
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edt == null) ? 0 : edt.hashCode());
		return result;
	}

	/**
	 * 
	 * @Override
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmploiDuTemps other = (EmploiDuTemps) obj;
		if (edt == null) {
			if (other.edt != null)
				return false;
		} else if (!edt.equals(other.edt))
			return false;
		return true;
	}

	public void affichage () {
		System.out.println("Emploi du temps : \n");
		for (Entry<String, Journee> entry : edt.entrySet()) {
			Journee j= entry.getValue();
			System.out.println(j.toString());
		}
	}
}
