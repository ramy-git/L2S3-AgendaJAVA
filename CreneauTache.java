package projetJavaS3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author thief
 *
 */
public class CreneauTache extends Creneau {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5165996953180829142L;
	/**
	 * 
	 */
	private String tache;
	
	/**
	 * 
	 */
	public CreneauTache () {
		super();
		this.tache = null;
	}
	
	/**
	 * 
	 * @param dbth
	 * @param dbtm
	 * @param finh
	 * @param finm
	 * @param tache
	 * @throws CreneauHoraireException
	 */
	public CreneauTache (int dbth, int dbtm, int finh, int finm, String tache) throws CreneauHoraireException {
		super(dbth,dbtm,finh,finm);
		this.tache = tache;
	}
	
	/**
	 * 
	 * @param dbth
	 * @param dbtm
	 * @param finh
	 * @param finm
	 * @throws CreneauHoraireException
	 */
	public CreneauTache (int dbth, int dbtm, int finh, int finm) throws CreneauHoraireException {
		super(dbth,dbtm,finh,finm);
		this.tache = null;
	}
	
	/**
	 * 
	 * @Override
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
	 * @Override
	 */
	public Creneau deserializable (File f) {  
		Creneau cr;
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			cr = (Creneau) ois.readObject();
			ois.close();
			return cr;
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
	 * @Override
	 */
	public void fragHtmlCons () {
		System.out.println("\t\tCreneau : " +
				"\t\t\tHeure de début : " + this.getHeured() + "h" + this.getMind() + "<br>\r\n" +
				"\t\t\tHeure de fin : " + this.getHeuref() + "h" + this.getMinf() + "<br>\r\n" +
				"\t\t\tTache : " + this.getTache() + "<br>\r\n");
		
		
	}

	/**
	 * 
	 * @Override
	 */
	public void fileHtmlFrag (File f, BufferedWriter bw) {
		String str = new String ("&nbsp;&nbsp;&nbsp;&nbsp;Creneau : " +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Heure de début : " + this.getHeured() + "h" + this.getMind()  + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Heure de fin : " + this.getHeuref() + "h" + this.getMinf() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tache : " + this.tache + "<br>\r\n");
		try {
			bw.write(str);
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
		int result = super.hashCode();
		result = prime * result + ((tache == null) ? 0 : tache.hashCode());
		return result;
	}

	/**
	 * 
	 * @Override
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreneauTache other = (CreneauTache) obj;
		if (tache == null) {
			if (other.tache != null)
				return false;
		} else if (!tache.equals(other.tache))
			return false;
		return true;
	}

	/**
	 * 
	 * @Override
	 */
	public String toString() {
		return "CreneauTache [tache=" + tache + "]";
	}

	/**
	 * 
	 * @return
	 */
	public String getTache() {
		return tache;
	}

	/**
	 * 
	 * @param tache
	 */
	public void setTache(String tache) {
		this.tache = tache;
	}
}
