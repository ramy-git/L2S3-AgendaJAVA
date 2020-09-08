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
public class CreneauRDV extends Creneau {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8878976331923749265L;
	/**
	 * 
	 */
	private VirtualCard vc;
	
	/**
	 * 
	 */
	public CreneauRDV () {
		super();
		this.vc = null;
	}
	
	/**
	 * 
	 * @param dbth
	 * @param dbtm
	 * @param finh
	 * @param finm
	 * @param v
	 * @throws CreneauHoraireException
	 */
	public CreneauRDV (int dbth, int dbtm, int finh, int finm, VirtualCard v) throws CreneauHoraireException {
		super(dbth,dbtm,finh,finm);
		this.vc = v;
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
		System.out.println("\t\tCreneau :<br>\n" + 
				"\t\t\tDébut : " + this.getHeured() + "h" + this.getMind() + "<br>\r\n" +
				"\t\t\tFin : " + this.getHeuref() + "h" + this.getMinf() + "<br>\r\n");
		this.vc.fragHtmlCons();
	}


	/**
	 * 
	 * @Override
	 */
	public void fileHtmlFrag (File f, BufferedWriter bw) {
		String str = new String ("&nbsp;&nbsp;&nbsp;&nbsp;Creneau : " +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Début : " + this.getHeured() + "h" + this.getMind() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fin: " + this.getHeuref() + "h" + this.getMinf() + "<br>\r\n");
		try {
			bw.write(str);
			this.vc.fragFileHtmlVCF(f, bw);
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
		result = prime * result + ((vc == null) ? 0 : vc.hashCode());
		return result;
	}
	
	/**
	 * 
	 * 	@Override
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreneauRDV other = (CreneauRDV) obj;
		if (vc == null) {
			if (other.vc != null)
				return false;
		} else if (!vc.equals(other.vc))
			return false;
		return true;
	}

	/**
	 * 
	 * @Override
	 */
	public String toString() {
		return "CreneauRDV [vc=" + vc + "]";
	}

	/**
	 * 
	 * @return
	 */
	public VirtualCard getVc() {
		return vc;
	}

	/**
	 * 
	 * @param vc
	 */
	public void setVc(VirtualCard vc) {
		this.vc = vc;
	}
}
