package projetJavaS3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author thief
 *
 */
public class Journee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 861100229814353172L;
	/*
	 * 
	 */
	private ArrayList <Creneau> creneaux;
	/**
	 * 
	 */
	private String date;
	
	/**
	 * 
	 */
	public Journee () {
		this.date = null;
		creneaux = null;
	}
	
	/**
	 * 
	 * @param date
	 */
	public Journee (String date) {
		this.date = date;
		creneaux = new ArrayList <Creneau>();
	}
	
	/**
	 * 
	 * @param c
	 * @throws CreneauHoraireException
	 */
	public void ajouter (Creneau c) throws CreneauHoraireException {
		if (creneaux.isEmpty()) {
			creneaux.add(c);
		} else {
			if (creneaux.contains(c)) {
				System.out.println("Le créneau existe déjà !");	
			} else if (c.getHeured() == -1) {
				throw new CreneauHoraireException (c.getHeured());
			} else if (c.getHeuref() == -1){
				throw new CreneauHoraireException (c.getHeuref());
			} else {
				int i = 0;
				while (i < creneaux.size()) {
					Creneau cr = creneaux.get(i);
					if (cr.getHeuref() < c.getHeured()) {
						cr = creneaux.get(i + 1);
						if (cr.getHeured() > c.getHeuref()) {
							creneaux.add(i + 1, c);
						} else throw new CreneauHoraireException("Le créneau ne peut pas être ajouté, il dépasse sur le créneau suivant !");
					}
					i++;
				}
			}
		} if (creneaux.contains(c)) {
				System.out.println("Le Creneau a bien été ajouté !");
		} else System.out.println("Le créneau n'a pas été ajouté !");
	}
	
	/**
	 * 
	 * @param c
	 */
	public void supprimer (Creneau c) {
		if (creneaux.contains(c)) {
			creneaux.remove(c);
		}
	}
	
	/**
	 * 
	 */
	public void fragHtmlCons () {
		System.out.println("\tJournee : " + this.date + "<br>\r\n");
		for (Creneau cr : creneaux) {
			cr.fragHtmlCons();
		}
	}
	
	/**
	 * 
	 * @param f
	 * @param bw
	 */
	public void fileHtmlFrag (File f, BufferedWriter bw) {
		String str = new String ("&nbsp;&nbsp;Journée : " + this.date + "<br>\r\n");
		try {
			bw.write(str);
			for (Creneau cr : creneaux) {
				cr.fileHtmlFrag(f, bw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param f
	 */
	public void serializable (File f) {
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
	public ArrayList<Journee> deserializable (File f) {
		ArrayList <Journee> j = new ArrayList<Journee>();
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			j.add((Journee) ois.readObject());
			ois.close();
			return j;
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
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creneaux == null) ? 0 : creneaux.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Journee other = (Journee) obj;
		if (creneaux == null) {
			if (other.creneaux != null)
				return false;
		} else if (!creneaux.equals(other.creneaux))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Journee [creneaux=" + creneaux + ", date=" + date + "]";
	}

	/**
	 * 
	 * @return
	 */
	public String getDate() {
		return this.date;
	}
}
